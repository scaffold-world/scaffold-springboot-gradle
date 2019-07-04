package com.cms.scaffold.job.jobManager.dao;

import com.cms.scaffold.job.jobManager.domain.JobInfo;
import com.cms.scaffold.core.baseService.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface JobInfoMapper extends BaseMapper<JobInfo> {

    @Update("update job_info set job_status = #{jobStatus}")
    int updateAllJobStatus(@Param("jobStatus") Integer jobStatus);

    @Delete("delete from job_info where id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("update job_info set job_status = #{jobStatus} where id = #{id}")
    int updateJobStatus(@Param("id") Long id, @Param("jobStatus") Integer jobStatus);
}