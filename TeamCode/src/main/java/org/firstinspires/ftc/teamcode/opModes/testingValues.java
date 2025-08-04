package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.util.ControllerWrapper;
import org.firstinspires.ftc.teamcode.util.TeleHandler;

@TeleOp
public class testingValues extends LinearOpMode {

    private ControllerWrapper Driver, Operator;
    private Arm arm;
    private TeleHandler teleHandler;

    @Override
    public void runOpMode() throws InterruptedException {

        Driver = new ControllerWrapper(gamepad1);
        arm = new Arm(hardwareMap);

        teleHandler = new TeleHandler(arm, Driver);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            teleHandler.TeleOp();

            telemetry.addData("button a", Driver.a());
            telemetry.addData("button b", Driver.b());
            telemetry.addData("button x", Driver.x());
            telemetry.addData("button y", Driver.y());

            telemetry.update();


        }

    }




}