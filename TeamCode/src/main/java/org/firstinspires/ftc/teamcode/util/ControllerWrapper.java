package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.util.Command.Command;
import org.firstinspires.ftc.teamcode.util.Command.CommandScheduler;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

/*
This Class is meant to simplify controller inputs
 */

public class ControllerWrapper {

    private final Gamepad gamepad;
    private final CommandScheduler scheduler;
    public ControllerWrapper(Gamepad gamepad, CommandScheduler scheduler) {

        this.gamepad = gamepad;
        this.scheduler = scheduler;

    }

    public void buttonPressed(BooleanSupplier wasPressed, Supplier<Command> commandSupplier){

        if(wasPressed.getAsBoolean()){
            scheduler.schedule(commandSupplier.get());
        }

    }

    public void buttonPressed(BooleanSupplier wasPressed, Supplier<Command> commandSupplier, Runnable action){

        if(wasPressed.getAsBoolean()){
            scheduler.schedule(commandSupplier.get());
            action.run();
        }

    }

    public void buttonPressed(BooleanSupplier wasPressed, Runnable action){

        if(wasPressed.getAsBoolean()){
            action.run();
        }

    }

    public void triggerPressed(DoubleSupplier wasPressed, double threshold, Supplier<Command> commandSupplier){

        if(wasPressed.getAsDouble() > threshold){
            scheduler.schedule(commandSupplier.get());
        }

    }

    public void triggerPressed(DoubleSupplier wasPressed, double threshold, Supplier<Command> commandSupplier, Runnable action){

        if(wasPressed.getAsDouble() > threshold){
            scheduler.schedule(commandSupplier.get());
            action.run();
        }

    }

    public void triggerPressed(DoubleSupplier wasPressed, double threshold, Runnable action){

        if(wasPressed.getAsDouble() > threshold){
            action.run();
        }

    }

    /*===============================| Controls |===========================*/

    public double getLy() {
        return gamepad.left_stick_y;
    }

    public double getLx(){
        return gamepad.left_stick_x;
    }

    public double getRx() {
        return gamepad.right_stick_x;
    }
    public double getRy() {
        return gamepad.right_stick_y;
    }


    public boolean a(){
        return gamepad.aWasPressed();
    }

    public boolean b(){
        return gamepad.bWasPressed();
    }
    public boolean x(){
        return gamepad.xWasPressed();
    }
    public boolean y() {
        return gamepad.yWasPressed();
    }

    public boolean RB(){
        return gamepad.rightBumperWasPressed();
    }
    public boolean LB(){
        return gamepad.leftBumperWasPressed();
    }

    public boolean DPR(){
        return gamepad.dpadRightWasPressed();
    }
    public boolean DPL(){
        return gamepad.dpadLeftWasPressed();
    }
    public boolean DPUP(){
        return gamepad.dpadUpWasPressed();
    }
    public boolean DPDOWN(){
        return gamepad.dpadDownWasPressed();
    }

    public double RT(){
        return gamepad.right_trigger;
    }

    public double LT(){
        return gamepad.left_trigger;
    }


    
}