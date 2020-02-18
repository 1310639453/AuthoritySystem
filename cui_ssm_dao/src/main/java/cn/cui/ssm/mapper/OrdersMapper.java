package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.Member;
import cn.cui.ssm.domain.Orders;
import cn.cui.ssm.domain.Product;
import cn.cui.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/11
 * @Desc Orders订单Mapper接口
 */
public interface OrdersMapper {

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.Orders>
     * @exception
     * @Desc 查询所有订单及关联信息
     **/
    @Results(id = "ordersResultMap", value = {@Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "productId", column = "productId"),
            @Result(property = "memberId", column = "memberId"),
            @Result(property = "product", column = "productId", javaType = Product.class, one=@One(select = "cn.cui.ssm.mapper.ProductMapper.findById", fetchType = FetchType.EAGER)),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "cn.cui.ssm.mapper.MemberMapper.findById", fetchType = FetchType.EAGER)),
            @Result(property = "travellers", column = "id", javaType = List.class, many=@Many(select = "cn.cui.ssm.mapper.TravellerMapper.findByOrdersId", fetchType = FetchType.LAZY))
    })
    @Select("select * from orders")
    public List<Orders> findAll() throws Exception;

    /**
     * @param id 订单id
     * @return cn.cui.ssm.domain.Orders
     * @exception
     * @Desc 根据id查询订单详情
     **/
    @Select("select * from orders where id=#{id}")
    @ResultMap("ordersResultMap")
    public Orders findById(String id);

    /**
     * @param orderId
     * @return void
     * @exception
     * @Desc 删除选中
     **/
    @Delete("delete from orders where id = #{orderId}")
    public void delete(String orderId) throws Exception;
}
