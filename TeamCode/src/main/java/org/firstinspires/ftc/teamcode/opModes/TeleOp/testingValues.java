package org.firstinspires.ftc.teamcode.opModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Slides.Slides;
import org.firstinspires.ftc.teamcode.util.Command.CommandScheduler;
import org.firstinspires.ftc.teamcode.util.Controller;
import org.firstinspires.ftc.teamcode.util.TeleHandler;

@TeleOp (name="Test OpMode", group="OpModes")
public class testingValues extends LinearOpMode {

    private Controller Driver, Operator;
    private Arm arm;
    private Slides slides;
    private TeleHandler teleHandler;
    private CommandScheduler scheduler;
    private ElapsedTime timer;



    @Override
    public void runOpMode() throws InterruptedException {

        scheduler = new CommandScheduler();
        Driver = new Controller(gamepad1, scheduler);

        arm = new Arm(hardwareMap);
        slides = new Slides(hardwareMap);
        teleHandler = new TeleHandler(arm, slides, Driver);

        timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {

            scheduler.run();
            teleHandler.TeleOp();
            Driver.Update();

            telemetry.addData("Current State", teleHandler.getState());
            telemetry.addData("ServoPos", arm.getPos());
            telemetry.addData("motorPos", slides.getPos());

            telemetry.update();
        }

    }

}