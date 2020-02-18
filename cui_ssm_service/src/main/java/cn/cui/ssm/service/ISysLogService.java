package cn.cui.ssm.service;

import cn.cui.ssm.domain.SysLog;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/15
 * @Desc
 */
public interface ISysLogService {

    /**
     * @param sysLog
     * @return void
     * @exception
     * @Desc 保存日志
     **/
    public void save(SysLog sysLog) throws Exception;

    /**
     * @param pageNum
     * @param pageSize
     * @return java.util.List<cn.cui.ssm.domain.SysLog>
     * @exception
     * @Desc 查看全部日志
     **/
    public List<SysLog> findAll(int pageNum, int pageSize) throws Exception;
}
