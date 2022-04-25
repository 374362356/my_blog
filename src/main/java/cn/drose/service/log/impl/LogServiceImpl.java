package cn.drose.service.log.impl;

import cn.drose.constant.ErrorConstant;
import cn.drose.dao.LogDao;
import cn.drose.exception.BusinessException;
import cn.drose.model.LogDomain;
import cn.drose.service.log.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  请求日志
 * Created by Donghua.Chen on 2018/4/29.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void addLog(String action, String data, String ip, Integer authorId) {
        LogDomain logDomain = new LogDomain();
        logDomain.setAuthorId(authorId);
        logDomain.setIp(ip);
        logDomain.setData(data);
        logDomain.setAction(action);
        logDao.addLog(logDomain);
    }

    @Override
    public void deleteLogById(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        logDao.deleteLogById(id);
    }

    @Override
    public PageInfo<LogDomain> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogDomain> logs = logDao.getLogs();
        PageInfo<LogDomain> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
