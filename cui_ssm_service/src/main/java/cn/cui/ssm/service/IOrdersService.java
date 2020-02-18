package cn.cui.ssm.service;

import cn.cui.ssm.domain.Orders;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/11
 * @Desc Orders订单Service接口
 */
public interface IOrdersService {

    /**
     * @param currentPage 分页当前页
     * @param pageSize 分页页大小
     * @return void
     * @exception 
     * @Desc 查询所有订单
     **/
    public List<Orders> findAll(int currentPage, int pageSize) throws Exception;

    /**
     * @param id 订单id
     * @return cn.cui.ssm.domain.Orders
     * @exception
     * @Desc 根据id查询订单详情
     **/
    public Orders findById(String id) throws Exception;

    /**
     * @param ids
     * @return void
     * @exception
     * @Desc 删除选中
     **/
    public void delete(String[] ids) throws Exception;
}
