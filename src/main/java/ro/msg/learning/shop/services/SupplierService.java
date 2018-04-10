package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Supplier;
import ro.msg.learning.shop.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier readSupplier(int supplierId) {

        return supplierRepository.findOne(supplierId);
    }

    public List<Supplier> readSuppliers() {

        Iterable<Supplier> iterableSuppliers = supplierRepository.findAll();
        List<Supplier> supplierList = new ArrayList<>();

        if(iterableSuppliers != null) {
            for(Supplier supplier : iterableSuppliers) {
                supplierList.add(supplier);
            }
        }

        return supplierList;
    }

    public Supplier saveSupplier(Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(int supplierId) {

        supplierRepository.delete(supplierId);
    }

    public void deleteAllSuppliers() {

        supplierRepository.deleteAll();
    }
}
