package com.wbq.job;

import org.quartz.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class SpiderJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String path=jobExecutionContext.getJobDetail().getJobDataMap().getString("path");
        try{
            Process process=Runtime.getRuntime().exec("python main.py",null,new File(path));
            BufferedReader in=new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line=null;
            while((line=in.readLine())!=null){
                System.out.println(line);
            }
            in.close();
            int re=process.waitFor();
            System.out.println(re);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
