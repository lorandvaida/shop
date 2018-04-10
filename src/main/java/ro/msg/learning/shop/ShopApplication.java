package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class ShopApplication {

	static Logger log = Logger.getLogger(ShopApplication.class.getName());


	public static void main(String[] args) {

		SpringApplication.run(ShopApplication.class, args);
		log.info("Here we go");
	}
}
