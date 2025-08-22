package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Slides;
import org.firstinspires.ftc.teamcode.util.Command.CommandScheduler;
import org.firstinspires.ftc.teamcode.util.ControllerWrapper;
import org.firstinspires.ftc.teamcode.util.TeleHandler;

@TeleOp
public class testingValues extends LinearOpMode {

    private ControllerWrapper Driver, Operator;
    private Arm arm;
    private Slides slides;
    private TeleHandler teleHandler;
    private CommandScheduler scheduler;
    private ElapsedTime timer;



    @Override
    public void runOpMode() throws InterruptedException {

        scheduler = new CommandScheduler();
        Driver = new ControllerWrapper(gamepad1, scheduler);
        arm = new Arm(hardwareMap);
        slides = new Slides(hardwareMap);

        teleHandler = new TeleHandler(arm, slides, Driver);

        arm.init();

        timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {

            scheduler.run();
            teleHandler.TeleOp();

            if(timer.milliseconds() > 175) /* Updates every 175 ms for less input lag*/ {

                telemetry.addData("Current State", teleHandler.getState());

                telemetry.addData("ServoPos", arm.getPos());
                telemetry.addData("motorPos", slides.getPos());

                telemetry.update();

                timer.reset();
            }

        }

    }

}