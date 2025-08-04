package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControllerWrapper {

    private final Gamepad gamepad;
    private final Gamepad previousGamepad = new Gamepad();


    public ControllerWrapper(Gamepad gamepad) {

        this.gamepad = gamepad;


    }


    public double getY() {
        return gamepad.left_stick_y;
    }

    public double getX() {
        return gamepad.left_stick_x;
    }

    public double getRx() {
        return gamepad.right_stick_x;
    }

    public void Update() {
        previousGamepad.copy(gamepad);
    }

    /*===============================| Controls |===========================*/

    public boolean a(){
        return gamepad.a && !previousGamepad.a;
    }

    public boolean b(){
        return gamepad.b && !previousGamepad.b;
    }
    public boolean x(){
        return gamepad.x && !previousGamepad.x;
    }
    public boolean y() {
        return gamepad.y && !previousGamepad.y;
    }

    public boolean RB(){
        return gamepad.right_bumper && !previousGamepad.right_bumper;
    }
    public boolean LB(){
        return gamepad.left_bumper && !previousGamepad.left_bumper;
    }

    public boolean DPR(){
        return gamepad.dpad_right && !previousGamepad.dpad_right;
    }
    public boolean DPL(){
        return gamepad.dpad_left && !previousGamepad.dpad_left;
    }
    public boolean DPUP(){
        return gamepad.dpad_up && !previousGamepad.dpad_up;
    }
    public boolean DPDOWN(){
        return gamepad.dpad_down && !previousGamepad.dpad_down;
    }

    public double RT(){
        return gamepad.right_trigger;
    }

    public double LT(){
        return gamepad.left_trigger;
    }


}