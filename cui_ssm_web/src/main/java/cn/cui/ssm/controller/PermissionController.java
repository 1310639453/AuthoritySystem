package cn.cui.ssm.controller;

import cn.cui.ssm.domain.Permission;
import cn.cui.ssm.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/13
 * @Desc 权限PermissionController
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    /**
     * @param pageNum 当前分页
     * @param pageSize 分页页大小
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 查询所有
     **/
    @RequestMapping("/findAll/{pageNum}/{pageSize}")
    public ModelAndView findAll(@PathVariable int pageNum, @PathVariable int pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> list = permissionService.findAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("permission/permission-list");
        return mv;
    }

    /**
     * @param permission
     * @return java.lang.String
     * @exception 
     * @Desc 保存
     **/
    @RequestMapping("/save")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:/permission/findAll/1/4";
    }
}
