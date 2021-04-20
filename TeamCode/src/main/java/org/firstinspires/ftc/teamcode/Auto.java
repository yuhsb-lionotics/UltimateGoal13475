package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto", group="LinearOpMode")

public class Auto extends DriveTrain {

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1680;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 0.5;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    static final double INCHES_FROM_GOAL = -90; //distance from starting  position to "C" wobble goal dropoff

    @Override
    public void runOpMode() {
        setup();
        waitForStart();
        runtime.reset();
        //encoderDrive(0.6, 10, 10, 10, 10, 10);
        //requestOpModeStop();

        // for moving wobble goal:
        encoderDrive(0.4, INCHES_FROM_GOAL-10, INCHES_FROM_GOAL, INCHES_FROM_GOAL-10, INCHES_FROM_GOAL, 10);

        // move back to park:
        encoderDrive(0.4, -12, -12, -12, -12, 10); //12 inches is just a guess, make adjustments if necessary

    }


        /* Let's quickly recap how to use encoder drive.
         *             !These are just motor values, not what encoder drive should be!
         *             case N: encodeDrive(1.0,1.0,1.0,1.0);
         *             case S: encodeDrive(-1.0,-1.0,-1.0,-1.0);
         *             case E: encodeDrive(1.0,-1.0,-1.0,1.0);
         *             case W: encodeDrive(-1.0,1.0,1.0,-1.0);
         *             case NE: encodeDrive(1.0,0,0,1.0);
         *             case NW: encodeDrive(0,1.0,1.0,0);
         *             case SE: encodeDrive(0,-1.0,-1.0,0);
         *             case SW: encodeDrive(-1.0,0,0,-1.0);
         *             case ROTATER: encodeDrive(1.0,-1.0,1.0,-1.0);
         *             case ROTATEL: encodeDrive(-1.0,1.0,-1.0,1.0);
         *             case STOP:encodeDrive(0,0,0,0);
         */

        //CODE GOES HERE:



}