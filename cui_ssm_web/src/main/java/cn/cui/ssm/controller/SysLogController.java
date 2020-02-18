package cn.cui.ssm.controller;

import cn.cui.ssm.domain.SysLog;
import cn.cui.ssm.service.ISysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/15
 * @Desc
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @exception
     * @Desc 查看全部日志
     **/
    @RequestMapping("/findAll/{pageNum}/{pageSize}")
    public ModelAndView findAll(@PathVariable int pageNum, @PathVariable int pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(sysLogs);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
