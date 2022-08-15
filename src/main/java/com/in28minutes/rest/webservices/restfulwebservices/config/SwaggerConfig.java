package com.in28minutes.rest.webservices.restfulwebservices.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2);
//    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).
//                groupName("users-public-api").select().
//                apis(RequestHandlerSelectors.any()).
//                paths(PathSelectors.any()).
//                build().
//                pathMapping("/").
//                enableUrlTemplating(false);
//    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30).
//                select().
//                paths(PathSelectors.ant("/*")).
//                apis(RequestHandlerSelectors.basePackage("com.in28minutes.rest.webservices.restfulwebservices")).
//                build().
//                apiInfo(apiDetails());
//    }
//
//    private ApiInfo apiDetails(){
//        return new ApiInfo(
//                "Address book API",
//                "sample api for bla bla",
//                "1.0",
//                "free to use",
//                new Contact("Badri", "http://badri.com", "badri.com"),
//                "API license",
//                "http://license.com",
//                Collections.emptyList());
//    }
}
