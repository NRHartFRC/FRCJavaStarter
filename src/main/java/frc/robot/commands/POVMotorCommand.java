// Author: N. Rombach
// Last Updated : September 2023

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class POVMotorCommand extends CommandBase {
    private final ArmSubsystem armSubsystem;
    private final GenericHID controller;

    public POVMotorCommand(ArmSubsystem armSubsystem, GenericHID controller, Trigger povTrigger) {
        this.armSubsystem = armSubsystem;
        this.controller = controller;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        // Optional: Add any initialization code here
    }

    @Override
    public void execute() {
        int povDirection = controller.getPOV(); // Get the POV angle in degrees

        if (povDirection == 0) {
            // Forward command
            armSubsystem.extendArm(0.5); // Set motor speed to 0.5 (adjust as needed)
        } else if (povDirection == 180) {
            // Reverse command
            armSubsystem.extendArm(-0.5); // Set motor speed to -0.5 for reverse (adjust as needed)
        } else {
            // Stop the motor if POV is not pressed
            armSubsystem.stopArm();
        }
        
    }

    @Override
    public boolean isFinished() {
        // This command will run continuously until interrupted
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the arm when the command ends
        armSubsystem.stopArm();
    }
}
