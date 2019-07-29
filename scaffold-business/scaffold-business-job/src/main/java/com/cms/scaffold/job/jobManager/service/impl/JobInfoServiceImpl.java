package com.cms.scaffold.job.jobManager.service.impl;

import com.cms.scaffold.job.jobManager.ao.JobInfoAO;
import com.cms.scaffold.job.jobManager.bo.JobInfoBO;
import com.cms.scaffold.job.jobManager.dao.JobInfoMapper;
import com.cms.scaffold.job.jobManager.domain.JobInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cms.scaffold.job.jobManager.service.JobInfoService;
import com.cms.scaffold.common.base.ResponseListModel;
import com.cms.scaffold.code.baseService.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobInfoServiceImpl extends BaseServiceImpl<JobInfoMapper, JobInfoAO, JobInfoBO, JobInfo> implements JobInfoService {
    @Override
    public ResponseListModel<JobInfoBO> getJobPageList(JobInfoAO ao) {
        PageHelper.startPage(ao.getPage(), ao.getRows());
        List<JobInfoBO> jobInfoBOs = findList(ao);
        PageInfo<JobInfoBO> pageInfo = new PageInfo(jobInfoBOs);
        return new ResponseListModel<>(jobInfoBOs, pageInfo.getTotal());
    }

    @Override
    public int updateAllJobStatus(Integer jobStatus) {
        return dao.updateAllJobStatus(jobStatus);
    }

    @Override
    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    @Override
    public int updateJobStatus(Long id, Integer jobStatus) {
        return dao.updateJobStatus(id, jobStatus);
    }

    @Override
    public void test() {
        try {
            Thread.sleep(1000);
            System.out.println("test----------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
