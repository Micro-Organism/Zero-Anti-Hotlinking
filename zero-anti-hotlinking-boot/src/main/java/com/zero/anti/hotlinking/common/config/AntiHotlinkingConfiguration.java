package com.zero.anti.hotlinking.common.config;

import com.zero.anti.hotlinking.common.filter.StaticResourceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AntiHotlinkingConfiguration {

    @Bean
    public FilterRegistrationBean<StaticResourceFilter> staticResourceFilter() {
        FilterRegistrationBean<StaticResourceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new StaticResourceFilter());
        registrationBean.addUrlPatterns("/images/*");
        return registrationBean;
    }

}
