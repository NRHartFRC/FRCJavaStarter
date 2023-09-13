// Author: N. Rombach
// Last Updated : September 2023

package frc.robot.commands; // Note: this is actaully subdirectory name too ;)

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

/* TimedDriveBackwardCommand 
 * Drives straight back using software-defined timer for a specified number of seconds
*/
public class TimedDriveBackwardCommand extends CommandBase {

    private final DriveSubsystem m_drivetrainSubsystem;
    private final Timer timer = new Timer();
    private final double duration;
    private final double driveRate;

    public TimedDriveBackwardCommand(double time, double driveRate) {
        this.duration = time;
        this.driveRate = driveRate;
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
            // Drive backward at the specified rate
            m_drivetrainSubsystem.drive(-driveRate, -driveRate); // Reverse the driveRate to move backward
        } else {
            // Stop driving when the duration is reached
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
