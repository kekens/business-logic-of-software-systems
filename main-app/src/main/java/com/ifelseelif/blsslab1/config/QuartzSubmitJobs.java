//package com.ifelseelif.blsslab1.config;
//
//import com.ifelseelif.blsslab1.jobs.RemindJob;
//import org.quartz.JobDetail;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
//
//@Configuration
//public class QuartzSubmitJobs {
//
//    @Bean(name = "remindModer")
//    public JobDetailFactoryBean jobMemberStats() {
//        return QuartzConfig.createJobDetail(RemindJob.class, "Reminder to moder");
//    }
//
//    @Bean(name = "remindModerTrigger")
//    public SimpleTriggerFactoryBean triggerMemberStats(@Qualifier("remindModer") JobDetail jobDetail) {
//        return QuartzConfig.createTrigger(jobDetail, 60000, "Reminder to moder trigger");
//    }
//
//}
