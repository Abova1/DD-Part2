package org.firstinspires.ftc.teamcode.subsystems.Arm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Command.Command;

public class Arm {

    public Servo servo;

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
            @Override
            public void init() {}

            @Override
            public void execute() {

                servo.setPosition(middle);

            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public void end(){}
        };
    }

    public Command ServoToLeft(){
        return new Command() {
            @Override
            public void init() {}

            @Override
            public void execute() {

                servo.setPosition(left);

            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public void end(){}
        };
    }

    public Command ServoToRight(){
        return new Command() {
            @Override
            public void init() {}

            @Override
            public void execute() {

                servo.setPosition(right);

            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public void end(){}
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
            public void end(){}
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
                return true;
            }

            @Override
            public void end(){}
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
            public void end(){}
        };
    }


}
