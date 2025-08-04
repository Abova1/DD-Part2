package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Pivot {

    private DcMotorEx pivot;

    public Pivot (HardwareMap hardwareMap){

        pivot = hardwareMap.get(DcMotorEx.class, "pivot");
        pivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void reset(){

        pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public double getPos(){
        return pivot.getCurrentPosition();
    }

    public void move(int pos){

        pivot.setTargetPosition(pos);
        pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot.setPower(1);

    }





}
