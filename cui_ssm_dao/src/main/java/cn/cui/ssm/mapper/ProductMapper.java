package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/9
 * @Desc ProductMapper接口
 */
public interface ProductMapper {

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.Product>
     * @exception
     * @Desc 查询所有的方法
     **/
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    /**
     * @param id
     * @return cn.cui.ssm.domain.Product
     * @exception 
     * @Desc 根据id查询一个
     **/
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;

    /**
     * @param product 产品信息
     * @return void
     * @exception 
     * @Desc 保存
     **/
    @Insert("insert into product(id,productNum,productName,cityName,DepartureTime,productPrice,productDesc,productStatus) " +
            "values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product) throws Exception;

    /**
     * @param productId
     * @return void
     * @exception
     * @Desc 删除
     **/
    @Delete("delete from product where id=#{productId}")
    public void delete(String productId) throws Exception;

    /**
     * @param productId
     * @return void
     * @exception
     * @Desc 修改为状态
     **/
    @Update("update product set productStatus=#{status} where id=#{productId}")
    public void updateStatus(@Param("productId") String productId, @Param("status")int status) throws Exception;
}
