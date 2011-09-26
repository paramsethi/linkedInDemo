package com.ln.dao;

import java.io.InputStream;
import java.util.Map;

/**
 * Connector for oauth
 * 
 * @author parampreetsethi
 *
 */
public interface Connector {
   
    String[] authenticate();

    String[] authorize(String code);

    SiteResponse get(String url);

    void init(String authToken, String authSecret);
    
    void setRequestToken(String reqToken, String reqSecret);

    int signOut();

    public interface SiteResponse {
        public abstract String getBody();

        public abstract int getCode();

        public abstract String getHeader(final String name);

        public abstract Map getHeaders();

        public abstract InputStream getStream();
    }
}

