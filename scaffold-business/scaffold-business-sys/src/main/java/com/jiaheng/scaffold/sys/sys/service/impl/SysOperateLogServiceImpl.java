package com.jiaheng.scaffold.sys.sys.service.impl;

import com.jiaheng.scaffold.sys.sys.ao.SysOperateLogAO;
import com.jiaheng.scaffold.sys.sys.bo.SysOperateLogBO;
import com.jiaheng.scaffold.sys.sys.dao.SysOperateLogMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysOperateLog;
import com.jiaheng.scaffold.sys.sys.service.SysOperateLogService;
import com.jiaheng.scaffold.core.baseService.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysOperateLogServiceImpl extends BaseServiceImpl<SysOperateLogMapper, SysOperateLogAO, SysOperateLogBO, SysOperateLog> implements SysOperateLogService {
}
