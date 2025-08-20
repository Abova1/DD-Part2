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

    public void TeleOp (){



        switch (state){
            case REGULAR:

                Driver.buttonPressed(Driver :: a, () -> arm.armCommand(arm.middle()));

                Driver.buttonPressed(Driver :: x, () -> arm.armCommand(arm.left()));

                Driver.buttonPressed(Driver :: b, () -> arm.armCommand(arm.right()));

                if(Driver.y()){

                    state = States.INVERTED;

                }
            break;

            case INVERTED:
                Driver.buttonPressed(Driver :: a, () -> arm.armCommand(arm.InvertedMiddle()));

                Driver.buttonPressed(Driver :: x, () -> arm.armCommand(arm.InvertedLeft()));

                Driver.buttonPressed(Driver :: b, () -> arm.armCommand(arm.InvertedRight()));

                if(Driver.y()){

                    state = States.REGULAR;

                }
            break;

        }





    }



}
