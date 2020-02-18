package cn.cui.ssm.service;

import cn.cui.ssm.domain.Product;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc ProductService业务层接口
 */
public interface IProductService {

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.Product>
     * @exception
     * @Desc 查询所有
     **/
    public List<Product> findAll(int pageNum, int pageSize) throws Exception;

    /**
     * @param product 产品信息
     * @return void
     * @exception
     * @Desc 保存
     **/
    public void save(Product product) throws Exception;

    /**
     * @param ids
     * @return void
     * @exception
     * @Desc 删除
     **/
    public void delete(String ids[]) throws Exception;

    /**
     * @param ids
     * @return void
     * @exception
     * @Desc 修改为开启状态
     **/
    public void updateStatus(String[] ids, int status)throws Exception;
}
