package org.firstinspires.ftc.teamcode.tuners;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config


@TeleOp

public class SlideTuner extends OpMode {

    private PIDFController SlideController;

    private static double sp = 0, si = 0, sd = 0;

    private static double sf = 0;

    private static int slidesTarget = 0;

    private DcMotorEx motor1;




    @Override
    public void init () {

        SlideController = new PIDFController(sp, si, sd, sf);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motor1 = hardwareMap.get(DcMotorEx.class, "motor0");

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }




    @Override
    public void loop () {

        SlideController.setPIDF(sp, si, sd, sf);

        int LslidePos = motor1.getCurrentPosition();

        double pid = SlideController.calculate(LslidePos, slidesTarget);

        double Power = pid;

        motor1.setPower(Power);


        telemetry.addData("motorPos: ", LslidePos);
        telemetry.addData("Target: ", slidesTarget);
        telemetry.addData("Power: " , Power);


        telemetry.update();

    }




}


