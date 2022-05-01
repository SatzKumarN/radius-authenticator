package org.example.radius;

public enum AuthProtocol {
	PAP("pap");

	/**
	 * Need to support or test: CHAP("chap"), MSCHAP("mschap"),
	 * MSCHAP_V1("mschapv1"), MSCHAP_V2("mschapv2"), EAP_MD5( "eap-md5"),
	 * EAP_MSCHAP_V2("eap-mschapv2"), EAP_TTLS("eap-ttls")
	 */

	private String protocol;

	private AuthProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getProtocol() {
		return protocol;
	}
}
