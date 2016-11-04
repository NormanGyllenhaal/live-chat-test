package com.rcplatform.livechat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by yang peng on 2016/9/30.
 */
@Configuration
public class WebApiConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        DataHttpMessageConverter dataHttpMessageConverter = new DataHttpMessageConverter();
        converters.add(dataHttpMessageConverter);
    }



}
