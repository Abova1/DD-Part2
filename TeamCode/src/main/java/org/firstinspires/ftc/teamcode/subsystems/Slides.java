package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.PID;

public class Slides {

    private DcMotorEx Rslide, Lslide;

    private static double p = 0, i = 0, d = 0, f = 0;

    private static double slidesTargetPos = 0;

    private PID PIDFslides = new PID(new PIDFController (p, i, d, f));

    public Slides (HardwareMap hardwareMap){

        Rslide = hardwareMap.get(DcMotorEx.class, "Rslide");
        Lslide = hardwareMap.get(DcMotorEx.class, "Lslide");

        Lslide.setDirection(DcMotorSimple.Direction.REVERSE);

        Rslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void reset(){
        Rslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Rslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //use the most accurate motor
    public double getPos(){
        return Rslide.getCurrentPosition();
    }

    public void move(double pos){

        double power = PIDFslides.calculate(getPos(), slidesTargetPos);

        Rslide.setPower(power);
        Lslide.setPower(power);

    }

    public void setPIDF(){

        PIDFslides.setPIDF(p, i, d, f);

    }


}
