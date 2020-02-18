package cn.cui.ssm;

import cn.cui.ssm.domain.Product;
import cn.cui.ssm.mapper.ProductMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc
 */
public class ProductMapperTest {

    @Test
    public void testFindAll() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-mybatis.xml");
        ProductMapper productMapper = (ProductMapper) ac.getBean(ProductMapper.class);
        List<Product> products = productMapper.findAll();
        for(Product p : products){
            System.out.println(p.getProductName());
        }
    }
}
