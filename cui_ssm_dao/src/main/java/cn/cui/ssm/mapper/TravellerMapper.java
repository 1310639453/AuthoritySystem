package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/12
 * @Desc 旅客Mapper接口
 */
public interface TravellerMapper {

    /**
     * @param orderId 订单id
     * @return java.util.List<cn.cui.ssm.domain.Traveller>
     * @exception
     * @Desc 根据订单id查询旅客信息
     **/
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{orderId})")
    public List<Traveller> findByOrdersId(String orderId) throws Exception;
}
