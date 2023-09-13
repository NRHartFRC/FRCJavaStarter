// Author: N. Rombach
// Last Updated : September 2023

package frc.robot.commands; // Note: this is actaully subdirectory name too ;)

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

/* TimedDriveStraightCommand 
 * Drives straight using software-defined timer for a specified number of seconds
*/

public class TimedDriveStraightCommand extends CommandBase {

    private final DriveSubsystem m_drivetrainSubsystem;
    private final Timer timer = new Timer(); // instatntiate a timer constructor
    private final double duration; // how long we want to drive
    private final double driveRate; // how fast we want to drive

    // the time and duration are passed into this method (declared) when this method is called
    // double time, double driveRate (this is called initializing the variables)
    public TimedDriveStraightCommand(double time, double driveRate) {
        this.duration = time;
        this.driveRate = driveRate;
        this.m_drivetrainSubsystem = Robot.m_driveSubsystem;
        addRequirements(m_drivetrainSubsystem);
    }

    // Clears then starts the timer
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    
    // Called every time the scheduler runs while the command is scheduled
    @Override
    public void execute() {
        double currentTime = timer.get();
        if (currentTime < duration) {
            // Drive forward at the specified rate (adjust as needed)
            m_drivetrainSubsystem.drive(driveRate, driveRate);
        } else {
            // Stop driving when the duration is reached
            m_drivetrainSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        // The command finishes when the timer exceeds the specified duration
        return timer.get() >= duration;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drivetrain when the command ends
        m_drivetrainSubsystem.stop();
    }
}

