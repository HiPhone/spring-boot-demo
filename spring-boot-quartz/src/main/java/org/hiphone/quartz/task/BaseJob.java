package org.hiphone.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job {

    /**
     * 定时任务实际过程
     * @param context
     * @throws JobExecutionException
     */
    void execute(JobExecutionContext context) throws JobExecutionException;
}
