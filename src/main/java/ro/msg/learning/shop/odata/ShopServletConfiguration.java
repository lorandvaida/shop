package ro.msg.learning.shop.odata;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopServletConfiguration {

    @Bean
    public ServletRegistrationBean odataServlet(ShopServlet shopServlet) {

        return new ServletRegistrationBean(shopServlet, "/odata/*");
    }
}