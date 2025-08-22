package org.firstinspires.ftc.teamcode.util;

import com.arcrobotics.ftclib.controller.PIDFController;


public class PID {

    private PIDFController PIDF;


    public PID(PIDFController pidf) {

        this.PIDF = pidf;

    }

    public double calculate(double pos , double targetPos){
       return PIDF.calculate(pos, targetPos);
    }

    public void setPIDF(double p, double i, double d, double f){
        PIDF.setPIDF(p, i, d, f);
    }


}