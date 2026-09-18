// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotBase;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
   public static final boolean tuningMode = true;
    public static boolean disableHAL = false;
    

    public static void disableHAL() {
        disableHAL = true;
    }

    public static enum Mode {
        /** Running on a real robot. */
        REAL,

        /** Running a physics simulator. */
        SIM,

        /** Replaying from a log file. */
        REPLAY
    }

    public static final Mode simMode = Mode.SIM;
    public static final double controllerDeadband = 0.15;
    public static final double triggerPressedThreshold = 0.1;
    public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;
    public static final double robotWidthWithBumpers = Units.inchesToMeters(34.25);
  public static class OperatorConstants {
    public static final int flyWheelPort = 6;
    public static double wheelDiameterInInches = 3.0;
  }

  public static final class leftFlywheelFF {
  // Static friction constant
  public static final double kS = 0.50027;

  // Increase for a lower max speed, decrease for a higher max speed
  public static final double kV = 0.37; // Best kV for 300 RPM

  // Increase for less acceleration, decrease for more acceleration
  // More acceleration == bad for bang-bangF, good for PIDF
  // Less acceleration == good for bang-bangF, sometimes bad for PIDF
  // Adjust this based on your needs
  public static final double kA = 0.6;
}

}