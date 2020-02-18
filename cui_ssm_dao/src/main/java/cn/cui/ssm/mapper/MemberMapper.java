package cn.cui.ssm.mapper;

import cn.cui.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther cui
 * @Date 2020/2/12
 * @Desc Member会员Mapper接口
 */
public interface MemberMapper {

    /**
     * @param id 会员id
     * @return cn.cui.ssm.domain.Member
     * @exception
     * @Desc 根据id查询一个
     **/
    @Select("select * from member where id = #{id}")
    public Member findById(String id) throws Exception;
}
