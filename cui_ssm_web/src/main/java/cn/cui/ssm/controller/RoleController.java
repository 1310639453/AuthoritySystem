package cn.cui.ssm.controller;

import cn.cui.ssm.domain.Permission;
import cn.cui.ssm.domain.Role;
import cn.cui.ssm.service.IRoleService;
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
 * @Desc 角色RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

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
        List<Role> list = roleService.findAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("role/role-list");
        return mv;
    }

    /**
     * @param id 角色 id
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 查询一个
     **/
    @RequestMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        role.getPermissions();
        mv.addObject("role", role);
        mv.setViewName("role/role-show");
        return mv;
    }

    /**
     * @param role 角色信息
     * @return org.springframework.web.servlet.ModelAndView
     * @exception 
     * @Desc 保存
     **/
    @RequestMapping("/save")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:/role/findAll/1/4";
    }

    /**
     * @param roleId 角色id
     * @param ids 权限id数组
     * @return java.lang.String
     * @exception 
     * @Desc 为角色添加权限
     **/
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId, String[] ids) throws Exception{
        roleService.addPermissionToRole(roleId, ids);
        return "redirect:/role/findAll/1/4";
    }

    /**
     * @param id 角色id
     * @return java.lang.String
     * @exception
     * @Desc 查询角色及可以关联的权限
     **/
    @RequestMapping("/findRoleByIdAndAllPermission/{id}")
    public ModelAndView findRoleByIdAndAllPermission(@PathVariable String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        List<Permission> permissionList = roleService.findRoleByIdAndAllPermission(id);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissionList);
        mv.setViewName("role/role-permission-add");
        return mv;
    }
}
