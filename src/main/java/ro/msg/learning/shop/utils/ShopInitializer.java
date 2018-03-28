package ro.msg.learning.shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.services.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShopInitializer {

    @Autowired
    private InitService service;

    @PostConstruct
    public void initShop() {
        service.initdb();
    }
}



@Service
@Transactional
class InitService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StockService stockService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private UserService userService;
    //@Autowired
    //private UserRoleRepository userRoleRepository;

    public void initdb() {

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

        productService.saveProduct(product);
        productService.saveProduct(product2);

        //Save Product category
        productCategoryService.saveProductCategory(productCategory);
        productCategoryService.saveProductCategory(productCategory2);

        //Save Supplier
        supplierService.saveSupplier(supplier);
        supplierService.saveSupplier(supplier2);

        //Save Location
        locationService.saveLocation(location);
        locationService.saveLocation(location2);

        //Save Customer
        customerService.saveCustomer(customer);

        //Save Stock
        stockService.saveStock(stock);

        //Save Order
        orderService.saveOrder(order);

        //Save Order Detail
        orderDetailService.saveOrderDetail(orderDetail);

        //create privilege
        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setName("ALL");

        List<UserPrivilege> userPrivilegeList = new ArrayList<>();
        userPrivilegeList.add(userPrivilege);

        //create user role
        UserRole userRole = new UserRole();
        userRole.setRole("CUSTOMER");
        userRole.setPrivileges(userPrivilegeList);

        List<UserRole> userRolesList = new ArrayList<>();
        userRolesList.add(userRole);

        //create user
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setEnabled(true);
        user.setRoles(userRolesList);

        //save user
        userService.saveUser(user);
    }

}
