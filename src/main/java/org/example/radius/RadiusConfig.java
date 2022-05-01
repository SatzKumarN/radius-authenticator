package org.example.radius;

public class RadiusConfig {
    private String ipAddr;
    private String secret;
    private int authPort;
    private int acctPort;
    private int timeout;
    private AuthProtocol authProtocol;

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getAuthPort() {
        return authPort;
    }

    public void setAuthPort(int authPort) {
        this.authPort = authPort;
    }

    public int getAcctPort() {
        return acctPort;
    }

    public void setAcctPort(int acctPort) {
        this.acctPort = acctPort;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public AuthProtocol getAuthProtocol() {
        return authProtocol;
    }

    public void setAuthProtocol(AuthProtocol authProtocol) {
        this.authProtocol = authProtocol;
    }
}
