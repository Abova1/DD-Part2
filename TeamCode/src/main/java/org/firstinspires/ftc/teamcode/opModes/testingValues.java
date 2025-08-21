package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.util.Command;
import org.firstinspires.ftc.teamcode.util.CommandScheduler;
import org.firstinspires.ftc.teamcode.util.ControllerWrapper;
import org.firstinspires.ftc.teamcode.util.States;
import org.firstinspires.ftc.teamcode.util.TeleHandler;

@TeleOp
public class testingValues extends LinearOpMode {

    private ControllerWrapper Driver, Operator;
    private Arm arm;
    private TeleHandler teleHandler;
    private CommandScheduler scheduler;
    private ElapsedTime timer;


    @Override
    public void runOpMode() throws InterruptedException {

        scheduler = new CommandScheduler();
        Driver = new ControllerWrapper(gamepad1, scheduler);
        arm = new Arm(hardwareMap);

        teleHandler = new TeleHandler(arm, Driver);

        timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return;


        while (opModeIsActive()) {

            teleHandler.TeleOp();
            scheduler.run();


            if(timer.milliseconds() > 175) /* Updates every 175 ms*/ {

                telemetry.addData("Current State", teleHandler.getState());

                telemetry.addData("ServoPos", arm.getPos());
                telemetry.update();

                timer.reset();
            }

        }

    }

}