package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/13
 * @Desc 权限PermissionMapper接口
 */
public interface PermissionMapper {

    /**
     * @param roleId 角色id
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception 
     * @Desc 根据角色id查询权限
     **/
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByRoleId(String roleId) throws Exception;

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception
     * @Desc 查询所有
     **/
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "permissionName", column = "permissionName"),
            @Result(property = "url", column = "url"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "cn.cui.ssm.mapper.RoleMapper.findByPermissionId", fetchType = FetchType.LAZY))
    })
    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    /**
     * @param permission
     * @return void
     * @exception
     * @Desc 保存
     **/
    @Insert("insert into permission values(#{id}, #{permissionName}, #{url})")
    public void save(Permission permission) throws Exception;
}
