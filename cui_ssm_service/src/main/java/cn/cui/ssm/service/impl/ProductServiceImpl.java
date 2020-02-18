package cn.cui.ssm.service.impl;

import cn.cui.ssm.domain.Product;
import cn.cui.ssm.mapper.ProductMapper;
import cn.cui.ssm.service.IProductService;
import cn.cui.ssm.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc IProductService实现类
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.Product>
     * @exception
     * @Desc 查询所有
     **/
    @Override
    public List<Product> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return productMapper.findAll();
    }

    /**
     * @param product 产品信息
     * @return void
     * @exception
     * @Desc 保存
     **/
    @Override
    public void save(Product product) throws Exception {
        String id = UUIDUtils.randomUUIDString();
        product.setId(id);
        productMapper.save(product);
    }

    /**
     * @param ids
     * @return void
     * @exception
     * @Desc 删除
     **/
    @Override
    public void delete(String ids[]) throws Exception {
        for (String productId : ids){
            productMapper.delete(productId);
        }
    }

    /**
     * @return void
     * @exception
     * @Desc 修改为状态
     **/
    public void updateStatus(String[] ids, int status)throws Exception{
        for (String productId : ids){
            productMapper.updateStatus(productId, status);
        }
    }
}
