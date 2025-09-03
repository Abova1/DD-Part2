package org.firstinspires.ftc.teamcode.util;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.Arrays;
import java.util.List;


public class PIDWrapper {

    private PIDFController PIDF;

    public PIDWrapper(PIDFController pidf) {

        this.PIDF = pidf;

    }

    public double calculate(double pos , double targetPos){
       return PIDF.calculate(pos, targetPos);
    }

    public void setPIDF(double p, double i, double d, double f){
        PIDF.setPIDF(p, i, d, f);
    }

    public void run(double currentPos, double targetPos, DcMotorEx... motors){
        double power = calculate(currentPos, targetPos);

        for(DcMotorEx motor : motors){
            motor.setPower(power);
        }
    }


}