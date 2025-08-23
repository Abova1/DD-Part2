package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Command.Command;
import org.firstinspires.ftc.teamcode.util.Command.CommandScheduler;
import org.firstinspires.ftc.teamcode.util.Command.ParallelCommand;
import org.firstinspires.ftc.teamcode.util.Command.SequentialCommand;


public class TeleHandler {

    private States state = States.REGULAR;
    private ControllerWrapper Driver, Operator;
    private Arm arm;
    private Slides slides;
    private ElapsedTime timer = new ElapsedTime();

    private CommandScheduler scheduler;

    public TeleHandler(Arm arm, Slides slides, ControllerWrapper driver ){

        this.Driver = driver;
        this.arm = arm;
        this.slides = slides;

    }

    public Command waitFor(double milliseconds){
        return new Command() {
            boolean finished = false;
            @Override
            public void init() {
                timer.reset();
            }
            @Override
            public void execute() {
                if(timer.milliseconds() >= milliseconds){
                    finished = true;
                }
            }
            @Override
            public boolean isFinished() {
                return finished;
            }
            @Override
            public void end() {}
        };
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
                                new ParallelCommand(
                                        slides.moveToTarget(),
                                        arm.ServoToMiddle()
                                )
                );

                Driver.triggerPressed(Driver :: RT , 0.95,() -> slides.slideAdjust(true));
                Driver.triggerPressed(Driver :: LT , 0.95,() -> slides.slideAdjust(false));

            break;

            case INVERTED:

                Driver.buttonPressed(Driver :: y, slides :: diffMove);

                Driver.triggerPressed(Driver :: RT , 0.95,() -> slides.slideAdjust(true));
                Driver.triggerPressed(Driver :: LT , 0.95,() -> slides.slideAdjust(false));

                Driver.buttonPressed(Driver :: b, arm::ServoToRight , ()-> state = States.REGULAR);

            break;

        }

    }

}
