package org.example.radius;

public class AuthRequest {
    private String name;
    private String pwd;
    private RadiusConfig config;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public RadiusConfig getConfig() {
        return config;
    }

    public void setConfig(RadiusConfig config) {
        this.config = config;
    }
}
