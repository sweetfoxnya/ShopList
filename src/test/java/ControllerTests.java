import com.example.shoplist.ShopListApplication;
import com.example.shoplist.model.Product;
import com.example.shoplist.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ShopListApplication.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class ControllerTests {
    //Проверка системы в целом
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void AddProductTest() throws Exception{

        Product product = new Product("basilik");
        ObjectMapper mapper = new ObjectMapper();
        String productJson = mapper.writeValueAsString(product);

        mockMvc.perform(post("/shopList/add") //через подменненный веб-слой, делаем запрос
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andDo(print()) //вывод ответа в консоль
                .andExpect(status().isOk()); //проверяем, что код возврата 200

    }
   // @Sql(value = {"/create-products.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   // @Sql(value = {"/delete-products.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void ShowAllProductsTest() throws Exception{

        mockMvc.perform(get("/")) //через подменненный веб-слой, делаем запрос
                .andDo(print()) //вывод ответа в консоль
                .andExpect(status().isOk()); //проверяем, что код возврата 200
    }
}
