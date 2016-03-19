package com.lynbrookrobotics.sixteen.config.constants;

import static com.lynbrookrobotics.sixteen.config.constants.ConfigToConstants.*;

public class ShooterArmConstants {
  static {
    loadInto(
        ShooterArmConstants.class,
        RobotConstants.config.getConfig("shooter-arm")
    );
  }

  @ConfigLoaded public final static double FORWARD_LIMIT = config();
  @ConfigLoaded public final static double FORWARD_INTAKE_STOWED_LIMIT = config();
  @ConfigLoaded public final static double REVERSE_LIMIT = config();
  @ConfigLoaded public final static double STOWED_THRESHOLD = config();
  @ConfigLoaded public final static double LOW_THRESHOLD = config();

  @ConfigLoaded public final static double MAX_SPEED = config();

  public static final double P_GAIN = 1.2 / 90D;

  public static final double I_GAIN = 0.0d;
  public static final int I_MEMORY = 1;
  public static final int SHOOTER_ARM_ERROR = 5;

  public static final double SHOOT_ANGLE = REVERSE_LIMIT;

  public static final double STOWED_SETPOINT = FORWARD_LIMIT - 2;

  @ConfigLoaded public final static double TRANSPORT_SETPOINT = config();
}
