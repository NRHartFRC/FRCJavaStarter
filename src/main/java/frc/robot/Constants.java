// Author: N. Rombach
// Last Updated : September 2023

package frc.robot;

public class Constants {

    // What does INITIALIZE mean? Answer: public static final int CONTROLLER_USB_PORT_ID
    // What does DECLARE mean? Answer:  = 0

    // Initialize and Declare the Controller USB Port
    public static final int CONTROLLER_USB_PORT_ID = 0; // USB port that controller is plugged into

    // Controller Input Axes
    public static final int LEFT_VERTICAL_JOYSTICK_AXIS = 1;
    public static final int RIGHT_VERTICAL_JOYSTICK_AXIS = 5;
    public static final int RIGHT_HORIZONTAL_JOYSTICK_AXIS = 2; // Arcade drive only
    public static final int Y_BUTTON = 4;

    // SparkMax CAN IDs
    public static final int LEFT_DRIVE_MOTOR_ID = 1;
    public static final int RIGHT_DRIVE_MOTOR_ID = 2;
    public static final int ARM_MOTOR_CAN_ID = 3;

    // Arm options
    public static final boolean ARM_MOTOR_INVERTED = false;

    // Drivetrain Constants
    public static final boolean DRIVE_INVERT_LEFT = false; // Whether to reverse the left drivetrain motors or not
    public static final boolean DRIVE_INVERT_RIGHT = true; // Whether to reverse the right drivetrain motors or not

    // Initialize and Declare the LED PWM Port(s)
    public static final int LED_PWM_ID = 4;
}
