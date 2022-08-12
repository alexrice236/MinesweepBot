/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AvoidObject extends Command {
  public AvoidObject() {
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    GyroTurn turn = new GyroTurn(90);
    Robot.drivetrain.tankDrive(-0.25, 0.25);
    turn.close();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.drivetrain.ifObjectSide();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    AutoScan resume = new AutoScan();
    resume.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
