package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.subsystems.Arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Slides.Slides;
import org.firstinspires.ftc.teamcode.util.Command.SequentialCommand;


public class TeleHandler {

    private RobotState state = RobotState.REGULAR;
    private Controller Driver, Operator;
    private Arm arm;
    private Slides slides;

    public Globals Globals;



    public TeleHandler(Arm arm, Slides slides, Controller driver ){

        this.Driver = driver;
        this.arm = arm;
        this.slides = slides;

    }

    public RobotState getState(){
        return state;
    }

    public void TeleOp (){

        slides.RunPid();

        switch (state){

            case REGULAR:

                //Correct
                Driver.buttonPressed(Driver :: a,
                        () -> new SequentialCommand(
                                arm.ServoToMiddle(),
                                slides.moveToTarget(),
                                arm.ServoToRight(),
                                slides.diffMove()
                        ),
                        () -> state = RobotState.INVERTED
                );

                //Incorrect (Check the difference in SubSystems)
                Driver.buttonPressed(Driver :: b, () ->
                        new SequentialCommand(

                                arm.ServoToLeft(),
                                slides.moveToTarget(),
                                arm.InvertedServoToLeft(),
                                slides.diffMove()

                        )
                );



                Driver.triggerPressed(Driver :: RT, 0.95, () -> slides.Adjust(true));
                Driver.triggerPressed(Driver :: LT, 0.95, () -> slides.Adjust(false));

            break;

            case INVERTED:

                Driver.buttonPressed(Driver :: b, arm::ServoToRight, ()-> state = RobotState.REGULAR);

            break;

        }

    }
}
