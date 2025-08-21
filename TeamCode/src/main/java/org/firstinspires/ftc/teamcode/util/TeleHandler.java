package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.subsystems.*;

public class TeleHandler {

    private States state = States.REGULAR;
    private ControllerWrapper Driver, Operator;
    private Arm arm;

    private CommandScheduler scheduler;

    public TeleHandler(Arm arm, ControllerWrapper driver){

        this.Driver = driver;
        this.arm = arm;

    }

    public States getState(){
        return state;
    }

    public void TeleOp (){

        switch (state){

            case REGULAR:

                Driver.buttonPressed(Driver :: a, () -> arm.ServoToMiddle());

                Driver.buttonPressed(Driver :: x, () -> arm.ServoToLeft());

                Driver.buttonPressed(Driver :: b, () -> arm.ServoToRight() , () -> state = States.INVERTED);
            break;

            case INVERTED:
                Driver.buttonPressed(Driver :: a, () -> arm.InvertedServoToMiddle());

                Driver.buttonPressed(Driver :: x, () -> arm.InvertedServoToLeft());

                Driver.buttonPressed(Driver :: b, () -> arm.InvertedServoToRight(), () -> state = States.REGULAR);
            break;

        }

    }

}
