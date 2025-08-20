package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.States;
import org.firstinspires.ftc.teamcode.util.*;

public class Arm {

    private States state;


    public Servo servo;

    public Arm(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get("servo");
    }

    public double getPos(){
        return servo.getPosition();
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


    public Command armCommand(double pos) {

        pos = Math.max(0, Math.min(1 , pos));

        double finalPos = pos;
        return new Command() {

            @Override
            public void init() {

                servo.setPosition(finalPos);

            }

            @Override
            public void execute() {
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public void end() {

            }


        };

    }

}
