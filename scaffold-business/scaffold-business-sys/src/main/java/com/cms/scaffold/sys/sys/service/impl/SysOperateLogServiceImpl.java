package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.sys.sys.ao.SysOperateLogAO;
import com.cms.scaffold.sys.sys.bo.SysOperateLogBO;
import com.cms.scaffold.sys.sys.dao.SysOperateLogMapper;
import com.cms.scaffold.sys.sys.domain.SysOperateLog;
import com.cms.scaffold.sys.sys.service.SysOperateLogService;
import com.cms.scaffold.core.baseService.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysOperateLogServiceImpl extends BaseServiceImpl<SysOperateLogMapper, SysOperateLogAO, SysOperateLogBO, SysOperateLog> implements SysOperateLogService {
}
