package com.example.shashank.sha;

/**
 * Created by shashank on 02-10-2015.
 */
public class
        Attendance {
    private double attendance;

    static double calculateAttandance(double totalHeld,double totalPresent){
        return totalPresent/totalHeld*100;
    }
    
}
