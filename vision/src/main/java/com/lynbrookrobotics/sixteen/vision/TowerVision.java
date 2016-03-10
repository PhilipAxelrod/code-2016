package com.lynbrookrobotics.sixteen.vision;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;

public class TowerVision {
  public static Mat detectHighGoal(Mat image) {
    Mat destination = new Mat();
    Imgproc.cvtColor(image, destination, Imgproc.COLOR_BGR2HSV);

    Mat mask = new Mat();
    Core.inRange(destination, new Scalar(80, 0, 100), new Scalar(96, 255, 255), mask);

    ArrayList<MatOfPoint> contours = new ArrayList<>();
    Mat matHeirarchy = new Mat();

    Mat out = new Mat();
    Core.bitwise_and(destination, destination, out, mask);
    Imgproc.findContours(mask, contours, matHeirarchy, Imgproc.RETR_EXTERNAL,
        Imgproc.CHAIN_APPROX_SIMPLE);

    Rect biggest = new Rect(0, 0, 0, 0);
    for (MatOfPoint matOfPoint: contours) {
      Rect rec = Imgproc.boundingRect(matOfPoint);

      if (rec.area() > biggest.area() && rec.width > rec.height && rec.area() > 600) {
        biggest = rec;
      }
    }

    Imgproc.rectangle(destination, biggest.br(), biggest.tl(), new Scalar(255, 255, 255));

    return destination;
  }
}