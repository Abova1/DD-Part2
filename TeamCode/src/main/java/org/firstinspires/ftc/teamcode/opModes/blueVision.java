package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.BlueSampleLocator;
import org.firstinspires.ftc.teamcode.vision.SampleLocator;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import com.acmerobotics.dashboard.FtcDashboard;

@TeleOp
public class blueVision extends LinearOpMode {

    OpenCvCamera webcam;
    BlueSampleLocator pipeline;

    @Override
    public void runOpMode() throws InterruptedException {

        webcam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"));

        pipeline = new BlueSampleLocator();
        webcam.setPipeline(pipeline);



        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
                FtcDashboard.getInstance().startCameraStream(webcam, 60);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addData("Camera Error", errorCode);
                telemetry.update();
            }
        });

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());



        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {


            telemetry.addData("Blue Stream Info: ", pipeline.blueStreamInfo);
            telemetry.addLine();
            telemetry.addData("Yellow Stream Info: ", pipeline.YellowStreamInfo);


            telemetry.update();

        }
    }
}
