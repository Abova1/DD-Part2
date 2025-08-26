package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.SampleLocator;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

@TeleOp
public class visionOpmode extends LinearOpMode {

    OpenCvCamera webcam;
    SampleLocator pipeline;

    @Override
    public void runOpMode() throws InterruptedException {

        webcam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"));

        pipeline = new SampleLocator();
        webcam.setPipeline(pipeline);



        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
                FtcDashboard.getInstance().startCameraStream(webcam, 120);
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

            telemetry.addData("blueCenter X", pipeline.blueCenterX);
            telemetry.addData("blueCenter Y", pipeline.blueCenterY);
            telemetry.addData("yellowCenter X", pipeline.yellowCenterX);
            telemetry.addData("yellowCenter Y", pipeline.yellowCenterY);

            telemetry.addData("Blue Width", pipeline.blueWidth);
            telemetry.addData("Blue Height", pipeline.blueHeight);
            telemetry.addData("Yellow Width", pipeline.yellowWidth);
            telemetry.addData("Yellow Height", pipeline.yellowHeight);

            telemetry.update();

        }
    }
}
