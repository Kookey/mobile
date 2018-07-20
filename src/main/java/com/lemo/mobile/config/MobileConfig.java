package com.lemo.mobile.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.mobile.device.DeviceWebArgumentResolver;
import org.springframework.mobile.device.switcher.SiteSwitcherHandlerInterceptor;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MobileConfig extends WebMvcConfigurerAdapter {

  @Bean
  public WebArgumentResolver argumentResolver() {
    return new DeviceWebArgumentResolver();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(siteSwitcherHandlerInterceptor());
  }

//  @Bean
//  public SiteSwitcherHandlerInterceptor siteSwitcherHandlerInterceptor() {
////    return SiteSwitcherHandlerInterceptor.mDot("www.wangxl.com", true);
//    return SiteSwitcherHandlerInterceptor.standard(
//        "www.wangxl.com",
//        "m.wangxl.com",
//        "t.wangxl.com",
//        ".wangxl.com");
//  }

  @Bean
  public SiteSwitcherHandlerInterceptor siteSwitcherHandlerInterceptor() {
//    return SiteSwitcherHandlerInterceptor.mDot("www.wangxl.com", true);
    return SiteSwitcherHandlerInterceptor.urlPath("/m");
  }

  @Bean
  @Order(1)
  public DeviceResolverRequestFilter filter() {
    return new DeviceResolverRequestFilter();
  }

  @Bean
  public FilterRegistrationBean deviceFilter() {
    DeviceResolverRequestFilter filter = new DeviceResolverRequestFilter();
    FilterRegistrationBean bean = new FilterRegistrationBean(filter);
    bean.addUrlPatterns("/home/*");
    bean.setOrder(1);
    return bean;
  }

}