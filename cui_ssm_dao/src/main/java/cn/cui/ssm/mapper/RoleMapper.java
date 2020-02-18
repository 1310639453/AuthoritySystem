package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.Permission;
import cn.cui.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/12
 * @Desc 角色RoleMapper接口
 */
public interface RoleMapper {

    /**
     * @param userId 用户id
     * @return java.util.List<cn.cui.ssm.domain.Role>
     * @exception
     * @Desc 查询用户的角色信息
     **/
    @Results(id = "roleResultMap", value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class, many = @Many(select = "cn.cui.ssm.mapper.PermissionMapper.findByRoleId", fetchType = FetchType.LAZY))
    })
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    public List<Role> findByUserId(String userId) throws Exception;

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.Role>
     * @exception
     * @Desc 查询所有
     **/
    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    /**
     * @param permissionId 权限id
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception
     * @Desc 查询权限对应的角色
     **/
    @Select("select * from role where id in (select roleId from role_permission where permissionId=#{permissionId})")
    public List<Role> findByPermissionId(String permissionId) throws Exception;

    /**
     * @param roleId 角色id
     * @return cn.cui.ssm.domain.Role
     * @exception 
     * @Desc 查询一个
     **/
    @ResultMap("roleResultMap")
    @Select("select * from role where id = #{roleId}")
    public Role findById(String roleId) throws Exception;

    /**
     * @param roleId 角色id
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception 
     * @Desc 查询角色可以关联的权限
     **/
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findRoleByIdAndAllPermission(String roleId) throws Exception;
    
    /**
     * @param role 角色信息
     * @return
     * @exception
     * @Desc 保存
     **/
    @Select("insert into role values(#{id}, #{roleName}, #{roleDesc})")
    public void save(Role role) throws Exception;

    /**
     * @param roleId 角色 id
     * @param permissionId 权限id
     * @return void
     * @exception
     * @Desc 为角色添加权限
     **/
    @Insert("insert into role_permission values(#{permissionId}, #{roleId})")
    public void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;
}
