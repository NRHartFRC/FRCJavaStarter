// Author: N. Rombach
// Last Updated : September 2023

/*Autonomous Mode (Default)
 * Basic Autonomous Routine. It drives forward for 2 seconds, turns around 180 degrees, and then drives back for 2 seconds. */
package frc.robot.commands.autonomous;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.TimedDriveBackwardCommand;
import frc.robot.commands.TimedDriveStraightCommand;
import frc.robot.commands.TimedRotateCCWCommand;
import frc.robot.commands.TimedExtendArmCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class Auton_Default extends SequentialCommandGroup {
    public Auton_Default() {
    // Acknowledge Mode
    SmartDashboard.putString("Auton Mode ", "Default");

    // list commands sequentially
    addCommands(new TimedDriveStraightCommand(3, 0)); // delay

    addCommands(new TimedDriveStraightCommand(1, 0.1)); // could these be negative?

    addCommands(new TimedExtendArmCommand(Robot.m_armSubsystem, 1, 0.1));

    addCommands(new TimedDriveBackwardCommand(1, 0.1));

    addCommands( new TimedRotateCCWCommand(1, -0.1));
  
    // addCommands(new TimedArmExtendCommand(3, 1));
    // addCommands(new TimedArmDownCommand(2, 0.3));
    // addCommands(new ToggleArmCommand(true));
    }
}

/*Need to create a new command?
For the new command (.java file) you need to create, follow the method structure: 
public class myNewCommand extends 
    - public void initialize() {} 
    - public void execute() {} 
    - public void isFinished() {} 
    - public void end() {}
Still have questions? Go to README.md file and find commands.html link under Commands.
*/
