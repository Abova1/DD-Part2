package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    public Servo servo;

    public Arm(HardwareMap hardwareMap){
        servo = hardwareMap.servo.get("servo");
    }

    public void middle(){
        servo.setPosition(0.5);
    }
    public void left(){
        servo.setPosition(0);
    }
    public void right(){
        servo.setPosition(1);
    }



}
