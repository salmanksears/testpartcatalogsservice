package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.SOLR_LAYER_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.searshc.hs.psc.logging.Level;
import com.searshc.hs.psc.logging.annotation.CheckPointLogger;
import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.GEN_CONST;
import com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC;
import com.searshc.hspartcatalog.delegator.SolrServiceDelegator;
import com.searshc.hspartcatalog.domain.solr.Model;
import com.searshc.hspartcatalog.exceptions.SolrException;
import com.searshc.hspartcatalog.mapper.ItemDomainMapper;
import com.searshc.hspartcatalog.services.domain.Item;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;
import com.searshc.hspartcatalog.services.vo.request.ItemDescriptionSearchRequest;
import com.searshc.hspartcatalog.services.vo.response.ItemSearchResponse;

@Component
public class ItemDescriptionSearchBO {

	private final static Logger LOGGER = LoggerFactory.getLogger(ItemDescriptionSearchBO.class);
	
	@Autowired
	SolrServiceDelegator solrServiceDelegator;
	
	@Autowired
	ItemDomainMapper itemDomainMapper;
	
	@CheckPointLogger(level = Level.TRACE)
	@ExtCallAuditLogger
	public BaseResponse itemDescriptionSearch(ItemDescriptionSearchRequest request, List<ItemAvailabilityStatusCcdb> availabilityStatusList) throws SolrException {

		ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
		List<String> messages = new ArrayList<String>();

		LOGGER.debug("Item Description Search called with values >>> modelNo:{} brand:{} productType:{} desc:{}",
						request.getModelNo(), request.getBrand(), request.getProductType(), request.getDescription());

		String modelNo = request.getModelNo();
		String brand = request.getBrand();
		String description = request.getDescription();

		try {
			
			// Search formatted model no using fuzzy logic
			List<Model> modelList = solrServiceDelegator.searchModelFuzzy(modelNo);
			LOGGER.debug("{}", modelList);
			if(CollectionUtils.isEmpty(modelList)){
				LOGGER.debug("No matching model available");
				// better throw no data found exception from here.
				messages.add(RESPONSE_DESC.DATA_NOT_FOUND);
				itemSearchResponse.setNumFound(String.valueOf(GEN_CONST.ZERO));
				itemSearchResponse.setMessages(messages);
				return itemSearchResponse;
			}
			
			
			List<Model> joinSearchList = filterBrandsFromModelList(modelList, modelNo, brand);
			LOGGER.debug("joinSearchList size: {}", joinSearchList.size());
			
			itemSearchResponse = filterItems(joinSearchList, availabilityStatusList, description);

			return itemSearchResponse;

		} catch (Exception e) {
			throw new SolrException(SOLR_LAYER_EXCEPTION, e);
		}
	}
	
	private ItemSearchResponse filterItems(List<Model> joinSearchList, List<ItemAvailabilityStatusCcdb> availabilityStatusList, String description) throws Exception{

		List<com.searshc.hspartcatalog.domain.solr.Item> exactMatchItemList = new ArrayList<com.searshc.hspartcatalog.domain.solr.Item>();
		List<com.searshc.hspartcatalog.domain.solr.Item> similarMatchItemList = new ArrayList<com.searshc.hspartcatalog.domain.solr.Item>();
		for(Model model : joinSearchList){
			List<com.searshc.hspartcatalog.domain.solr.Item> foundItemList = solrServiceDelegator.searchItemModelJoinSchematic(model.getFormattedModelNo());
			for(com.searshc.hspartcatalog.domain.solr.Item item : foundItemList){
				if(item.getItemDescription().equalsIgnoreCase(description)){
					// exact match
					exactMatchItemList.add(item);
				}else{
					// make a similar filter here
					similarMatchItemList.add(item);
				}
			}
		}
		
		List<Item> itemList = null;
		
		if(!CollectionUtils.isEmpty(exactMatchItemList)){
			LOGGER.debug("No. of exact items found: {}", exactMatchItemList.size());
			// found exact match
			itemList = getItemsOrSubItemsList(exactMatchItemList, availabilityStatusList);
		} else if(!CollectionUtils.isEmpty(similarMatchItemList)){
			LOGGER.debug("No. of similar items found: {}", similarMatchItemList.size());
			// similar item found
			itemList = getItemsOrSubItemsList(similarMatchItemList, availabilityStatusList);
			
		}  
		
		ItemSearchResponse itemSearchResponse = prepareItemSearchResponse(itemList);
		
		return itemSearchResponse;
	}
	
	private ItemSearchResponse prepareItemSearchResponse(List<Item> itemList){
		
		ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
		if(CollectionUtils.isEmpty(itemList)){
			// no item found
			LOGGER.debug("no item found");
			// better throw no data found exception
			List<String> messages = new ArrayList<String>();
			messages.add(RESPONSE_DESC.DATA_NOT_FOUND);
			itemSearchResponse.setNumFound(String.valueOf(GEN_CONST.ZERO));
			itemSearchResponse.setMessages(messages);
		}else{
			itemSearchResponse.setItems(itemList);
			itemSearchResponse.setNumFound(String.valueOf(itemList.size()));
		}
		
		return itemSearchResponse;
	}
	
	private List<Model> filterBrandsFromModelList(List<Model> modelList, String modelNo, String brand){
		List<Model> exactMatchModelBrandList = new ArrayList<Model>();
		List<Model> exactMatchModelList = new ArrayList<Model>();
		for(Model model : modelList){
			if(model.getModelNo().equalsIgnoreCase(modelNo)){
				if(model.getBrand().equalsIgnoreCase(brand)){
					exactMatchModelBrandList.add(model);
				}else{
					exactMatchModelList.add(model);
				}
			}
		}
		
		List<Model> joinSearchList = new ArrayList<Model>();
		if(!CollectionUtils.isEmpty(exactMatchModelBrandList)){
			LOGGER.info("exact matching model and brand available");
			joinSearchList.addAll(exactMatchModelBrandList);
		}else if(!CollectionUtils.isEmpty(exactMatchModelList)){
			LOGGER.info("exact matching model available not brand");
			joinSearchList.addAll(exactMatchModelList);
		}else{
			LOGGER.info("no exact matching model and brand available");
			joinSearchList.addAll(modelList);
		}
		
		return joinSearchList;
	}
	
	private List<Item> getItemsOrSubItemsList(List<com.searshc.hspartcatalog.domain.solr.Item> solrItemList, List<ItemAvailabilityStatusCcdb> availabilityStatusList) {
		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		List<String> availStatusList = new ArrayList<String>();

		for (ItemAvailabilityStatusCcdb list : availabilityStatusList){
			availStatusList.add(list.getItemAvailabilityStatusCd());
		}

		for (com.searshc.hspartcatalog.domain.solr.Item solrItem : solrItemList) {
			if (StringUtils.isNotBlank(solrItem.getItemId())) {
				if (StringUtils.isNotBlank(solrItem.getSubItemAvailabilityStatus())) {
					item = itemDomainMapper.noSubstituteItems(solrItem);
					itemList.add(item);
				}
				if (StringUtils.isNotBlank(solrItem.getSubItemAvailabilityStatus())
						&& StringUtils.isNotBlank(solrItem.getItemAvailabilityStatus())) {
					if (!availStatusList.contains(solrItem.getItemAvailabilityStatus())
							&& !availStatusList.contains(solrItem.getSubItemAvailabilityStatus())) {
						item = itemDomainMapper.noSubstituteItems(solrItem);
						itemList.add(item);
					}
					if (availStatusList.contains(solrItem.getItemAvailabilityStatus())
							&& !availStatusList.contains(solrItem.getSubItemAvailabilityStatus())) {
						item = itemDomainMapper.substituteItem(solrItem);
						itemList.add(item);
					}
					if (!availStatusList.contains(solrItem.getItemAvailabilityStatus())
							&& availStatusList.contains(solrItem.getSubItemAvailabilityStatus())) {
						item = itemDomainMapper.noSubstituteItems(solrItem);
						itemList.add(item);
					}
				}
			}
		}
		return itemList;
	}
}
