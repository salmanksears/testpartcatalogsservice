package com.searshc.hspartcatalog.util;

import com.searshc.hspartcatalog.services.domain.Client;

public class ThreadLocalContainer {
	private static ThreadLocal<Client> clientThreadLocal = new ThreadLocal<Client>();

	public static void clear() {
		clientThreadLocal.remove();
	}

	public static void setClient(Client client) {
		clientThreadLocal.set(client);
	}

	public static Client getClient() {
		return clientThreadLocal.get();
	}
}
