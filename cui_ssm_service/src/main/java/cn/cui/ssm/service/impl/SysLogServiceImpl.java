package cn.cui.ssm.service.impl;

import cn.cui.ssm.domain.SysLog;
import cn.cui.ssm.mapper.SysLogMapper;
import cn.cui.ssm.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/15
 * @Desc
 */
@Service
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * @param sysLog
     * @return void
     * @exception
     * @Desc 保存日志
     **/
    public void save(SysLog sysLog) throws Exception{
        sysLogMapper.save(sysLog);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return java.util.List<cn.cui.ssm.domain.SysLog>
     * @exception
     * @Desc 查看全部日志
     **/
    public List<SysLog> findAll(int pageNum, int pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        return sysLogMapper.findAll();
    }
}
