package org.acme;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class CacheProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String correlationID = in.getHeader("JMSCorrelationID", String.class);
        String message = exchange.getIn().getBody(String.class);
        System.out.println("CacheProcessor.process() correlationID: "+correlationID);
        System.out.println("CacheProcessor.process() Transcript: "+message);
        
    }

}
