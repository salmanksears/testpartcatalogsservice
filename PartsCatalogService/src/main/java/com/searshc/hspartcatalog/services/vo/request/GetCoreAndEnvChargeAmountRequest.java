package com.searshc.hspartcatalog.services.vo.request;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.searshc.hs.psc.logging.CustomToStringStyle;
import com.searshc.hspartcatalog.services.vo.base.BaseRequest;
import com.searshc.hspartcatalog.services.domain.PartDetail;
import com.searshc.hspartcatalog.services.domain.Trans;

@XmlRootElement(name="GetCoreAndEnvChargeAmountRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCoreAndEnvChargeAmountRequest")
public class GetCoreAndEnvChargeAmountRequest extends BaseRequest{
	private Trans trans;
	
	private List<PartDetail> partDetails;
	
	public Trans getTrans() {
		return trans;
	}

	public void setTrans(Trans trans) {
		this.trans = trans;
	}

	public List<PartDetail> getPartDetails() { 
		if(partDetails!=null && !partDetails.isEmpty())
			return partDetails;
		else 
			return null;
	}
	
	public void setPartDetails(List<PartDetail> partDetails) {
		this.partDetails = partDetails;
	}
	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, CustomToStringStyle.custom);
		builder.append("part details cnt", (partDetails == null ? 0 : partDetails.size()));
		return builder.toString();
	}
}
