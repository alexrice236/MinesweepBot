/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoysticks;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {

  private WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(RobotMap.leftBackMotor);
	private WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor);
	private WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(RobotMap.rightBackMotor);
	private WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor);
	
	private SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftBackMotor, leftFrontMotor);
	private SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightBackMotor, rightFrontMotor);
	
  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);
  
  public AHRS gyro;

  public double width = 12;
  public double length = 15;

  public Ultrasonic frontUltrasonic = new Ultrasonic(0, 1);
  public Ultrasonic sideUltrasonic = new Ultrasonic(2, 3);
	

  public Drivetrain() {
        
    gyro = new AHRS(SPI.Port.kMXP);
        
    configureTalons();
        
    drive = new DifferentialDrive(leftMotors, rightMotors);
      
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoysticks());
  }

  public void configureTalons() {
		
		leftFrontMotor.configClosedloopRamp(.1, 0);
		leftBackMotor.configClosedloopRamp(.1, 0);
		rightFrontMotor.configClosedloopRamp(.1, 0);
		rightBackMotor.configClosedloopRamp(.1, 0);
		
		leftBackMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    rightBackMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    
    leftBackMotor.setSelectedSensorPosition(0);
    rightBackMotor.setSelectedSensorPosition(0);
    
  }

  public double getAngle() {
		return gyro.getAngle();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public double getLeftEncoderPosition() {
		return leftBackMotor.getSelectedSensorPosition(0) * -1;
	}
	public double getRightEncoderPosition() {
		return rightBackMotor.getSelectedSensorPosition(0);
  }
  

	public double getLeftEncoderVelocity() {
		return leftBackMotor.getSelectedSensorVelocity(0);
	}
	public double getRightEncoderVelocity() {
		return rightBackMotor.getSelectedSensorVelocity(0);
  }

  public void writeToDashboard() {
		SmartDashboard.putNumber("Right Sensor (Encoder)", getRightEncoderPosition());
		SmartDashboard.putNumber("Left Sensor (Encoder)", getLeftEncoderPosition());
		SmartDashboard.putNumber("Gyro", getAngle());
	}



  public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed, true);	
  }
  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }
  


  public double getLength(){
    length = length - 1;
    return length;

  }
  public double getWidth(){
    width = width - 1;
    return width;
  }

  public boolean ifObjectFront(){
    return frontUltrasonic.getRangeInches() < 18;
  }

  public boolean ifObjectSide(){
    return sideUltrasonic.getRangeInches() < 18;
  }



  public void stopDrive() {
		drive.tankDrive(0, 0);
	}


  
}
