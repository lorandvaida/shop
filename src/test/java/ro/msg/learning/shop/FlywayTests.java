package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.services.CreateOrderService;
import ro.msg.learning.shop.services.StockService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlywayTests {

    @Autowired
    private StockService stockService;
    @Autowired
    private CreateOrderService createOrderService;


    @Test
    public void testGetStockListByLocation() {

        assertThat(stockService.getStockList(40).size()).isEqualTo(1);
    }

    @Test
    public void testCreateOrder() {

        //mock data


        //Product category
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Telefoane");
        productCategory.setDescription("Aceasta categorie este dedicata telefoanelor");

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setName("Electrocasnice");
        productCategory2.setDescription("Aceasta categorie este dedicata electrocasnicelor");

        //Supplier
        Supplier supplier = new Supplier();
        supplier.setName("Altex");

        Supplier supplier2 = new Supplier();
        supplier2.setName("Emag");

        //Address
        Address address = new Address();
        address.setAddressCountry("Romania");
        address.setAddressCity("Cluj");
        address.setAddressCounty("Cj");
        address.setAddressStreet("Str. Samuel Brassai Nr. 9");

        Address address2 = new Address();
        address2.setAddressCountry("Romania");
        address2.setAddressCity("Cluj");
        address2.setAddressCounty("Cj");
        address2.setAddressStreet("Str. Aurel Vlaicu Nr. 44");

        //Location
        Location location = new Location();
        location.setName("Telefoane Cluj");
        location.setAddress(address);

        Location location2 = new Location();
        location2.setName("Vasilica's home");
        location2.setAddress(address2);

        //Product
        Product product = new Product();
        product.setName("Samsung");
        product.setDescription("S9");
        product.setPrice(new BigDecimal(499));
        product.setWeight(0.200);
        product.setCategory(productCategory);
        product.setSupplier(supplier);

        Product product2 = new Product();
        product2.setName("Beko");
        product2.setDescription("Frigider");
        product2.setPrice(new BigDecimal(1033));
        product2.setWeight(35);
        product2.setCategory(productCategory2);
        product2.setSupplier(supplier2);

        //Stock
        Stock stock = new Stock();
        stock.setLocation(location);
        stock.setProduct(product);
        stock.setQuantity(23);

        //Customer
        Customer customer = new Customer();
        customer.setFirstName("Vasile");
        customer.setLastName("Pop");
        customer.setUsername("vasile_pop");

        //Order
        Order order = new Order();
        order.setShippedFrom(location);
        order.setCustomer(customer);
        order.setAddress(address2);

        //Order Detail
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(1);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrder(order);
        orderDetail2.setProduct(product2);
        orderDetail2.setQuantity(1);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);
        //orderDetailList.add(orderDetail2);

        CreateOrderDto createOrderDto = new CreateOrderDto();
        createOrderDto.setOrderTimestamp(new Timestamp(System.currentTimeMillis()));
        createOrderDto.setDeliveryAddress(address);
        createOrderDto.setOrderDetailList(orderDetailList);


        Order o = null;

        try{
            o = createOrderService.createOrder(createOrderDto);
        }catch (Exception e) {

        }

        assertThat(o).isNotNull();




    }


/*
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testReadAllProducts() {
        assertThat(productRepository.count()).isEqualTo(3);
    }

    @Test
    public void readOneProduct() {
        assertThat(productRepository.findOne(1)).isEqualTo(1);
    }

    @Test
    public void createOneProduct() {
        Product product = new Product(0,"Celular","Camera VGA, Calitate buna.", new BigDecimal(499), 0.200, 1, 1);
        productRepository.save(product);
        assertThat(productRepository.count()).isEqualTo(4);
    }

    @Test
    public void updateOneProduct() {
        Product product = productRepository.findOne(4);
        assertThat(product.getName()).equals("Celular");
    }

    @Test
    public void deleteOneProduct() {
        productRepository.delete(1);
        assertThat(productRepository.count()).isEqualTo(3);
    }
*/


}
