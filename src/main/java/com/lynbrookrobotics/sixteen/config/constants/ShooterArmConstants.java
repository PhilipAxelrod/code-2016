package com.lynbrookrobotics.sixteen.config.constants;

public class ShooterArmConstants {
  public static final double FORWARD_LIMIT = 54.31585200092063;
  public static final double REVERSE_LIMIT = 40.501635099161405;
  public static final double STOWED_THRESHOLD = 20;

  public static final double MAX_SPEED = 0.4;

  // TODO: experimentally determine PID factors
  public static final double P_GAIN = 0.0d;

  public static final double I_GAIN = 0.0d;
  public static final int I_MEMORY = 1;
  public static final int SHOOTER_ARM_ERROR = 3;

  // TODO: experimentally determine conversion factor
  public static final double CONVERSION_FACTOR = 0.0d;

  public static final double SHOOT_ANGLE = 0;

  public static final double STOWED_SETPOINT = 0.0;

  public static final double TRANSPORT_SETPOINT = 0.0;
}
