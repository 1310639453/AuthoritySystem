package cn.cui.ssm.controller;

import cn.cui.ssm.domain.Role;
import cn.cui.ssm.domain.Users;
import cn.cui.ssm.service.IUsersService;
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
 * @Desc UsersController类
 */
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private IUsersService usersService;

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
        List<Users> list = usersService.findAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user/user-list");
        return mv;
    }

    /**
     * @param id 用户id
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 根据用户id查询用户详情
     **/
    @RequestMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Users user = usersService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user/user-show");
        return mv;
    }

    /**
     * @param id 用户id
     * @return java.lang.String
     * @exception
     * @Desc 查询用户及用户可以添加角色
     **/
    @RequestMapping("/findUserByIdAndAllRole/{id}")
    public ModelAndView findUserByIdAndAllRole(@PathVariable String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Users user = usersService.findById(id);
        List<Role> roleList = usersService.findOtherRoles(id);
        mv.addObject("user", user);
        mv.addObject("roleList", roleList);
        mv.setViewName("user/user-role-add");
        return mv;
    }

    /**
     * @param users 用户信息
     * @return org.springframework.web.servlet.ModelAndView
     * @exception 
     * @Desc 添加用户
     **/
    @RequestMapping("/save")
    public String save(Users users) throws Exception{
        usersService.save(users);
        return "redirect:/user/findAll/1/4";
    }

    /**
     * @param userId 用户id
     * @param ids 角色id数组
     * @return java.lang.String
     * @exception 
     * @Desc 给用户添加角色
     **/
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId, String[] ids) throws Exception{
        usersService.addRoleToUser(userId, ids);
        return "redirect:/user/findAll/1/4";
    }
}