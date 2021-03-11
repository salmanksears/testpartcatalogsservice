package com.searshc.hspartcatalog.services.bo.tasks;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.SOLR_CONST.CONTENT_TYPE;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.mapper.EntityDocMapper;
import com.searshc.hspartcatalog.pojo.Doc;
import com.searshc.hspartcatalog.pojo.SolrResponse;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.vo.request.GetModelDetailsRequest;
import com.searshc.hspartcatalog.util.ApplicationContextUtils;
import com.searshc.hspartcatalog.util.QueryUtils;

public class ModelItemsTask implements Callable<List<Item>> {

	private String processId;
	private GetModelDetailsRequest request;
	private List<ProductGroupCcdb> productGroupList;
	private List<ItemAvailabilityStatusCcdb> availabilityStatusList;
	private List<ItemRestrictionCcdb> restrictionList;
	private String itemSellPriceLimit;
	private List<ItemAttributeCcdb> attributeList;
	
	private EntityDocMapper entityDocMapper = new EntityDocMapper();
	
	private SolrConnection solrConnection;
	
	private static final String SOLR_QUERY_OLD = "{!join+from=modelSchematics+to=itemSchematics}modelId:\"";
	private static final String SOLR_QUERY = "itemModelId:\"";
	private static final String SOLR_COLUMNS = "\"&fl=id,itemModelId,itemSchematicId,itemKeyId,itemId,productGroupIdPls,partNo,productGroupId,productGroupName,itemDescription,itemImageURL,itemAvailabilityStatus,itemSellingPrice,itemCost,itemAddDate,itemAccessoryIndicator,productIndicator,itemCondition,engineModelFlag,formattedItemId,formattedPartNo,subbedFlag,subEngineModelFlag,itemAttributes,itemRestrictions";
	private static final String ALL_SOLR_ROWS = "&rows=1500&wt=json";
	
	private static final Logger logger = LoggerFactory.getLogger(ModelItemsTask.class);
	
	public ModelItemsTask() {
		
	}
	
	public ModelItemsTask(String processId, GetModelDetailsRequest request,
			List<ProductGroupCcdb> productGroupList,
			List<ItemAvailabilityStatusCcdb> availabilityStatusList,
			List<ItemRestrictionCcdb> restrictionList,
			String itemSellPriceLimit, 
			List<ItemAttributeCcdb> attributeList) {

		this.processId = processId;
		this.request = request;
		this.productGroupList = productGroupList;
		this.availabilityStatusList = availabilityStatusList;
		this.restrictionList = restrictionList;
		this.itemSellPriceLimit = itemSellPriceLimit;
		this.attributeList = attributeList;
	}

	@Override
	public List<Item> call() throws Exception {
		return getItemsForModel();
	}
	
	private List<Item> getItemsForModel() throws SolrException{
		
		List<Item> itemList = new ArrayList<Item>();
		SolrResponse solrResponse = null;
		StringBuilder query = new StringBuilder();
		Item item = new Item();
		List<String> attributes = new ArrayList<String>();
		
		try {			
			String modelId = URLEncoder.encode(request.getModelId(), CONTENT_TYPE);
			
			query.append(SOLR_QUERY).append(modelId).append(SOLR_COLUMNS);
			query.append(QueryUtils.getCommonCodeGetItem(productGroupList, restrictionList, itemSellPriceLimit.toString(), availabilityStatusList, true));
			query.append(ALL_SOLR_ROWS);
			
			solrResponse = getSolrConnection().invoke(request.getClientKey(), query.toString());
			
			List<String> availStatusList = new ArrayList<String>();
			
			for(ItemAvailabilityStatusCcdb list : availabilityStatusList)
				availStatusList.add(list.getItemAvailabilityStatusCd());
			
			for(Doc doc : solrResponse.getResponse().getDocs()){
				if(doc.getSubItemAvailabilityStatus() == null){
					item = entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc);
					for(ItemAttributeCcdb attribute : attributeList){
						attributes.add(item.getItemId()+"_"+String.valueOf(attribute.getAttributeId()));
					}
					if(!CollectionUtils.isEmpty(item.getAttributes()))
						item.getAttributes().removeAll(attributes);
					itemList.add(item);
					attributes.clear();
				}
				if(doc.getSubItemAvailabilityStatus() != null && doc.getItemAvailabilityStatus() != null){
					if(!availStatusList.contains(doc.getItemAvailabilityStatus()) && 
							!availStatusList.contains(doc.getSubItemAvailabilityStatus())){
						item = entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc);
						for(ItemAttributeCcdb attribute : attributeList){
							attributes.add(item.getItemId()+"_"+String.valueOf(attribute.getAttributeId()));
							attributes.add(item.getSubItemId()+"_"+String.valueOf(attribute.getAttributeId()));
						}
						if(!CollectionUtils.isEmpty(item.getAttributes()))
							item.getAttributes().removeAll(attributes);
						itemList.add(item);
						attributes.clear();
					}
					if(availStatusList.contains(doc.getItemAvailabilityStatus()) && 
								!availStatusList.contains(doc.getSubItemAvailabilityStatus())){
						for(ItemAttributeCcdb attribute : attributeList){
							attributes.add(doc.getItemId()+"_"+String.valueOf(attribute.getAttributeId()));
							attributes.add(doc.getSubItemId()+"_"+String.valueOf(attribute.getAttributeId()));
						}
						item = entityDocMapper.docToItemSubstituteWithRestrictionAttribute(doc);
						if(!CollectionUtils.isEmpty(item.getAttributes()))
							item.getAttributes().removeAll(attributes);
						itemList.add(item);
						attributes.clear();
					}
					if(!availStatusList.contains(doc.getItemAvailabilityStatus()) && 
							availStatusList.contains(doc.getSubItemAvailabilityStatus())){
						for(ItemAttributeCcdb attribute : attributeList){
							attributes.add(doc.getItemId()+"_"+String.valueOf(attribute.getAttributeId()));
							attributes.add(doc.getSubItemId()+"_"+String.valueOf(attribute.getAttributeId()));
						}
						item = entityDocMapper.docToItemNoSubstituteWithRestrictionAttribute(doc);
						if(!CollectionUtils.isEmpty(item.getAttributes()))
							item.getAttributes().removeAll(attributes);	itemList.add(item);
						attributes.clear();
					}
				}
			}
			
		}catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
		return itemList;
	}
	
	public SolrConnection getSolrConnection() {
		if (solrConnection == null) {
			ApplicationContext appCtx = ApplicationContextUtils
					.getApplicationContext();
			solrConnection = (SolrConnection) appCtx.getBean("solrConnection");
		}

		return solrConnection;
	}
}