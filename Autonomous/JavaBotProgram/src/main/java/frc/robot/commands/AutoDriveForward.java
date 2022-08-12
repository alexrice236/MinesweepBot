/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class AutoDriveForward extends PIDCommand {

  private double wheelRotations;
	private double travelDistance;
  private double Circumference = 6 * Math.PI;
  

  public AutoDriveForward(double distance) {
    super(0.5, 0, 0);

    requires(Robot.drivetrain);
    
    getPIDController().setAbsoluteTolerance(.1);
    getPIDController().setSetpoint(distance);
    
    
    // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    super.initialize();
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    getPIDController().enable();
  }
  
  
  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
      return getPIDController().onTarget();
  }

  
  @Override
  protected void end() {

    Robot.drivetrain.stopDrive();

  }

  @Override
  protected double returnPIDInput() {
      wheelRotations = (Robot.drivetrain.getLeftEncoderPosition() + Robot.drivetrain.getRightEncoderPosition()) / 8192;

        travelDistance = wheelRotations * Circumference;
        
      return travelDistance;
    }
  

  @Override
  protected void usePIDOutput(double output) {
    Robot.drivetrain.tankDrive(output, output);
  }
}
