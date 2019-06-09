package com.jiaheng.scaffold.job.jobManager.dao;

import com.jiaheng.scaffold.core.baseService.BaseMapper;
import com.jiaheng.scaffold.job.jobManager.domain.JobInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface JobInfoMapper extends BaseMapper<JobInfo> {

    @Update("update gjj_job_info set job_status = #{jobStatus}")
    int updateAllJobStatus(@Param("jobStatus") Integer jobStatus);

    @Delete("delete from gjj_job_info where id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("update gjj_job_info set job_status = #{jobStatus} where id = #{id}")
    int updateJobStatus(@Param("id") Long id, @Param("jobStatus") Integer jobStatus);
}