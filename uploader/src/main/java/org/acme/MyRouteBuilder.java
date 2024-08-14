package org.acme;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/hello")
            .get("/say")
                .to("direct:mockResponse");
                
        rest("/audios")
            .post()
                .consumes("application/octet-stream")

                    // .param().name("agencia").type(RestParamType.query).dataType("integer").example("1")
                    // .endParam()
                    // .param().name("conta").type(RestParamType.query).dataType("integer").example("2")
                    // .endParam()
                    // .param().name("terminalLogNsu").type(RestParamType.query).dataType("integer").example("3")
                    // .endParam()
                    // .param().name("nsuSolicitacao").type(RestParamType.query).dataType("integer").example("4")
                    // .endParam()
                    // .param().name("tipoConta").type(RestParamType.query).dataType("integer").allowableValues("0", "1")
                    //     .description("0 =  Conta, 1 = Poupança")
                    // .endParam()
                    // .param().name("nsuSolicitacao").type(RestParamType.query).dataType("integer").example("5")
                    // .endParam()
                .to("direct:sendMessage");
    

            from("direct:sendMessage")
                .to("log:audioEntrada?showHeaders=true")
                .to("amqp:queue:audios")
                .to("log:audioSaidaQueue?showHeaders=true")
            ;

            from("direct:mockResponse")
                .to("log:transacoes?showHeaders=true")
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody(simple("OK"))
            ;
    }
    
}
