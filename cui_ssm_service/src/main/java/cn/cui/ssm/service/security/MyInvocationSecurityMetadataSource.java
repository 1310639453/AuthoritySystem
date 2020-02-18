package cn.cui.ssm.service.security;

import cn.cui.ssm.domain.Permission;
import cn.cui.ssm.domain.Role;
import cn.cui.ssm.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author cui
 * @TIME 2020/2/13 22:08
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;
    //存放权限信息(key:url value:url需要的权限)
    private Map<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine() throws Exception {
        map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<Permission> permissions = permissionMapper.findAll();
        for(Permission permission : permissions) {
            array = new ArrayList<>();
            for (Role role : permission.getRoles()) {
                cfg = new SecurityConfig("ROLE_"+role.getRoleName());
                //此处只添加了角色的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
                array.add(cfg);
            }
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getUrl(), array);
        }
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map == null) {
            try {
                loadResourceDefine();
            } catch (Exception e) {
                new IllegalArgumentException(e);
            }
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            resUrl = entry.getKey();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
