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

    public int blueHeight;
    public int blueWidth;

    public int yellowHeight;
    public int yellowWidth;



    @Override
    public Mat processFrame(Mat input) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Scalar lowerBlue = new Scalar(100, 150, 100);
        Scalar upperBlue = new Scalar(150, 255, 255);

        Scalar lowerYellow = new Scalar(20, 100, 100);
        Scalar upperYellow = new Scalar(55, 255, 255);

        Mat blueMask = new Mat();
        Core.inRange(hsv, lowerBlue, upperBlue, blueMask);

        Mat yellowMask = new Mat();
        Core.inRange(hsv, lowerYellow, upperYellow, yellowMask);


        List<MatOfPoint> blueContours = new ArrayList<>();
        List<MatOfPoint> yellowContours = new ArrayList<>();
        Imgproc.findContours(blueMask, blueContours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.findContours(yellowMask, yellowContours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


        if (!blueContours.isEmpty()) {
            double largestArea = 0;
            Rect largestRectangle = null;

            for (MatOfPoint contour : blueContours) {
                Rect rectangle = Imgproc.boundingRect(contour);

                int width = rectangle.width;

                int height = rectangle.height;

                if (width > 55 && width < 160 && height > 55 && height < 160) {


                    Mat roi = new Mat(blueMask, rectangle);

                    int bluePixels = Core.countNonZero(roi);

                    int totalPixels = width * height;

                    double bluePercentage = (double) bluePixels / totalPixels * 100.0;


                    int difference = Math.abs(width - height);


                    boolean square = difference < 28.5;
                    boolean tooMuch = bluePercentage > 65.0;


                    if (square && tooMuch) {
                        continue;
                    }

                    if (rectangle.area() > largestArea) {
                        largestArea = rectangle.area();
                        largestRectangle = rectangle;
                    }
                }
            }

            if (largestRectangle != null) {
                blueCenterX = largestRectangle.x + largestRectangle.width / 2;
                blueCenterY = largestRectangle.y + largestRectangle.height / 2;

                blueHeight = largestRectangle.height;
                blueWidth = largestRectangle.width;

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
            double YellowlargestArea = 0;
            Rect YellowlargestRectangle = null;

            for (MatOfPoint contour : yellowContours) {
                Rect Yellowrectangle = Imgproc.boundingRect(contour);

                int width = Yellowrectangle.width;

                int height = Yellowrectangle.height;

                if (width > 55 && width < 160 && height > 55 && height < 160) {


                    Mat roi = new Mat(yellowMask, Yellowrectangle);

                    int yellowPixels = Core.countNonZero(roi);

                    int totalPixels = width * height;

                    double yellowPercentage = (double) yellowPixels / totalPixels * 100.0;


                    int difference = Math.abs(width - height);


                    boolean square = difference < 28.5;
                    boolean tooMuch = yellowPercentage > 65.0;


                    if (square && tooMuch) {
                        continue;
                    }

                    if (Yellowrectangle.area() > YellowlargestArea) {
                        YellowlargestArea = Yellowrectangle.area();
                        YellowlargestRectangle = Yellowrectangle;
                    }
                }

            }

            if (YellowlargestRectangle != null) {
                yellowCenterX = YellowlargestRectangle.x + YellowlargestRectangle.width / 2;
                yellowCenterY = YellowlargestRectangle.y + YellowlargestRectangle.height / 2;

                yellowHeight = YellowlargestRectangle.height;
                yellowWidth = YellowlargestRectangle.width;

                if(YellowlargestRectangle.height > YellowlargestRectangle.width){
                    yellowOrientation = 0;
                    Imgproc.putText(
                            input,
                            "Yellow: Vertical",
                            new Point(0, 25),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(255, 255, 0))
                    ;
                }
                else if (YellowlargestRectangle.width > YellowlargestRectangle.height){
                    yellowOrientation = 180;
                    Imgproc.putText(
                            input,
                            "Yellow: horizontal",
                            new Point(0, 25),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(255, 255, 0))
                    ;
                }

                Imgproc.rectangle(input, YellowlargestRectangle, new Scalar(100, 255, 255), 2);

                Imgproc.circle(input, new Point(yellowCenterX, yellowCenterY), 5, new Scalar(255, 0, 0), -1);

            }
        } else {
            yellowCenterX = -1;
            yellowCenterY = -1;
        }

        return input;
    }

}
