package com.example.shashank.sha;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shashank on 02-10-2015.
 */
public class Attendance {
    static double calculateAttandance(double totalHeld,double totalPresent){
        try {
            return totalPresent / totalHeld * 100;
        }catch(Exception e){
            return -1;
        }
    }

    static int calculateDaysCrossing75(int tot_held,int tot_pre,int[] classesEveryDay,int weekDayNum,int minAtt){
        double att=calculateAttandance(tot_held,tot_pre);
        int no_of_days=0;
        for(int i=weekDayNum;att<minAtt;i++){
            tot_held=tot_held+classesEveryDay[i];
            tot_pre=tot_pre+classesEveryDay[i];
            att=calculateAttandance(tot_held,tot_pre);
            no_of_days++;
            if (i==6)
                i=-1;
        }
        return no_of_days;
    }

    static int calculateDaysDropping75(int tot_held,int tot_pre,int[] classesEveryDay,int weekDayNum,int minAtt){
        double att=calculateAttandance(tot_held,tot_pre);
        int no_of_days=0;
        for(int i=weekDayNum;att>minAtt;i++){
            tot_held=tot_held+classesEveryDay[i];
            tot_pre=tot_pre-classesEveryDay[i];
            att=calculateAttandance(tot_held,tot_pre);
            no_of_days++;
            if (i==6)
                i=-1;
        }
        return no_of_days;
    }
}
