package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository cutomerRepository;

    @Autowired
    public CustomerService(CustomerRepository cutomerRepository) {
        this.cutomerRepository = cutomerRepository;
    }

    public Customer readCustomer(int customerId) {

        return cutomerRepository.findOne(customerId);
    }

    public List<Customer> readCustomers() {

        Iterable<Customer> iterableCustomers = cutomerRepository.findAll();
        List<Customer> customerList = new ArrayList<>();

        if(iterableCustomers != null) {
            for(Customer customer : iterableCustomers) {
                customerList.add(customer);
            }
        }

        return customerList;
    }

    public Customer saveCustomer(Customer customer) {

        return cutomerRepository.save(customer);
    }

    public void deleteCustomer(int customerId) {

        cutomerRepository.delete(customerId);
    }

    public void deleteAllCustomers() {

        cutomerRepository.deleteAll();
    }
}
