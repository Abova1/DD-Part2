package org.firstinspires.ftc.teamcode.vision;

import android.graphics.fonts.Font;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class SampleLocator extends OpenCvPipeline {

    public int blueCenterX = -1;
    public int blueCenterY = -1;

    public int yellowCenterX = -1;
    public int yellowCenterY = -1;

    public int yellowOrientation = 0;
    public int blueOrientation = 0;


    @Override
    public Mat processFrame(Mat input) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Scalar lowerBlue = new Scalar(100, 150, 100);
        Scalar upperBlue = new Scalar(150, 255, 255);

        Scalar lowerYellow = new Scalar(20, 50, 50);
        Scalar upperYellow = new Scalar(40, 255, 255);

        Mat blueMask = new Mat();
        Core.inRange(hsv, lowerBlue, upperBlue, blueMask);

        Mat yellowMask = new Mat();
        Core.inRange(hsv, lowerYellow, upperYellow, yellowMask);

        Mat blur = new Mat();
        Imgproc.GaussianBlur(input, blur, new Size(7, 7), 0);


        List<MatOfPoint> blueContours = new ArrayList<>();
        List<MatOfPoint> yellowContours = new ArrayList<>();
        Imgproc.findContours(blueMask, blueContours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.findContours(yellowMask, yellowContours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


        if (!blueContours.isEmpty()) {
            double largestArea = 0;
            Rect largestRectangle = null;

            for (MatOfPoint contour : blueContours) {
                Rect rectangle = Imgproc.boundingRect(contour);

                if (rectangle.area() > largestArea) {
                    largestArea = rectangle.area();
                    largestRectangle = rectangle;
                }
            }

            if (largestRectangle != null) {
                blueCenterX = largestRectangle.x + largestRectangle.width / 2;
                blueCenterY = largestRectangle.y + largestRectangle.height / 2;

                if(largestRectangle.height > largestRectangle.width){
                    blueOrientation = 0;
                    Imgproc.putText(
                            input,
                            "Blue: Vertical",
                            new Point(375, 25),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(255, 255, 0))
                    ;
                }
                else if (largestRectangle.width > largestRectangle.height){
                    blueOrientation = 180;
                    Imgproc.putText(
                            input,
                            "Blue: horizontal",
                            new Point(375, 25),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(255, 255, 0))
                    ;
                }


                Imgproc.rectangle(input, largestRectangle, new Scalar(100, 0, 255), 2);

                Imgproc.circle(input, new Point(blueCenterX, blueCenterY), 5, new Scalar(255, 0, 0), -1);



            }
        } else {
            blueCenterX = -1;
            blueCenterY = -1;
        }

        if (!yellowContours.isEmpty()) {
            double largestArea = 0;
            Rect largestRectangle = null;

            for (MatOfPoint contour : yellowContours) {
                Rect rectangle = Imgproc.boundingRect(contour);

                if (rectangle.area() > largestArea) {
                    largestArea = rectangle.area();
                    largestRectangle = rectangle;
                }
            }

            if (largestRectangle != null) {
                yellowCenterX = largestRectangle.x + largestRectangle.width / 2;
                yellowCenterY = largestRectangle.y + largestRectangle.height / 2;

                if(largestRectangle.height > largestRectangle.width){
                    yellowOrientation = 0;
                    Imgproc.putText(
                            input,
                            "Yellow: Vertical",
                            new Point(0, 25),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(0, 255, 255))
                    ;
                }
                else if (largestRectangle.width > largestRectangle.height){
                    yellowOrientation = 180;
                    Imgproc.putText(
                            input,
                            "Yellow: horizontal",
                            new Point(0, 25),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(0, 255, 255))
                    ;
                }



                Imgproc.rectangle(input, largestRectangle, new Scalar(255 , 255, 0), 2);

                Imgproc.circle(input, new Point(yellowCenterX, yellowCenterY), 3, new Scalar(255, 0, 0), -1);

            }

        } else {
            yellowCenterX = -1;
            yellowCenterY = -1;
        }

        return input;
    }

    public void getOrientation(){



    }


}
