package cn.cui.ssm.service;

import cn.cui.ssm.domain.Permission;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/13
 * @Desc Permission业务逻辑层接口
 */
public interface IPermissionService {

    /**
     * @param pageNum 当前分页
     * @param pageSize 分页页大小
     * @return java.util.List<cn.cui.ssm.domain.Permission>
     * @exception 
     * @Desc 查询所有
     **/
    public List<Permission> findAll(int pageNum, int pageSize) throws Exception;

    /**
     * @param permission
     * @return void
     * @exception
     * @Desc 保存
     **/
    public void save(Permission permission) throws Exception;
}
