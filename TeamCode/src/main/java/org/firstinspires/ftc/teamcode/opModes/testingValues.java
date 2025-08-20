package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.util.CommandScheduler;
import org.firstinspires.ftc.teamcode.util.ControllerWrapper;
import org.firstinspires.ftc.teamcode.util.TeleHandler;
import org.firstinspires.ftc.teamcode.util.States;

@TeleOp
public class testingValues extends LinearOpMode {

    private ControllerWrapper Driver, Operator;
    private Arm arm;
    private TeleHandler teleHandler;
    private CommandScheduler scheduler;


    @Override
    public void runOpMode() throws InterruptedException {

        scheduler = new CommandScheduler();
        Driver = new ControllerWrapper(gamepad1, scheduler);
        arm = new Arm(hardwareMap);

        teleHandler = new TeleHandler(arm, Driver);

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            teleHandler.TeleOp();
            scheduler.run();

            telemetry.addData("button a", Driver.a());
            telemetry.addData("button b", Driver.b());
            telemetry.addData("button x", Driver.x());
            telemetry.addData("button y", Driver.y());
            telemetry.addData("ServoPos", arm.getPos());


            telemetry.update();
        }
    }




}