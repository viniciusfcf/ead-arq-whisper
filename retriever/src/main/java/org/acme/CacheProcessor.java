package org.acme;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CacheProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String correlationID = exchange.getIn().getHeader("JMSCorrelationID", String.class);
        System.out.println("CacheProcessor.process() "+correlationID);
    }

}
