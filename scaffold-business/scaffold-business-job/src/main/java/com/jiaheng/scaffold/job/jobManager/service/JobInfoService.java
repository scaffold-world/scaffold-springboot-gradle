package com.jiaheng.scaffold.job.jobManager.service;

import com.jiaheng.scaffold.job.jobManager.ao.JobInfoAO;
import com.jiaheng.scaffold.job.jobManager.bo.JobInfoBO;
import com.jiaheng.scaffold.common.base.BaseServiceInterface;
import com.jiaheng.scaffold.common.base.ResponseListModel;


public interface JobInfoService extends BaseServiceInterface<JobInfoAO, JobInfoBO> {
    /**
     * 获取任务列表
     * @param jobInfoAo
     * @return
     */
    ResponseListModel<JobInfoBO> getJobPageList(JobInfoAO jobInfoAo);

    /**
     * 更新所有任务的状态
     * @param jobStatus
     * @return
     */
    int updateAllJobStatus(Integer jobStatus);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * @param id
     * @param jobStatus
     * @return
     */
    int updateJobStatus(Long id, Integer jobStatus);

    void test();
}
