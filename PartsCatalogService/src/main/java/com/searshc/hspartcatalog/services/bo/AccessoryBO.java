package com.searshc.hspartcatalog.services.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searshc.hs.psc.logging.annotation.ExtCallAuditLogger;
import com.searshc.hspartcatalog.services.dao.PartsCatalogDao;
import com.searshc.hspartcatalog.services.domain.Accessory;
import com.searshc.hspartcatalog.services.domain.Maintenance;
import com.searshc.hspartcatalog.services.vo.request.AccessoryRequest;

@Component
public class AccessoryBO {
	
	private static final String MAX_ROWS = "100";
	
	@Autowired
	private PartsCatalogDao partsCatalogDao;

	private static final Logger logger = LoggerFactory.getLogger(AccessoryBO.class);

	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<Accessory> getAccessories(AccessoryRequest request) throws Exception {

		List<Accessory> accessories = new ArrayList<Accessory>();
		if(request.getMaxRows() == null)
			request.setMaxRows(MAX_ROWS);
		
		accessories = partsCatalogDao.getAccessoryByModel(request.getProductTypeId(), request.getBrandId(),
				request.getModelNo(), request.getMaxRows());
		
		logger.debug("{} Accessories found for productTypeId:{} brandId:{} modelNo:{})", accessories.size(), request.getProductTypeId(), request.getBrandId(),
				request.getModelNo()); 
		
		return accessories;
	}
	
	@ExtCallAuditLogger(logInput = false, logOutput = false)
	public List<Maintenance> getMaintenanceParts(AccessoryRequest request) throws Exception {

		List<Maintenance> maintenanceParts = new ArrayList<Maintenance>();
		if(request.getMaxRows() == null)
			request.setMaxRows(MAX_ROWS);
		
		maintenanceParts = partsCatalogDao.getMaintenancePartsByModel(request.getProductTypeId(), request.getBrandId(),
				request.getModelNo(), request.getMaxRows());
		
		logger.debug("{} MaintenanceParts found for productTypeId:{} brandId:{} modelNo:{})", maintenanceParts.size(), request.getProductTypeId(), request.getBrandId(),
				request.getModelNo()); 
		
		return maintenanceParts;
	}
}