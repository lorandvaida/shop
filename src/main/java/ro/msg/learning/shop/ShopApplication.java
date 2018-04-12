package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class ShopApplication {

    private final static Logger log = Logger.getLogger(ShopApplication.class.getName());

    public static void main(String[] args) {

        SpringApplication.run(ShopApplication.class, args);
        log.info("Here we go");
    }
}
