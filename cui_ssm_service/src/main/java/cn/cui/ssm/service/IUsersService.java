package cn.cui.ssm.service;

import cn.cui.ssm.domain.Role;
import cn.cui.ssm.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/12
 * @Desc User类Service接口，继承UserDetails类
 */
public interface IUsersService extends UserDetailsService {
    
    /**
     * @param pageNnm 当前分页
     * @param pageSize 分页页大小
     * @return java.util.List<cn.cui.ssm.domain.Users>
     * @exception 
     * @Desc 查询所有
     **/
    public List<Users> findAll(int pageNnm, int pageSize) throws Exception;

    /**
     * @param id 用户id
     * @return cn.cui.ssm.domain.Users
     * @exception
     * @Desc 根据用户id查询用户详情
     **/
    public Users findById(String id) throws Exception;

    /**
     * @param users 用户信息
     * @return void
     * @exception 
     * @Desc 添加用户
     **/
    public void save(Users users) throws Exception;

    /**
     * @param userId 用户id
     * @return java.util.List<cn.cui.ssm.domain.Role>
     * @exception
     * @Desc 查询用户没有关联的角色信息
     **/
    public List<Role> findOtherRoles(String userId) throws Exception;

    /**
     * @param userId 用户id
     * @param ids 角色id数组
     * @return void
     * @exception
     * @Desc 给用户添加角色
     **/
    public void addRoleToUser(String userId, String[] ids) throws Exception;
}
