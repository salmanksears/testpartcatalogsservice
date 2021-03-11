package com.searshc.hspartcatalog.services.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.searshc.hspartcatalog.services.domain.PartDetailReturn;
import com.searshc.hspartcatalog.services.vo.base.BaseResponse;

@XmlRootElement(name="getCoreAndEnvChargeAmountResponse" , namespace="http://vo.services.hspartcatalog.searshc.com/response/")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GetCoreAndEnvChargeAmountResponse extends BaseResponse{
	
	private List<PartDetailReturn> partDetailReturn;

	@XmlElementWrapper(name="partDetailReturns")
	@XmlElement(name="partDetailReturn")
	public List<PartDetailReturn> getPartDetailReturn() {
		if(partDetailReturn!=null && !partDetailReturn.isEmpty())
			return partDetailReturn;
		else 
			return null;
	}

	public void setPartDetailReturn(List<PartDetailReturn> partDetailReturn) {
		this.partDetailReturn = partDetailReturn;
	}
}
