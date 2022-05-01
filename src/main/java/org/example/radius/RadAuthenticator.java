package org.example.radius;

import static net.jradius.client.RadiusClient.getAuthProtocol;
import static net.jradius.packet.attribute.AttributeFactory.loadAttributeDictionary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.jradius.client.auth.RadiusAuthenticator;
import net.jradius.dictionary.Attr_UserName;
import net.jradius.dictionary.Attr_UserPassword;
import net.jradius.exception.RadiusException;
import net.jradius.packet.AccessAccept;
import net.jradius.packet.AccessRequest;
import net.jradius.packet.RadiusResponse;
import net.jradius.packet.attribute.AttributeList;

public class RadAuthenticator {

    public AuthResponse authenticate(AuthRequest request) {
        try {
            AuthResponse res = new AuthResponse();
            res.setAuthenticated(doAuthenticate(request) instanceof AccessAccept);
            return res;
        } catch (Exception e) {
            throw new AuthenticationFailed(e);
        }
    }

    private RadiusResponse doAuthenticate(AuthRequest request)
            throws IOException, NoSuchAlgorithmException, RadiusException {
        try (ClosableRadiusClient rc = new ClosableRadiusClient(request.getConfig())) {
            loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
            return rc.authenticate(new AccessRequest(rc, getAttributes(request)), getAuthenticator(request.getConfig()),
                    0);
        }
    }

    private AttributeList getAttributes(AuthRequest request) {
        final AttributeList attrs = new AttributeList();
        attrs.add(new Attr_UserName(request.getName()));
        attrs.add(new Attr_UserPassword(request.getPwd()));
        return attrs;
    }

    private RadiusAuthenticator getAuthenticator(RadiusConfig config) {
        return getAuthProtocol(config.getAuthProtocol().getProtocol());
    }
}
