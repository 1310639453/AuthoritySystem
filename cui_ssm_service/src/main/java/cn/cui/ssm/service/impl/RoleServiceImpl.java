package cn.cui.ssm.service.impl;

import cn.cui.ssm.domain.Permission;
import cn.cui.ssm.domain.Role;
import cn.cui.ssm.mapper.RoleMapper;
import cn.cui.ssm.service.IRoleService;
import cn.cui.ssm.service.security.MyInvocationSecurityMetadataSource;
import cn.cui.ssm.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/13
 * @Desc 角色业务逻辑实现类
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MyInvocationSecurityMetadataSource metadataSource;

    /**
     * @param pageNum 当前分页
     * @param pageSize 分页页大小
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 查询所有
     **/
    @Override
    public List<Role> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return roleMapper.findAll();
    }

    /**
     * @param roleId 角色id
     * @return cn.cui.ssm.domain.Role
     * @exception
     * @Desc 查询一个
     **/
    public Role findById(String roleId)throws Exception{
        return roleMapper.findById(roleId);
    }

    /**
     * @param roleId 角色id
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception
     * @Desc 查询角色可以关联的权限
     **/
    public List<Permission> findRoleByIdAndAllPermission(String roleId) throws Exception{
        return roleMapper.findRoleByIdAndAllPermission(roleId);
    }


    /**
     * @param role 角色信息
     * @return
     * @exception
     * @Desc 保存
     **/
    @Override
    public void save(Role role) throws Exception {
        role.setId(UUIDUtils.randomUUIDString());
        roleMapper.save(role);
    }

    /**
     * @param roleId 角色id
     * @param ids 权限id数组
     * @return
     * @exception
     * @Desc 为角色添加权限
     **/
    public void addPermissionToRole(String roleId, String[] ids) throws Exception{
        for (String permissionId : ids){
            roleMapper.addPermissionToRole(roleId, permissionId);
        }
        //权限变更，重新从数据库更新权限信息
        metadataSource.loadResourceDefine();
    }
}
