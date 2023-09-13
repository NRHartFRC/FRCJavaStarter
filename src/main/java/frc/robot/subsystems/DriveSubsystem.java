// Author: N. Rombach
// Last Updated : September 2023

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  
    // Initialize Drivetrain Motor Controllers
    private CANSparkMax m_leftMotor;
    private CANSparkMax m_rightMotor;

    // A 'direction' variable is initialized and declared here because we wanted to be able to flip which side of the robot is the 'front'
    private int direction = 1;

  // Create a chooser for selecting the desired drive speed scale
  SendableChooser<Double> driveScaleChooser = new SendableChooser<Double>();
  public double CURRENT_DRIVE_SCALE;

  public DriveSubsystem() {
    // Instantiate the Drivetrain motor controllers (constructors)
    m_leftMotor = new CANSparkMax(Constants.LEFT_DRIVE_MOTOR_ID, MotorType.kBrushed);
    m_rightMotor = new CANSparkMax(Constants.RIGHT_DRIVE_MOTOR_ID, MotorType.kBrushed);

    // Reverse some of the motors if needed
    m_leftMotor.setInverted(Constants.DRIVE_INVERT_LEFT); //this is false in Constants.java
    m_rightMotor.setInverted(Constants.DRIVE_INVERT_RIGHT); //this is true in Constants.java

    // Drive Scale Options
    SmartDashboard.putString("Drivetrain ", "Select Power");
    driveScaleChooser.addOption("100%", 1.0);
    driveScaleChooser.setDefaultOption("75%", 0.75);
    driveScaleChooser.addOption("50%", 0.5);
    driveScaleChooser.addOption("25%", 0.25);

    SmartDashboard.putData("Drivetrain Speed", driveScaleChooser);
  }

  /* Set power to the drivetrain motors */
  public void drive(double leftPercentPower, double rightPercentPower) {
    m_leftMotor.set(direction * leftPercentPower);
    m_rightMotor.set(direction * rightPercentPower);
  }
  public void stop() {
    drive(0, 0);
  }

  // These methods allow us to flip which side is considered the "front" of the robot
  public void setDirection(DIRECTION direction) {
    this.direction = direction.direction;
  }
  public void toggleDirection() {
    this.direction *= -1;
  }

  @Override
  public void periodic() {

    CURRENT_DRIVE_SCALE = driveScaleChooser.getSelected(); // Continously update the desired drive scale

  }

  // Helps with flipping which side is considered the "front" of the robot
  enum DIRECTION {
    INTAKE_FRONT(1),
    EXTENDER_FRONT(-1);

    public int direction;

    DIRECTION(int direction) {
      this.direction = direction;
    }
  }
}