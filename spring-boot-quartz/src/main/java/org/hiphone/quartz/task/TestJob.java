package org.hiphone.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author HiPhone
 */
@Slf4j
@Component
public class TestJob implements BaseJob{

    @Override
    public void execute(JobExecutionContext arg) throws JobExecutionException {
        log.info("quartz schedule is running!!!");
    }
}
