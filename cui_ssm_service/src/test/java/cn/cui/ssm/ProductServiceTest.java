package cn.cui.ssm;

import cn.cui.ssm.domain.Product;
import cn.cui.ssm.service.IProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc
 */
public class ProductServiceTest {

    @Test
    public void testFindAll() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        IProductService productService = ac.getBean(IProductService.class);
        List<Product> products = productService.findAll();
        for(Product p : products){
            System.out.println(p.getProductName());
        }
    }
}
