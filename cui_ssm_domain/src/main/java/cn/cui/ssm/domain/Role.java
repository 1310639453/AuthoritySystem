package cn.cui.ssm.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/12
 * @Desc
 */
public class Role implements Serializable {
    private String id;
    private String roleName;
    private String roleDesc;

    //角色对应的permission信息
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
