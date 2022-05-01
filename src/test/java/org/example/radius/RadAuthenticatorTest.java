package org.example.radius;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class RadAuthenticatorTest {

	@Container
	private static final RadiusContainer RADIUS = new RadiusContainer();

	private void assertRadiusAuthentication(AuthRequest request, boolean expected) {
		assertThat(triggerAuthenticate(request).isAuthenticated(), is(expected));
	}

	private AuthResponse triggerAuthenticate(AuthRequest request) {
		RadAuthenticator radiusAuthProcessor = new RadAuthenticator();
		return radiusAuthProcessor.authenticate(request);
	}

	private AuthRequest getRequest(String name, String pwd) {
		return getRequest(name, pwd, getValidRadiusConfig());
	}

	private AuthRequest getRequest(String name, String pwd, RadiusConfig config) {
		AuthRequest radiusRequest = new AuthRequest();
		radiusRequest.setName(name);
		radiusRequest.setPwd(pwd);
		radiusRequest.setConfig(config);
		return radiusRequest;
	}

	private RadiusConfig getValidRadiusConfig() {
		return getRadiusConfig(RADIUS.getHost(), 1812, 1813, AuthProtocol.PAP, "ValidSharedSecret123", 2);
	}

	private RadiusConfig getRadiusConfig(String ipAddr, int authPort, int acctPort, AuthProtocol protocol,
			String secret, int timeout) {
		RadiusConfig config = new RadiusConfig();
		config.setIpAddr(ipAddr);
		config.setAuthPort(authPort);
		config.setAcctPort(acctPort);
		config.setAuthProtocol(protocol);
		config.setSecret(secret);
		config.setTimeout(timeout);
		return config;
	}

	@Test
	void testAuthenticateShouldAuthenticateForCorrectUserCredentials() throws Exception {
		assertRadiusAuthentication(getRequest("bob", "ThisIsBobsSecret"), true);
	}

	@Test
	void testAuthenticateShouldNotAuthenticateForInCorrectUserCredentials() throws Exception {
		assertRadiusAuthentication(getRequest("bob", "ThisIsNotBobsSecret"), false);
	}

	@Test
	void testAuthenticateShouldThrowExceptionForInvalidRadiusConfig() throws Exception {
		AuthRequest request = getRequest("bob", "test",
				getRadiusConfig(RADIUS.getHost(), 1812, 1813, AuthProtocol.PAP, "NotValidSharedSecret", 1));
		AuthenticationFailed cause = assertThrows(AuthenticationFailed.class, () -> triggerAuthenticate(request),
				"Method should throw an exception");
		assertThat(cause.getMessage(),
				containsString("net.jradius.exception.TimeoutException: Timeout: No Response from RADIUS Server"));
	}
}
