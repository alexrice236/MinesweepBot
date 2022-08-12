/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoScan;
import frc.robot.subsystems.Drivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {



  public static Drivetrain drivetrain;
  public static DigitalInput detector;
  public static OI oi;

  Command autonomousCommand;


  @Override
  public void robotInit() {
    oi = new OI();
    drivetrain = new Drivetrain();
    detector = new DigitalInput(RobotMap.detector);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Front Range", Robot.drivetrain.frontUltrasonic.getRangeInches());
    SmartDashboard.putNumber("Side Range", Robot.drivetrain.sideUltrasonic.getRangeInches());
    SmartDashboard.putBoolean("MetalDetected", Robot.detector.get());
  }


  @Override
  public void autonomousInit() {
    autonomousCommand = new AutoScan();
  }


  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Front Range", Robot.drivetrain.frontUltrasonic.getRangeInches());
    SmartDashboard.putNumber("Side Range", Robot.drivetrain.sideUltrasonic.getRangeInches());
    drivetrain.writeToDashboard();
    }



  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    drivetrain.writeToDashboard();
  }

  
  @Override
  public void testPeriodic() {
  }
}
