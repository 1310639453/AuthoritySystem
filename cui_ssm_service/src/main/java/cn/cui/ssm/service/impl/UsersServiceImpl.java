package cn.cui.ssm.service.impl;

import cn.cui.ssm.domain.Role;
import cn.cui.ssm.domain.Users;
import cn.cui.ssm.mapper.UsersMapper;
import cn.cui.ssm.service.IUsersService;
import cn.cui.ssm.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/12
 * @Desc 实现IUserService接口，实现UserDetailsService类的中方法
 */
@Service("userService")
public class UsersServiceImpl implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = null;
        try {
            users = usersMapper.findByUsername(username);
        } catch (Exception e) {
            new RuntimeException(e);
        }
        //把数据库的查询出来的用户信息封装成UserDetails返回
        User userDetails = new User(users.getUsername(), users.getPassword(), users.getStatus()==1?true:false,
                true, true, true,
                getAuthority(users.getRoles()));
        return userDetails;
    }

    /**
     * @param roles 用户所对应的角色信息
     * @return java.util.List<org.springframework.security.core.GrantedAuthority>
     * @exception 
     * @Desc 返回对应的权限信息
     **/
    private List<GrantedAuthority> getAuthority(List<Role> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * @param pageNnm 当前分页
     * @param pageSize 分页页大小
     * @return java.util.List<cn.cui.ssm.domain.Users>
     * @exception 
     * @Desc 查询所有
     **/
    @Override
    public List<Users> findAll(int pageNnm, int pageSize) throws Exception{
        PageHelper.startPage(pageNnm, pageSize);
        return usersMapper.findAll();
    }

    /**
     * @param id  用户id
     * @return cn.cui.ssm.domain.Users
     * @exception
     * @Desc 根据用户id查询用户详情
     **/
    @Override
    public Users findById(String id) throws Exception {
        return usersMapper.findById(id);
    }

    /**
     * @param users 用户信息
     * @return void
     * @exception 
     * @Desc 添加用户
     **/
    @Override
    public void save(Users users) throws Exception {
        users.setId(UUIDUtils.randomUUIDString());
        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword())); //对密码进行加密
        usersMapper.save(users);
    }

    /**
     * @param userId 用户id
     * @return java.util.List<cn.cui.ssm.domain.Role>
     * @exception 
     * @Desc 查询用户没有关联的角色信息
     **/
    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return usersMapper.findOtherRoles(userId);
    }

    /**
     * @param userId 用户id
     * @param ids 角色id数组
     * @return void
     * @exception
     * @Desc 给用户添加角色
     **/
    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String roleId : ids){
            usersMapper.addRoleToUser(userId, roleId);
        }
    }
}
