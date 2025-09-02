package org.firstinspires.ftc.teamcode.subsystems.Slides;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Command.Command;
import org.firstinspires.ftc.teamcode.util.PID;

public class Slides {

    private DcMotorEx motor1, motor2;

    private final double p = Values.p, i = Values.i, d = Values.d, f = Values.f;

    private double targetPos = Values.targetPos;
    private final double zero = Values.zero, adjust = Values.adjust, up = Values.up;

    private PID pid = new PID(new PIDFController (p, i, d, f));

    public Slides(HardwareMap hardwareMap){

        motor1 = hardwareMap.get(DcMotorEx.class, "motor0");
//        motor2 = hardwareMap.get(DcMotorEx.class, "motor1");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        reset();

    }
    public void reset(){
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        targetPos = 0;
    }

    public void RunPid(){

        setPIDF();

        double currentPos = getPos();

        double calc = pid.calculate(currentPos, targetPos);

        double Power = calc;

        motor1.setPower(Power);
//        motor2.setPower(Power);


    }

    public void setPIDF(){

        pid.setPIDF(p, i, d, f);

    }

    //use the most accurate motor
    public double getPos(){
        return motor1.getCurrentPosition();
    }



    public Command moveToTarget(){
        return new Command(){

            @Override
            public void init() {

            }

            @Override
            public void execute() {

                targetPos = up;

            }

            @Override
            public boolean isFinished() {

                int error = (int) Math.abs(getPos() - targetPos);
                return error <= 10;

            }

            @Override
            public void end() {
            }

        };
    }

    public Command diffMove(){
        return new Command(){

            @Override
            public void init() {}

            @Override
            public void execute() {

                targetPos = zero;

            }

            @Override
            public boolean isFinished() {

                int error = (int) Math.abs(getPos() - targetPos);
                return error <= 20;

            }

            @Override
            public void end() {}
        };
    }

    public Command Adjust(boolean forward){
        return new Command(){

            @Override
            public void init() {


            }

            @Override
            public void execute() {

                if(forward) {
                    targetPos = getPos() + adjust;
                }

                else if(!forward){
                    targetPos = getPos() - adjust;
                }

            }

            @Override
            public boolean isFinished() {

                return true;

            }

            @Override
            public void end() {}
        };
    }

}
