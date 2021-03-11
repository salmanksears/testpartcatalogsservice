package com.searshc.hspartcatalog.services.bo;

import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.ATTRIBUTES;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.AVAILABILITY_STATUS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.BRANDS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.BRAND_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.CLIENT_DETAILS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.CLIENT_ID;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.CLIENT_KEY;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.DIV_PLS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.ITEM_SELL_PRICE_LIMIT;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.PRODUCT_TYPE;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.CLIENT_CONFIG_DB_FIELDS.RESTRICTIONS;
import static com.searshc.hspartcatalog.constants.PartsCatalogServiceConstants.RESPONSE_DESC.CCDB_LAYER_EXCEPTION;

import java.math.BigDecimal;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.searshc.hspartcatalog.domain.discount.ClientDiscount;
import com.searshc.hspartcatalog.exceptions.CcdbException;
import com.searshc.hspartcatalog.services.dao.ClientConfigMapper;
import com.searshc.hspartcatalog.services.dao.DiscountMapper;
import com.searshc.hspartcatalog.services.domain.BrandCcdb;
import com.searshc.hspartcatalog.services.domain.Client;
import com.searshc.hspartcatalog.services.domain.ItemAttributeCcdb;
import com.searshc.hspartcatalog.services.domain.ItemAvailabilityStatusCcdb;
import com.searshc.hspartcatalog.services.domain.ItemRestrictionCcdb;
import com.searshc.hspartcatalog.services.domain.ProductGroupCcdb;
import com.searshc.hspartcatalog.services.domain.ProductTypeCcdb;

/**
* Title			:   ClientConfigBO
* Description	:	this class fetches data from client config DB and put it into EH Cache 
* @author		:	Abhishek Jain
*/

public class ClientConfigBO {

	@Autowired
	private ClientConfigMapper clientConfigMapper;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private DiscountMapper discountMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientConfigBO.class);
	
	/**
	 * 
	 * @param clientKey
	 * @return
	 * @throws Exception
	 */
	public Client getClientDetailsByKey(String clientKey) throws Exception{
		Element element = getCachedObject(getEhCacheClientKey(CLIENT_DETAILS, clientKey));
		if(element != null) {
			return (Client)element.getObjectValue();
		}
		else{
			Client client = clientConfigMapper.getClientByKey(clientKey);
			//select discounts for and add to Client
			List<ClientDiscount> discounts = discountMapper.getDiscounts(client.getBusinessCd());
			client.setDiscounts(discounts);
			putObjectInCache(getEhCacheClientKey(CLIENT_DETAILS, clientKey), client);
			
			return client;
		}
	}
	
	/**
     * Method 		 	: getRestrictions
     * Description		: This method retrieves restrictions from client config DB and put it into EHCache   
     * @param			: clientId
     * @return			: List<ItemRestrictionCcdb>
     */
	@SuppressWarnings("unchecked")
	public List<ItemRestrictionCcdb> getRestrictions(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(RESTRICTIONS, clientId));
			
			if(element != null) {
				logger.trace("RESTRICTIONS retrieved from Cache for client:{}", clientId);
				return (List<ItemRestrictionCcdb>)element.getObjectValue();
			}
			else{
				List<ItemRestrictionCcdb> restrictions = clientConfigMapper.getRestrictions(clientId);
				putObjectInCache(getEhCacheKey(RESTRICTIONS, clientId), restrictions);
				logger.info("RESTRICTIONS retrieved from DB for client:{}", clientId);
				return restrictions;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: getAttributes
     * Description		: This method retrieves attributes from client config DB and put it into EHCache   
     * @param			: clientId
     * @return			: List<ItemAttributeCcdb>
     */
	@SuppressWarnings("unchecked")
	public List<ItemAttributeCcdb> getAttributes(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(ATTRIBUTES, clientId));
			
			if(element != null) {
				logger.trace("ATTRIBUTES retrieved from Cache for client:{}", clientId);
				return (List<ItemAttributeCcdb>)element.getObjectValue();
			}	
			else{
				List<ItemAttributeCcdb> attributes = clientConfigMapper.getAttributes(clientId);
				putObjectInCache(getEhCacheKey(ATTRIBUTES, clientId), attributes);
				logger.info("ATTRIBUTES retrieved from DB for client:{}", clientId);
				return attributes;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: getItemAvailabilityStatus
     * Description		: This method retrieves from ItemAvailabilityStatus client config DB and put it into EHCache   
     * @param			: clientId
     * @return			: List<ItemAvailabilityStatusCcdb>
     */
	@SuppressWarnings("unchecked")
	public List<ItemAvailabilityStatusCcdb> getItemAvailabilityStatus(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(AVAILABILITY_STATUS, clientId));
			
			if(element != null) {
				logger.trace("AVAILABILITY STATUS retrieved from Cache for client:{}", clientId);
				return (List<ItemAvailabilityStatusCcdb>)element.getObjectValue();
			}	
			else{
				List<ItemAvailabilityStatusCcdb> attributes = clientConfigMapper.getAvailabilityStatus(clientId);
				putObjectInCache(getEhCacheKey(AVAILABILITY_STATUS, clientId), attributes);
				logger.info("AVAILABILITY STATUS retrieved from DB for client:{}", clientId);
				return attributes;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: getBrandIds
     * Description		: This method retrieves brandIds from client config DB and put it into EHCache   
     * @param			: clientId
     * @return			: List<BrandCcdb>
     */
	@SuppressWarnings("unchecked")
	public List<BrandCcdb> getBrandIds(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(BRAND_ID, clientId));
			
			if(element != null) {
				logger.trace("BRAND IDS retrieved from Cache for client:{}", clientId);
				return (List<BrandCcdb>)element.getObjectValue();
			}	
			else{
				List<BrandCcdb> brands = clientConfigMapper.getBrandIds(clientId);
				putObjectInCache(getEhCacheKey(BRAND_ID, clientId), brands);
				logger.info("BRAND IDS retrieved from DB for client:{}", clientId);
				return brands;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: getBrands
     * Description		: This method retrieves brands from client config DB and put it into EHCache   
     * @param			: clientId
     * @return			: List<BrandCcdb>
     */
	@SuppressWarnings("unchecked")
	public List<BrandCcdb> getBrands(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(BRANDS, clientId));
			
			if(element != null) {
				logger.trace("BRANDS retrieved from Cache for client:{}", clientId);
				return (List<BrandCcdb>)element.getObjectValue();
			}	
			else{
				List<BrandCcdb> brands = clientConfigMapper.getBrands(clientId);
				putObjectInCache(getEhCacheKey(BRANDS, clientId), brands);
				logger.info("BRANDS retrieved from DB for client:{}", clientId);
				return brands;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: getProductGroup
     * Description		: This method retrieves productGroup from client config DB and put it into EHCache   
     * @param			: clientId
     * @return			: List<ProductGroupCcdb>
     */
	@SuppressWarnings("unchecked")
	public List<ProductGroupCcdb> getProductGroup(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(DIV_PLS, clientId));
			
			if(element != null) {
				logger.trace("PRODUCT GROUPS retrieved from Cache for client:{}", clientId);
				return (List<ProductGroupCcdb>)element.getObjectValue();
			}	
			else{
				List<ProductGroupCcdb> brands = clientConfigMapper.getProductGroups(clientId);
				putObjectInCache(getEhCacheKey(DIV_PLS, clientId), brands);
				logger.info("PRODUCT GROUPS retrieved from DB for client:{}", clientId);
				return brands;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	public Integer getClientId(String clientKey) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheClientKey(CLIENT_ID, clientKey));
			
			if(element != null) {
				logger.trace("CLIENT ID retrieved from Cache for client:{}", clientKey);
				return (Integer)element.getObjectValue();
			}	
			else{
				Integer clientId = clientConfigMapper.getClientId(clientKey);
				putObjectInCache(getEhCacheClientKey(CLIENT_ID, clientKey), clientId);
				logger.info("CLIENT ID retrieved from DB for client:{}", clientKey);
				return clientId;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}

	/**
     * Method 		 	: getProductType
     * Description		: This method retrieves productType from client config DB and put it into EHCache   
     * @param			: clientId
     * @return			: List<ProductTypeCcdb>
     */
	@SuppressWarnings("unchecked")
	public List<ProductTypeCcdb> getProductType(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(PRODUCT_TYPE, clientId));
			
			if(element != null) {
				logger.trace("PRODUCT TYPE retrieved from Cache for client:{}", clientId);
				return (List<ProductTypeCcdb>)element.getObjectValue();
			}	
			else{
				List<ProductTypeCcdb> productType = clientConfigMapper.getProductType(clientId);
				putObjectInCache(getEhCacheKey(PRODUCT_TYPE, clientId), productType);
				logger.info("PRODUCT TYPE retrieved from DB for client:{}", clientId);
				return productType;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: isClientValid
     * Description		: This method retrieves client info from client config DB and validates if valid puts it into EHCache   
     * @param			: clientId, clientKey
     * @return			: boolean
     */
	public boolean isClientValid(String clientKey) throws CcdbException {
		try{
			boolean isValid = false;
			Element element = getCachedObject(getEhCacheClientKey(CLIENT_KEY, clientKey));
			
			if(element != null) {
				logger.trace("CLIENT KEY retrieved from Cache for Validation using client key:{}", clientKey);
				return (Boolean) element.getObjectValue();
			}	
			else{
				String resp = clientConfigMapper.isClientValidAndActive(clientKey);
				logger.info("CLIENT KEY retrieved from DB for Validation using client key:{}", clientKey);
				if(StringUtils.equalsIgnoreCase(resp, "Y")){
					isValid  = true; 
					putObjectInCache(getEhCacheClientKey(CLIENT_KEY, clientKey), isValid);
					return isValid;
				}
				else 
					return isValid;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: getItemSellingPriceLimit
     * Description		: This method retrieves ItemSellingPriceLimit from client config DB puts it into EHCache   
     * @param			: clientId
     * @return			: boolean
     */
	public BigDecimal getItemSellingPriceLimit(Integer clientId) throws CcdbException {
		try{
			Element element = getCachedObject(getEhCacheKey(ITEM_SELL_PRICE_LIMIT, clientId));
			
			if(element != null) {
				logger.trace("ITEM SELLL PRICE retrieved from Cache for client:{}", clientId);
				return (BigDecimal)element.getObjectValue();
			}	
			else{
				BigDecimal itemSellingPriceLimit = clientConfigMapper.getItemSellPriceLimit(clientId);
				putObjectInCache(getEhCacheKey(ITEM_SELL_PRICE_LIMIT, clientId), itemSellingPriceLimit);
				logger.info("ITEM SELLL PRICE retrieved from DB for client:{}", clientId);
				return itemSellingPriceLimit;
			}
		}catch(Exception e){
			throw new CcdbException(CCDB_LAYER_EXCEPTION, e);
		}
	}
	
	/**
     * Method 		 	: getCache
     * Description		: This method communicates with clientConfigDBCache   
     * @return			: Cache
     */ 
	private Cache getCache() throws Exception{
		return cacheManager.getCache("clientConfigDBCache");
	}

	/**
     * Method 		 	: getCachedObject
     * Description		: This method fetches the cached objects   
     * @return			: Element
     */
	private Element getCachedObject(Object object)throws Exception{
		return getCache().get(object);
	}
	
	/**
     * Method 		 	: putObjectInCache
     * Description		: This method puts the object into cache   
     * @return			: void
     */
	private void putObjectInCache(Object key, Object value)throws Exception{
		if(value != null)
			getCache().put(new Element(key, value));
	}
		
	/**
     * Method 		 	: getEhCacheClientKey
     * Description		: This method creates cache key for client  
     * @return			: String
     */
	private String getEhCacheClientKey(String key, String clientKey){
		return key+clientKey.hashCode();
	}
	
	/**
     * Method 		 	: getEhCacheKey
     * Description		: This method creates cache keys   
     * @return			: String
     */
	private String getEhCacheKey(String key, Integer clientId){
		return key+String.valueOf(clientId.hashCode());
	}
}
