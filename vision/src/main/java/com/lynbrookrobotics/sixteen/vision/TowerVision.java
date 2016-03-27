package com.lynbrookrobotics.sixteen.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Optional;

import akka.japi.tuple.Tuple3;

// java -Djava.library.path=/usr/local/share/OpenCV/java -jar vision-ly-0.1-SNAPSHOT.jar
public class TowerVision {
  private static int H_LOW_THRESHOLD = 150;

  public static Optional<Tuple3<Mat, Double, Double>> detectHighGoal(Mat image) {
    Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2HSV);

    Mat mask = new Mat();
    Core.inRange(image, new Scalar(0, 0, H_LOW_THRESHOLD), new Scalar(255, 255, 255), mask);

    ArrayList<MatOfPoint> contours = new ArrayList<>();

    Core.bitwise_and(image, image, image, mask);
    Imgproc.findContours(mask, contours, mask, Imgproc.RETR_EXTERNAL,
        Imgproc.CHAIN_APPROX_SIMPLE);

    mask.release();
    Rect biggest = null;
    for (MatOfPoint matOfPoint: contours) {
      Rect rec = Imgproc.boundingRect(matOfPoint);

      if ((biggest == null && rec.width > rec.height) || (rec.area() < 100000 && rec.area() > biggest.area())) {
        biggest = rec;
      }
    }

    if (biggest != null) {
      Imgproc.rectangle(image, biggest.br(), biggest.tl(), new Scalar(255, 255, 255));

      return Optional.of(new Tuple3<>(image, (biggest.tl().x + biggest.br().x) / 2, biggest.br().y));
    } else {
      return Optional.empty();
    }
  }
}
