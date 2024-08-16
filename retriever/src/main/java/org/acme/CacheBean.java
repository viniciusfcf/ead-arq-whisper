package org.acme;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.infinispan.client.hotrod.RemoteCache;

import io.quarkus.infinispan.client.Remote;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
@Named("cacheBean")
public class CacheBean {

    @Inject
    @Remote("transcricoes") 
    RemoteCache<String, String> cache; 

    public void put(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        String correlationID = in.getHeader("JMSCorrelationID", String.class);
        String message = exchange.getIn().getBody(String.class);
        cache.put(correlationID, message, 1, TimeUnit.DAYS);
        
    }

    public String get(String key) {
        return cache.get(key);
    }

}
