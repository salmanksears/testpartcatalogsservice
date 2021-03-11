package com.searshc.hspartcatalog.util;

import org.apache.commons.lang.StringUtils;

import com.searshc.hspartcatalog.services.domain.Client;

public class ClientUtils {

	public static Client getClient() {
		return ThreadLocalContainer.getClient();
	}
	
	public static String getClientKey() {
		if (ThreadLocalContainer.getClient() == null) {
			return null;
		}

		return ThreadLocalContainer.getClient().getClientKey();
	}

	public static String getClientName() {
		if (ThreadLocalContainer.getClient() == null) {
			return null;
		}

		return ThreadLocalContainer.getClient().getClientName();
	}

	public static Integer getClientId() {
		if (ThreadLocalContainer.getClient() == null) {
			return null;
		}

		return ThreadLocalContainer.getClient().getClientId();
	}

	public static Character getActiveFlag() {
		if (ThreadLocalContainer.getClient() == null) {
			return null;
		}

		return ThreadLocalContainer.getClient().getActiveFlag();
	}
	
	public static boolean isClientActive(){
		if(getActiveFlag() == null){
			return false;
		}
		return StringUtils.equalsIgnoreCase(getActiveFlag().toString(), "Y");
	}
}
