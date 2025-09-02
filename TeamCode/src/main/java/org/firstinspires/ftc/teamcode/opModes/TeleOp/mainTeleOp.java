package org.firstinspires.ftc.teamcode.opModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.subsystems.Slides.Slides;
import org.firstinspires.ftc.teamcode.util.*;
import org.firstinspires.ftc.teamcode.util.Command.CommandScheduler;

@TeleOp (name="Main TeleOp", group="OpModes")
public class mainTeleOp extends LinearOpMode {

    private DT DT;
    private org.firstinspires.ftc.teamcode.subsystems.Slides.Slides Slides;
    private Controller Driver, Operator;

    private CommandScheduler scheduler;


    @Override
    public void runOpMode() throws InterruptedException {


        DT = new DT(hardwareMap);
        Slides = new Slides(hardwareMap);


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            Slides.setPIDF();


            DT.Drive(-Driver.getLy(), Driver.getLx() * 1.1, Driver.getRx());


        }

    }
}