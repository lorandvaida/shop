package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.utils.CsvUtil;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvTest {

    @Test
    public void testWriteToCsv() {

        List<ProductCategory> productCategoryList = new ArrayList<>();

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Telefoane");
        productCategory.setDescription("Aceasta categorie este dedicata telefoanelor");

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setName("Electrocasnice");
        productCategory2.setDescription("Aceasta categorie este dedicata electrocasnicelor");

        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            CsvUtil.writeToCsv(new ProductCategory(), ProductCategory.class,productCategoryList,outputStream);

            StringBuffer expectedResult = new StringBuffer();
            expectedResult.append("\"Aceasta categorie este dedicata telefoanelor\",0,Telefoane\n");
            expectedResult.append("\"Aceasta categorie este dedicata electrocasnicelor\",0,Electrocasnice");

            assertThat(expectedResult.toString().equals(outputStream.toString()));

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
