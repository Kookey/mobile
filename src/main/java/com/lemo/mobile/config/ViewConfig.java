package com.lemo.mobile.config;

import static org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverFactory;
import org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverProperties;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableConfigurationProperties(WebMvcProperties.class)
public class ViewConfig {

  private final WebMvcProperties webMvcProperties;

  public ViewConfig(WebMvcProperties webMvcProperties) {
    this.webMvcProperties = webMvcProperties;
  }

  /**
   * springboot 默认的是混合视图解析器.混合视图解析器中包含了其他解析器,所以
   * 需要 配置一个自己的bean 名称一定要是 viewResolver这样才可以覆盖默认的混合解析器
   * 才能看到自动降级的效果
   *
   * @param viewResolver
   * @param factory
   * @return
   */
  @Bean
  public LiteDeviceDelegatingViewResolver viewResolver(
      FreeMarkerViewResolver viewResolver, DeviceDelegatingViewResolverFactory factory) {
    return factory.createViewResolver(viewResolver);
  }

  /**
   * spirngboot 会自动探测所有的视图解析器,
   * 需要调用DispatcherServlet.setDetectAllViewResolvers(false)方法关闭
   * 不然测不出来自动降级的效果
   * @return
   */
  @Bean(name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
  public DispatcherServlet dispatcherServlet() {
    DispatcherServlet dispatcherServlet = new DispatcherServlet();
    dispatcherServlet.setDispatchOptionsRequest(
        this.webMvcProperties.isDispatchOptionsRequest());
    dispatcherServlet.setDispatchTraceRequest(
        this.webMvcProperties.isDispatchTraceRequest());
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(
        this.webMvcProperties.isThrowExceptionIfNoHandlerFound());
    dispatcherServlet.setDetectAllViewResolvers(false);
    return dispatcherServlet;
  }

}
