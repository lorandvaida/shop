package ro.msg.learning.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.utils.CsvUtil;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class ShopApplication {

	static Logger log = Logger.getLogger(ShopApplication.class.getName());


	public static void main(String[] args) {

		SpringApplication.run(ShopApplication.class, args);
		log.info("Here we go");
	}
}
