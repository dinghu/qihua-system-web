package com.rqh.system;

//swagger2的配置文件，在项目的启动类的同级文件建立

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 implements WebMvcConfigurer{
    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                //为当前包路径
//                .apis(RequestHandlerSelectors.basePackage("com.rqh.system.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
    @Bean
    public Docket categoryApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .host("youzkj.com")
                .groupName("bookplatform-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rqh.system.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(java.sql.Timestamp.class, java.sql.Date.class)
                .enableUrlTemplating(false);
    }


        private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("rqh接口API")
                //创建人
                .contact(new Contact("Jammy", "http://www.baidu.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //过滤swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v2/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");

    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List <HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List <HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List <HttpMessageConverter <?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List <HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List <HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }


}