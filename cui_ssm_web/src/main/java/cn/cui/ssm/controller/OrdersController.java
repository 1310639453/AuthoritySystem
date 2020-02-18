package cn.cui.ssm.controller;

import cn.cui.ssm.domain.Orders;
import cn.cui.ssm.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/11
 * @Desc 订单Controller
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    /**
     * @param pageNum 分页当前页
     * @param pageSize 分页页大小
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 查询所有订单
     **/
    @RequestMapping("/findAll/{pageNum}/{pageSize}")
    public ModelAndView findAll(@PathVariable int pageNum,
                                @PathVariable int pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(pageNum, pageSize);
        //PageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("order/orders-list");
        return mv;
    }

    /**
     * @param id 订单id
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 根据id查询订单详情
     **/
    @RequestMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders", orders);
        mv.setViewName("order/orders-show");
        return mv;
    }

    /**
     * @param ids
     * @return java.lang.String
     * @exception
     * @Desc 删除选中
     **/
    @RequestMapping("/delete")
    public String delete(String ids[]) throws Exception{
        if(ids!=null){
            ordersService.delete(ids);
        }
        return "redirect:/orders/findAll/1/4";
    }
}
