// Author: N. Rombach
// Last Updated : September 2023

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

public class TimedRotateCCWCommand extends CommandBase {

    private final DriveSubsystem m_drivetrainSubsystem;
    private final Timer timer = new Timer();
    private final double duration;
    private final double rotateRate;

    public TimedRotateCCWCommand(double time, double rotateRate) {
        this.duration = time;
        this.rotateRate = rotateRate;
        this.m_drivetrainSubsystem = Robot.m_driveSubsystem;
        addRequirements(m_drivetrainSubsystem);
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
            // Rotate counterclockwise at the specified rate
            m_drivetrainSubsystem.drive(rotateRate, -rotateRate); // Adjust as needed
        } else {
            // Stop rotation when the duration is reached
            m_drivetrainSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= duration;
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.stop();
    }
}
