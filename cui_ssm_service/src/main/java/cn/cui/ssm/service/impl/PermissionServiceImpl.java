package cn.cui.ssm.service.impl;

import cn.cui.ssm.domain.Permission;
import cn.cui.ssm.mapper.PermissionMapper;
import cn.cui.ssm.service.IPermissionService;
import cn.cui.ssm.service.security.MyInvocationSecurityMetadataSource;
import cn.cui.ssm.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/13
 * @Desc Permission业务逻辑层实现类
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MyInvocationSecurityMetadataSource metadataSource;

    /**
     * @param pageNum 当前分页
     * @param pageSize 分页页大小
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception
     * @Desc 查询所有
     **/
    @Override
    public List<Permission> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return permissionMapper.findAll();
    }

    /**
     * @param permission
     * @return void
     * @exception
     * @Desc 保存
     **/
    @Override
    public void save(Permission permission) throws Exception {
        permission.setId(UUIDUtils.randomUUIDString());
        permissionMapper.save(permission);
        //权限变更，重新从数据库更新权限信息
        metadataSource.loadResourceDefine();
    }
}
