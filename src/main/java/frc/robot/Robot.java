// Author: N. Rombach
// Last Updated : September 2023

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;

// Autonomous scheduler
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.autonomous.Auton_Default;

// Use the Subsystems and Commands
import frc.robot.commands.DriveCommand;
import frc.robot.commands.POVMotorCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;

// switch directions with Y BUTTON
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.InstantCommand;


public class Robot extends TimedRobot {

    // Create a chooser to select an autonomous command
    CommandBase m_autonomousCommand;
    SendableChooser<CommandBase> autonChooser = new SendableChooser<CommandBase>();

    // Instantiate our controller at the specified USB port
    public static final GenericHID controller = new GenericHID(Constants.CONTROLLER_USB_PORT_ID);

    // Create a POV trigger for your controller
    private Trigger povTrigger;

    // create instance of POVMotorCommand as a class-level variable
    private POVMotorCommand povMotorCommand;

    // Subsystem constructors (constructors have object inside that you can access using constructor.*)
    public static final DriveSubsystem m_driveSubsystem = new DriveSubsystem(); // Drivetrain subsystem
    public static final ArmSubsystem m_armSubsystem = new ArmSubsystem(); //arm subsystem
    
    @Override
    public void robotInit() {

        configureButtonBindings(); // Bind our commands to physical buttons on a controller

        // Set the DriveCommand to execute by default
        m_driveSubsystem.setDefaultCommand(new DriveCommand());

        // setup POV for ancillary motor that uses ArmSubsystem
        povMotorCommand = new POVMotorCommand(m_armSubsystem, controller, povTrigger);

        // Add our Autonomous Routines to the chooser //
		autonChooser.setDefaultOption("Default Auto", new Auton_Default());
        // autonChooser.addOption("Booger", new Auton_Custom1(m_armSubsystem));
		// autonChooser.addOption("Flicker", new Auton_Custom2(m_armSubsystem));
        SmartDashboard.putData("Auto Mode", autonChooser);

        // Create instances of your autonomous commands
        Auton_Default defaultAuton = new Auton_Default();
        // Auton_Custom1 customAuton1 = new Auton_Custom1(m_armSubsystem);
        // Auton_Custom2 customAuton2 = new Auton_Custom2(m_armSubsystem);

        // Configure the SendableChooser to select an autonomous command
        autonChooser.setDefaultOption("Default Auto", defaultAuton);
        // autonChooser.addOption("Custom Auto 1", customAuton1);
        // autonChooser.addOption("Custom Auto 2", customAuton2);

        // Put the SendableChooser on the SmartDashboard
        SmartDashboard.putData("Auto Mode", autonChooser);
    }

    @Override
    public void robotPeriodic() {
      // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
      // commands, running already-scheduled commands, removing finished or interrupted commands,
      // and running subsystem periodic() methods. This must be called from the robot's periodic
      // block in order for anything in the Command-based framework to work.
      CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {

        System.out.println("AUTONOMOUS MODE STARTED");
        m_autonomousCommand = autonChooser.getSelected();

        // schedule the selected autonomous command
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
        povMotorCommand.execute();
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }

    /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or onse of its subclasses ({@link edu.wpi.first.wpilibj.Joystick} 
   * or {@link XboxController}), and then passing it to a {@link edu.wpi.first.wpilibj2.command.button.Trigger}.
   */
  private void configureButtonBindings() {
    // Drivetrain Controls //
    new Trigger(() -> controller.getRawButton(Constants.Y_BUTTON)).onTrue(new InstantCommand(() -> m_driveSubsystem.toggleDirection()));
    // Create a POV trigger for your controller
    povTrigger = new Trigger(() -> controller.getPOV() != -1);
    new POVMotorCommand(m_armSubsystem, controller, povTrigger);
  }
}
