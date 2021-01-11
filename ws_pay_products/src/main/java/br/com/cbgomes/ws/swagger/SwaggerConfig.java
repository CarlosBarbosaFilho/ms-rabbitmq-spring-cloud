package br.com.cbgomes.ws.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Component
public class SwaggerConfig  {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.cbgomes.ws"))
                .build();
    }

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }

    private List<ResponseMessage> responseMessagesGet(){
        return new ArrayList<ResponseMessage>(){{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Please contact your system administrator ")
                    .responseModel(new ModelRef("Error"))
                    .build());

            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Forbidden! - Client not identified")
                    .build());
        }};
    }
}
