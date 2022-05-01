package org.example.radius;

import static java.time.Duration.ofMinutes;
import static org.testcontainers.containers.BindMode.READ_ONLY;
import static org.testcontainers.containers.InternetProtocol.UDP;
import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;
import static org.testcontainers.utility.DockerImageName.parse;

import org.testcontainers.containers.GenericContainer;

class RadiusContainer extends GenericContainer<RadiusContainer> {

	public RadiusContainer() {
		super(parse("freeradius/freeradius-server:latest"));
	}

	@Override
	protected void configure() {
		withClasspathResourceMapping("freeradius-docker-config/authorize", "/etc/raddb/mods-config/files/authorize",
				READ_ONLY);
		withClasspathResourceMapping("freeradius-docker-config/clients.conf", "/etc/raddb/clients.conf", READ_ONLY);
		addFixedExposedPort(1812, 1812, UDP);
		addFixedExposedPort(1813, 1813, UDP);
		withCommand("-X");
		waitingFor(forLogMessage(".*Ready to process requests.*\\n", 1));
		withStartupTimeout(ofMinutes(2));
	}
}