package org.example.radius;

import static java.net.InetAddress.getByName;

import java.io.IOException;

import net.jradius.client.RadiusClient;

class ClosableRadiusClient extends RadiusClient implements AutoCloseable {

	public ClosableRadiusClient(RadiusConfig config) throws IOException {
		super(getByName(config.getIpAddr()), config.getSecret(), config.getAuthPort(), config.getAcctPort(),
				config.getTimeout());
	}
}
