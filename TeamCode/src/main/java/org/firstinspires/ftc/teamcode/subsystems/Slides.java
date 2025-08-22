package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Command.Command;
import org.firstinspires.ftc.teamcode.util.PID;

public class Slides {

    private DcMotorEx motor1;

    private static double p = 0.04, i = 0, d = 5e-7, f = 0;

    private static double targetPos = 0;

    private PID pid = new PID(new PIDFController (p, i, d, f));

    public Slides(HardwareMap hardwareMap){

        motor1 = hardwareMap.get(DcMotorEx.class, "motor0");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        reset();

    }

    public void reset(){
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        targetPos = 0;
    }

    //use the most accurate motor
    public double getPos(){
        return motor1.getCurrentPosition();
    }

    public double up(){
        return 800;
    }

    public void setPIDF(){

        pid.setPIDF(p, i, d, f);

    }

    public void RunPid(){

        setPIDF();

        double currentPos = getPos();

        double calc = pid.calculate(currentPos, targetPos);

        double Power = calc;

        motor1.setPower(Power);

    }

    public Command moveToTarget(){
        return new Command(){

            @Override
            public void init() {

            }

            @Override
            public void execute() {

                targetPos = up();

            }

            @Override
            public boolean isFinished() {

                int error = (int) Math.abs(getPos() - targetPos);
                return error <= 30;

            }

            @Override
            public void end() {

            }


        };
    }


}
