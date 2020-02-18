package cn.cui.ssm.service;

import cn.cui.ssm.domain.Permission;
import cn.cui.ssm.domain.Role;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/13
 * @Desc 角色role业务逻辑接口
 */
public interface IRoleService {

    /**
     * @param pageNum 当前页
     * @param pageSize 分页页大小
     * @return java.util.List<cn.cui.ssm.domain.Role>
     * @exception 
     * @Desc 查询所有
     **/
    public List<Role> findAll(int pageNum, int pageSize) throws Exception;

    /**
     * @param roleId 角色id
     * @return cn.cui.ssm.domain.Role
     * @exception
     * @Desc 查询一个
     **/
    public Role findById(String roleId)throws Exception;

    /**
     * @param roleId 角色id
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception
     * @Desc 查询角色可以关联的权限
     **/
    public List<Permission> findRoleByIdAndAllPermission(String roleId) throws Exception;

    /**
     * @param role 角色信息
     * @return
     * @exception
     * @Desc 保存
     **/
    public void save(Role role) throws Exception;

    /**
     * @param roleId 角色id
     * @param ids 权限id数组
     * @return
     * @exception
     * @Desc 为角色添加权限
     **/
    public void addPermissionToRole(String roleId, String[] ids) throws Exception;
}
