package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Command.CommandScheduler;
import org.firstinspires.ftc.teamcode.util.Command.SequentialCommand;


public class TeleHandler {

    private States state = States.REGULAR;
    private ControllerWrapper Driver, Operator;
    private Arm arm;

    private Slides slides;

    private CommandScheduler scheduler;

    public TeleHandler(Arm arm, Slides slides, ControllerWrapper driver ){

        this.Driver = driver;
        this.arm = arm;
        this.slides = slides;

    }

    public States getState(){
        return state;
    }

    public void TeleOp (){


        slides.RunPid();



        switch (state){

            case REGULAR:

                Driver.buttonPressed(Driver :: a, arm::ServoToMiddle );

                Driver.buttonPressed(Driver :: x, arm::ServoToLeft);

                Driver.buttonPressed(Driver :: b, arm::ServoToRight , ()-> state = States.INVERTED);

                Driver.buttonPressed(Driver :: y, () ->
                        new SequentialCommand(
                                slides.moveToTarget(),
                                arm.ServoToMiddle()
                        )
                );

            break;

            case INVERTED:

                Driver.buttonPressed(Driver :: a, () -> arm.InvertedServoToMiddle());

                Driver.buttonPressed(Driver :: x, () -> arm.InvertedServoToLeft());

                Driver.buttonPressed(Driver :: b, () -> arm.InvertedServoToRight(), () -> state = States.REGULAR);
            break;

        }

    }

}
