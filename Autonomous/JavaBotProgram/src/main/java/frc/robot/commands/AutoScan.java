/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.AutoDriveForward;



public class AutoScan extends CommandGroup {
  public AutoScan() {
    requires(Robot.drivetrain);

    Robot.drivetrain.configureTalons();

    while(Robot.drivetrain.getWidth() > 0 && Robot.drivetrain.getLength() > 0){
    /*if(Robot.drivetrain.sideUltrasonic.getRangeInches() < 18){
      AvoidObject object = new AvoidObject();
      object.close();
    }else if(Robot.detector.get()){
      Robot.drivetrain.stopDrive();
      new AutoDriveForward(2);
      new MarkMine();
    }else{*/

    addSequential(new AutoDriveForward(Robot.drivetrain.getWidth()));
    addSequential(new GyroTurn(90));
    addSequential(new AutoDriveForward(Robot.drivetrain.getLength()));
    addSequential(new GyroTurn(90));  

    }
  }
  }
