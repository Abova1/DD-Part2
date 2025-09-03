package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.subsystems.Arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Slides.Slides;
import org.firstinspires.ftc.teamcode.util.Command.ParallelCommand;
import org.firstinspires.ftc.teamcode.util.Command.SequentialCommand;


public class TeleHandler {

    private RobotState state = RobotState.REGULAR;
    private Controller Driver, Operator;
    private Arm arm;
    private Slides slides;

    private Globals Globals;
    private SequentialCommand servoToMotor;
    private ParallelCommand servoAndMotor;
    private SequentialCommand initPosSeq;
    private ParallelCommand initPosParallel;



    public TeleHandler(Arm arm, Slides slides, Controller driver ){

        this.Driver = driver;
        this.arm = arm;
        this.slides = slides;

    }

    public RobotState getState(){
        return state;
    }

    public void TeleOp (){

        slides.run();

        servoToMotor = new SequentialCommand(
                arm.ServoToMiddle(),
                slides.moveToTarget(),
                arm.ServoToRight(),
                slides.diffMove()
        );

        servoAndMotor = new ParallelCommand(

                arm.ServoToMiddle(),
                slides.moveToTarget()
        );

        initPosSeq = new SequentialCommand(

                arm.ServoToLeft(),
                slides.diffMove()
        );
        initPosParallel = new ParallelCommand(
                arm.ServoToLeft(),
                slides.diffMove()
        );


        switch (state){

            case REGULAR:

                Driver.buttonPressed(Driver :: a, () -> servoToMotor);
                Driver.buttonPressed(Driver :: b, () -> state = RobotState.INVERTED);
                Driver.triggerPressed(Driver :: RT, 0.95, () -> slides.Adjust(true));
                Driver.triggerPressed(Driver :: LT, 0.95, () -> slides.Adjust(false));

            break;

            case INVERTED:

                Driver.buttonPressed(Driver :: y, () -> servoAndMotor);
                Driver.buttonPressed(Driver :: a, () -> initPosSeq , () -> state = RobotState.REGULAR);


                break;

        }

    }
}
