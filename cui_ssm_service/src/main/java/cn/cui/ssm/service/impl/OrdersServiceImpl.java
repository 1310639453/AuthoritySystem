package cn.cui.ssm.service.impl;

import cn.cui.ssm.domain.Orders;
import cn.cui.ssm.mapper.OrdersMapper;
import cn.cui.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/11
 * @Desc Orders订单Service实现类
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    
    /**
     * @param currentPage 分页当前页
     * @param pageSize 分页页大小
     * @return void
     * @exception 
     * @Desc 查询所有订单
     **/
    @Override
    public List<Orders> findAll(int currentPage, int pageSize) throws Exception {
        PageHelper.startPage(currentPage, pageSize);
        List<Orders> ordersList = ordersMapper.findAll();
        return  ordersList;
    }

    /**
     * @param id 订单id
     * @return cn.cui.ssm.domain.Orders
     * @exception 
     * @Desc 根据id查询订单详情
     **/
    @Override
    public Orders findById(String id) throws Exception {
        Orders orders = ordersMapper.findById(id);
        return orders;
    }

    /**
     * @param ids
     * @return void
     * @exception
     * @Desc 删除选中
     **/
    public void delete(String[] ids) throws Exception{
        for (String orderId : ids){
            ordersMapper.delete(orderId);
        }
    }
}
