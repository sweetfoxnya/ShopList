import com.example.shoplist.ShopListApplication;
import com.example.shoplist.model.Product;
import com.example.shoplist.repository.ProductRepository;
import com.example.shoplist.service.ShopListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ShopListApplication.class)
public class ShoplistServiceTests {

    @Autowired
    private ShopListService shopListService;

    @MockBean //создает подменные объекты, которые эмулируют реально существующие объекты
    private ProductRepository productRepository;

    @Test
    public void AddProductTest(){
        Product product = new Product("Basilik");

        when(shopListService.addProduct(product)).thenReturn(product);
        Product savedProduct = shopListService.addProduct(product);
        assertFalse(product.isBought());
        verify(productRepository, times(1)).save(product); //шпион
        assertThat(savedProduct).isNotNull();
	    Assertions.assertEquals(product, savedProduct);
        productRepository.delete(product);
    }

     @Test
     public void showAllProductsTest(){
         Product product1 = new Product("Basilik");
         Product product2 = new Product("Chicken");
         Product product3 = new Product("Fish");

         List<Product> products = new ArrayList<>();
         products.add(product1);
         products.add(product2);
         products.add(product3);

         when(shopListService.getShopList()).thenReturn(products);
         List<Product> result = shopListService.getShopList();

         verify(productRepository, times(1)).findAll(); //шпион
         assertThat(result).isNotNull();
         assertThat(products.size()).isEqualTo(result.size());
         for(int i =0 ; i< products.size(); i++){
             assertThat(products.get(i).getName()).isEqualTo(result.get(i).getName());
         }
     }
}
