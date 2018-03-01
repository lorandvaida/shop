package ro.msg.learning.shop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.math.BigDecimal;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping
    String viewAll() {
        return "view";
    }

    @RequestMapping("/{id}/view")
    public String view(Model model, @PathVariable("id") long id) {

        Product product = new Product(0,"Celular","Camera VGA, Calitate buna.", new BigDecimal(499), 0.200, 1, 1);

        model.addAttribute(product);

        log.info("displaying view page for person with id {}", id);

        return "view";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("product", productRepository.findAll());

        return "view";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());

        log.info("displaying create page for new person");

        return "edit";
    }

    @RequestMapping("/products")
    HttpEntity<PagedResourcesAssembler<Product>> products(Pageable pageable, PagedResourcesAssembler assembler) {

        //Page<Product> products = productRepository.findAll(pageable);

        //return new ResponseEntity<>(assembler.toResource(products), HttpStatus.OK);

        return null;
    }

}
