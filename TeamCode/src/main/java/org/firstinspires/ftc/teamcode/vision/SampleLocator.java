package org.firstinspires.ftc.teamcode.vision;

import android.graphics.fonts.Font;
import android.os.Environment;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class SampleLocator extends OpenCvPipeline {

    public int blueCenterX = -1, blueCenterY = -1, yellowCenterY = -1, yellowCenterX = -1;;;
    public int yellowOrientation, blueOrientation;
    public int blueHeight, blueWidth, yellowHeight, yellowWidth;

    public boolean SS = false;
    public String YellowStreamInfo = "";
    public String BlueStreamInfo = "";

    public String BlueSSInfo = "";
    public String YellowSSInfo = "";


    Mat hsv = new Mat();
    Mat blueMask = new Mat();
    Mat yellowMask = new Mat();
    Mat roi;

    double CameraMidPointX;
    double CameraMidPointY;

    public void detection(
            Mat input,
            String Color,
            Mat Mask,
            List<MatOfPoint> Contours,
            int width, int height,
            int CenterX, int CenterY,
            int Orientation,
            int labelX, int labelY) {

        if (!Contours.isEmpty()) {
            double largestArea = 0;
            Rect largestRectangle = null;

            for (MatOfPoint contour : Contours) {
                Rect rectangle = Imgproc.boundingRect(contour);

                width = rectangle.width;

                height = rectangle.height;

                if (width > 55 && width < 160 && height > 55 && height < 160) {

                    roi = new Mat(Mask, rectangle);

                    int Pixels = Core.countNonZero(roi);

                    int totalPixels = width * height;

                    double Percentage = (double) Pixels / totalPixels * 100.0;

                    int difference = Math.abs(width - height);

                    boolean square = difference < 28.5;

                    boolean tooMuch = Percentage > 63;

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
                CenterX = largestRectangle.x + largestRectangle.width / 2;
                CenterY = largestRectangle.y + largestRectangle.height / 2;

                height = largestRectangle.height;
                width = largestRectangle.width;

                if(largestRectangle.height > largestRectangle.width){
                    Orientation = 0;
                    Imgproc.putText(
                            input,
                            Color + ": Vertical",
                            new Point(labelX, labelY),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(255, 255, 0))
                    ;
                }
                else if (largestRectangle.width > largestRectangle.height){
                    Orientation = 180;
                    Imgproc.putText(
                            input,
                            Color + ": Horizontal",
                            new Point(labelX, labelY),
                            Imgproc.FONT_HERSHEY_DUPLEX,
                            0.85,
                            new Scalar(255, 255, 0))
                    ;
                }


                Imgproc.rectangle(input, largestRectangle, new Scalar(100, 0, 255), 2);
                Imgproc.circle(input, new Point(CenterX, CenterY), 4, new Scalar(255, 0, 0), -1);
            }
        } else {
            CenterX = -1;
            CenterY = -1;
        }

        String ssInfo1 = Color + " height: " + height + " | "
                + Color + " Width: " + width + " | "
                + Color + " X: " + CenterX + " | "
                + Color + " Y: " + CenterY + " | "
                + Color + " Orientation: " + Orientation;

        if (Color.equals("Blue")) {
            BlueStreamInfo = ssInfo1;
        } else if (Color.equals("Yellow")) {
            YellowStreamInfo = ssInfo1;
        }
    }


    @Override
    public Mat processFrame(Mat input) {

        CameraMidPointX = (double) input.width() / 2;
        CameraMidPointY = (double) input.height() / 2;

        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Scalar lowerBlue = new Scalar(100, 150, 100);
        Scalar upperBlue = new Scalar(165, 255, 255);

        Scalar lowerYellow = new Scalar(20, 100, 100);
        Scalar upperYellow = new Scalar(55, 255, 255);

        Core.inRange(hsv, lowerBlue, upperBlue, blueMask);

        Core.inRange(hsv, lowerYellow, upperYellow, yellowMask);


        List<MatOfPoint> blueContours = new ArrayList<>();
        List<MatOfPoint> yellowContours = new ArrayList<>();
        Imgproc.findContours(blueMask, blueContours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.findContours(yellowMask, yellowContours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.circle(input, new Point( CameraMidPointX , CameraMidPointY), 6, new Scalar(0, 255, 0), -1);

        detection(input,
                "Blue", blueMask, blueContours,
                blueWidth, blueHeight,
                blueCenterX, blueCenterY, blueOrientation,
                375, 25

        );

        detection(input,
                "Yellow", yellowMask, yellowContours,
                yellowWidth, yellowHeight,
                yellowCenterX, yellowCenterY, yellowOrientation,
                0, 25
        );

        if(SS){
            String Path =  Environment.getExternalStorageDirectory().getPath() + System.currentTimeMillis() + ".png";

            Imgcodecs.imwrite(Path, input);

            YellowSSInfo = YellowStreamInfo;
            BlueSSInfo = BlueStreamInfo;


            SS = false;
        }

        return input;
    }

}
