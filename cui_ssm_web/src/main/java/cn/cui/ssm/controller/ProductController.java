package cn.cui.ssm.controller;

import cn.cui.ssm.domain.Product;
import cn.cui.ssm.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/10
 * @Desc Product表现层
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 查询所有
     **/
    @RequestMapping("/findAll/{pageNum}/{pageSize}")
    public ModelAndView findAll(@PathVariable int pageNum,
                                @PathVariable int pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product/product-list");
        return mv;
    }

    /**
     * @param product 产品信息
     * @return void
     * @exception
     * @Desc 保存
     **/
    @RequestMapping("/save")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "redirect:/product/findAll/1/4";
    }

    /**
     * @param ids
     * @return java.lang.String
     * @exception
     * @Desc 删除
     **/
    @RequestMapping("/delete")
    public String delete(String[] ids) throws Exception{
        if(ids!=null){
            productService.delete(ids);
        }
        return "redirect:/product/findAll/1/4";
    }

    /**
     * @param ids
     * @return java.lang.String
     * @exception 
     * @Desc 修改为开启状态
     **/
    @RequestMapping("/updateEnable")
    public String updateEnable(String[] ids)throws Exception{
        if(ids!=null){
            productService.updateStatus(ids, 1);
        }
        return "redirect:/product/findAll/1/4";
    }

    /**
     * @param ids
     * @return java.lang.String
     * @exception
     * @Desc 修改为关闭状态
     **/
    @RequestMapping("/updateDisable")
    public String updateDisable(String[] ids)throws Exception{
        if(ids!=null){
            productService.updateStatus(ids, 0);
        }
        return "redirect:/product/findAll/1/4";
    }
}