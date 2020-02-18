package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.Role;
import cn.cui.ssm.domain.Users;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/12
 * @Desc UserMapper接口
 */
public interface UsersMapper {

    /**
     * @param username 用户名
     * @return cn.cui.ssm.domain.Users
     * @exception 
     * @Desc 根据用户名查询用户
     **/
    @Results(id = "usersResultMap", value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "email", property = "email"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "cn.cui.ssm.mapper.RoleMapper.findByUserId", fetchType = FetchType.LAZY))
    })
    @Select("select * from users where username=#{username}")
    public Users findByUsername(String username) throws Exception;

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.Users>
     * @exception
     * @Desc 查询所有
     **/
    @Select("select * from users")
    public List<Users> findAll() throws Exception;

    /**
     * @param id 用户id
     * @return cn.cui.ssm.domain.Users
     * @exception
     * @Desc 根据用户id查询用户详情
     **/
    @ResultMap("usersResultMap")
    @Select("select * from users where id = #{id}")
    public Users findById(String id) throws Exception;

    /**
     * @param users 用户信息
     * @return void
     * @exception 
     * @Desc 添加用户
     **/
    @Select("insert into users values(#{id}, #{email}, #{username}, #{password}, #{phoneNum}, #{status})")
    public void save(Users users) throws Exception;

    /**
     * @param userId 用户id
     * @return java.util.List<cn.cui.ssm.domain.Role>
     * @exception 
     * @Desc 查询用户没有关联的角色信息
     **/
    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    public List<Role> findOtherRoles(String userId) throws Exception;

    /**
     * @param userId 用户id
     * @param roleId 角色id数组
     * @return void
     * @exception
     * @Desc 给用户添加角色
     **/
    @Insert("insert into users_role(userId, roleId) values(#{userId}, #{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
