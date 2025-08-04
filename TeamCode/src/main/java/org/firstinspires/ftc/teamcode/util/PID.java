package org.firstinspires.ftc.teamcode.util;

import com.arcrobotics.ftclib.controller.PIDFController;


public class PID {

    public PIDFController PIDF;


    public PID(PIDFController PIDF){

        this.PIDF = PIDF;

    }

    public double calculate(double pos , double targetPos){
       return PIDF.calculate(pos, targetPos);
    }

    public void setPIDF(double p, double i, double d, double f){
        PIDF.setPIDF(p, i, d, f);
    }


}