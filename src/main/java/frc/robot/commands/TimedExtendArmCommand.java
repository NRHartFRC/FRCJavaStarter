// Author: N. Rombach
// Last Updated : September 2023

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class TimedExtendArmCommand extends CommandBase {

    private final ArmSubsystem m_armSubsystem;
    private final Timer timer = new Timer();
    private final double duration; // Duration in seconds
    private final double power; // Power to extend the arm (positive value)

    public TimedExtendArmCommand(ArmSubsystem armSubsystem, double duration, double power) {
        m_armSubsystem = armSubsystem;
        this.duration = duration;
        this.power = power;
        addRequirements(m_armSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        double currentTime = timer.get();
        if (currentTime < duration) {
            // Extend the arm with the specified power (positive value)
            m_armSubsystem.extendArm(power);
        } else {
            // Stop extending the arm when the duration is reached
            m_armSubsystem.stopArm();
        }
    }

    @Override
    public boolean isFinished() {
        // The command finishes when the timer exceeds the specified duration
        return timer.get() >= duration;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the arm when the command ends
        m_armSubsystem.stopArm();
    }
}

