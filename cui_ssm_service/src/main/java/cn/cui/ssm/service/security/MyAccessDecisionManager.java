package cn.cui.ssm.service.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author cui
 * @TIME 2020/2/13 21:34
 * @desc 决策器
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication UserService中循环添加到 GrantedAuthority 对象中的权限信息集合
     * @param object 包含客户端发起的请求的requset信息，可转换为
     *          HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * @param configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
     *                   此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     *                   用来判定用户是否有此权限。如果不在权限表中则放行。
     * @return void
     * @exception
     * @Desc 判定是否拥有权限的决策方法
     **/
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        String needRole;
        Iterator<ConfigAttribute> iter = configAttributes.iterator();
        while (iter.hasNext()) {
            needRole = iter.next().getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()) {//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        throw new AccessDeniedException("Access to "+request.getRequestURL()+" denied");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
