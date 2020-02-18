package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/15
 * @Desc
 */
public interface SysLogMapper {

    /**
     * @param sysLog
     * @return void
     * @exception
     * @Desc 保存日志
     **/
    @Insert("insert into sysLog values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method},#{exception})")
    public void save(SysLog sysLog) throws Exception;

    /**
     * @param
     * @return java.util.List<cn.cui.ssm.domain.SysLog>
     * @exception
     * @Desc 查看全部日志
     **/
    @Select("select * from sysLog order by visitTime desc")
    public List<SysLog> findAll() throws Exception;
}
