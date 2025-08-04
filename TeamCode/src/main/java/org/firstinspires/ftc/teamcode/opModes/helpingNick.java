package org.firstinspires.ftc.teamcode.opModes;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class helpingNick extends LinearOpMode {

    private CRServo swerve;


    @Override
    public void runOpMode() throws InterruptedException {

        swerve = hardwareMap.crservo.get("swerve");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            if(gamepad1.right_trigger > 0.5) {

                swerve.setPower(1);


            }
            else if (gamepad1.left_trigger > 0.5){
                swerve.setPower(-1);
            }
            else {

                swerve.setPower(0);
            }




        }
    }
}