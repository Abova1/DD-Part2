package org.firstinspires.ftc.teamcode.subsystems.Arm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Command.Command;
import org.firstinspires.ftc.teamcode.util.Command.CommandScheduler;
import org.firstinspires.ftc.teamcode.util.Globals;

public class Arm {

    public Servo servo;
    public ElapsedTime servoTimer = new ElapsedTime();
    public CommandScheduler scheduler;

    private final double initPos = Values.initPos;
    private final double
            middle = Values.middle,
            left = Values.left,
            right = Values.right
    ;
    private final double
            invertedMiddle = Values.invertedMiddle,
            invertedLeft = Values.invertedLeft,
            invertedRight = Values.invertedRight
    ;

    public Arm(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get("servo");

        init();
    }

    public void init(){
        //put whatever the values are
        servo.setPosition(initPos);
    }

    public double getPos(){
        return servo.getPosition();
    }

    public Command ServoToMiddle(){
        return new Command() {
            long timeNeeded;

            @Override
            public void init() {

                servoTimer.reset();

                timeNeeded = Globals.CHUBArmEstimates(Globals.CHUB_SERVO_TYPES.GOBUILDA_TORQUE, 1, 0,0, Math.abs(Globals.getAngle(Globals.SERVO_TYPES_ANGLES.GOBUILDA, getPos()) - 150));
            }

            @Override
            public void execute() {

                servo.setPosition(middle);

            }

            @Override
            public boolean isFinished() {
                return servoTimer.milliseconds() >= timeNeeded;
            }

            @Override
            public void end(boolean cancelled){}
        };
    }

    public Command ServoToLeft(){
        return new Command() {
            long timeNeeded;

            @Override
            public void init() {

                servoTimer.reset();

                timeNeeded = Globals.CHUBArmEstimates(Globals.CHUB_SERVO_TYPES.GOBUILDA_TORQUE, 1, 0,0, Math.abs(Globals.getAngle(Globals.SERVO_TYPES_ANGLES.GOBUILDA, getPos()) - 0));
            }

            @Override
            public void execute() {

                servo.setPosition(left);

            }

            @Override
            public boolean isFinished() {
                return servoTimer.milliseconds() > timeNeeded && getPos() == left;
            }

            @Override
            public void end(boolean cancelled){
            }
        };
    }

    public Command ServoToRight(){
        return new Command() {
            long timeNeeded;


            @Override
            public void init() {

                servoTimer.reset();

                timeNeeded = Globals.CHUBArmEstimates(Globals.CHUB_SERVO_TYPES.GOBUILDA_TORQUE,1, 0,0, Math.abs(Globals.getAngle(Globals.SERVO_TYPES_ANGLES.GOBUILDA, getPos()) - 300));
            }

            @Override
            public void execute() {

                servo.setPosition(right);

            }

            @Override
            public boolean isFinished() {

                return servoTimer.milliseconds() >= timeNeeded;
            }

            @Override
            public void end(boolean cancelled){}
        };
    }

    public Command InvertedServoToMiddle(){
        return new Command() {


            @Override
            public void init() {}

            @Override
            public void execute() {

                servo.setPosition(invertedMiddle);

            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public void end(boolean cancelled){}
        };
    }

    public Command InvertedServoToLeft(){
        return new Command() {
            @Override
            public void init() {}

            @Override
            public void execute() {

                servo.setPosition(invertedLeft);

            }

            @Override
            public boolean isFinished() {
                return servo.getPosition() == invertedLeft;
            }

            @Override
            public void end(boolean cancelled){}
        };
    }

    public Command InvertedServoToRight(){
        return new Command() {
            @Override
            public void init() {}

            @Override
            public void execute() {
                servo.setPosition(invertedRight);
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public void end(boolean cancelled){}
        };
    }


}
