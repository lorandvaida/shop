package ro.msg.learning.shop.odata.jpa;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopJpaServletConfiguration {

    @Bean
    public ServletRegistrationBean odataServlet(ShopJpaServlet shopServlet) {

        return new ServletRegistrationBean(shopServlet, "/odata/*");
    }
}