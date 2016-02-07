package com.lynbrookrobotics.sixteen.tasks.drivetrain;

import com.lynbrookrobotics.potassium.tasks.FiniteTask;
import com.lynbrookrobotics.sixteen.components.drivetrain.Drivetrain;
import com.lynbrookrobotics.sixteen.components.drivetrain.TurnByAngleController;
import com.lynbrookrobotics.sixteen.config.RobotHardware;

public class TurnByAngle extends FiniteTask {
  RobotHardware hardware;
  Drivetrain drivetrain;

  double angle;

  TurnByAngleController controller;

  public TurnByAngle(double angle, RobotHardware hardware, Drivetrain drivetrain) {
    this.hardware = hardware;
    this.drivetrain = drivetrain;

    this.angle = angle;
  }

  @Override
  protected void startTask() {
    controller = new TurnByAngleController(angle, hardware);
    drivetrain.setController(controller);
  }

  int goodTicks = 0;

  @Override
  protected void update() {
    System.out.println("LEFT: " + controller.difference());
    if (controller.difference() < 0.25) {
      goodTicks++;
      if (goodTicks == 100) {
        finished();
      }
    } else {
      goodTicks = 0;
    }
  }

  @Override
  protected void endTask() {
    drivetrain.resetToDefault();
    controller = null;
  }
}
