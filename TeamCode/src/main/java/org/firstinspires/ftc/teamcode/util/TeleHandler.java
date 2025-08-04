package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.*;

public class TeleHandler {

    private States state = States.Middle;
    private ControllerWrapper Driver, Operator;
    private Arm arm;

    public TeleHandler(Arm arm, ControllerWrapper driver){

        this.Driver = driver;
        this.arm = arm;

    }

    public void TeleOp (){

        Driver.Update();

        if(Driver.x()){

            state = States.Sample;

        }

        if(Driver.b()){

            state = States.Neutral;

        }

        if(Driver.a()){

            state = States.Middle;


        }



        switch(state){

            case Sample:

                arm.left();

                break;
            case Neutral:

                arm.right();

                break;
            case Middle:

                arm.middle();

                break;


        }




    }



}
