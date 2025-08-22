package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Command.Command;

public class Arm {

    public Servo servo;

    public Arm(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get("servo");
    }

    public void init(){
        //put whatever the values are
        servo.setPosition(initPos());
    }

    public double getPos(){
        return servo.getPosition();
    }

    public double initPos(){
        return 0;
    }

    public double middle() {
        return 0.5;
    }

    public double left() {
        return 0.0;
    }

    public double right() {
        return 1.0;
    }

    public double InvertedMiddle() {
        return 0.0;
    }

    public double InvertedLeft() {
        return 1.0;
    }

    public double InvertedRight() {
        return 0.5;
    }


    public Command ServoToMiddle(){
        return new Command() {
            @Override
            public void init() {}

            @Override
            public void execute() {

                servo.setPosition(middle());

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

                servo.setPosition(left());

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

                servo.setPosition(right());

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

                servo.setPosition(InvertedMiddle());

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

                servo.setPosition(InvertedLeft());

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
                servo.setPosition(InvertedRight());
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
