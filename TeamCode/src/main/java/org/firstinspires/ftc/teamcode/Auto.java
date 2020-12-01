package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto", group="LinearOpMode")

public class Auto extends LinearOpMode{
    private DcMotor FR = null;
    private DcMotor FL = null;
    private DcMotor BR = null;
    private DcMotor BL = null;

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1220;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);


    @Override
    public void runOpMode() {
        setup();
        waitForStart();

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

    private void setup() {
        FR = hardwareMap.get(DcMotor.class, "fr");
        FL = hardwareMap.get(DcMotor.class, "fl");
        BR = hardwareMap.get(DcMotor.class, "br");
        BL = hardwareMap.get(DcMotor.class, "bl");

        FR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderDrive(double speed, double FLin, double FRin, double BLin, double BRin,
                             double timeoutS) {
        int newFRTarget;
        int newFLTarget;
        int newBRTarget;
        int newBLTarget;

        //ensure opmode is still active
        if(opModeIsActive()) {

            //Determine new target position and pass it to motor controller
            newFRTarget = FL.getCurrentPosition() + (int) (FRin * COUNTS_PER_INCH);
            newFLTarget = FR.getCurrentPosition() + (int) (FLin * COUNTS_PER_INCH);
            newBRTarget = BL.getCurrentPosition() + (int) (BRin * COUNTS_PER_INCH);
            newBLTarget = BR.getCurrentPosition() + (int) (BLin * COUNTS_PER_INCH);

            //Turn on RUN_TO_POSITION
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset timeout and start motion
            runtime.reset();
            FR.setPower(Math.abs(speed));
            FL.setPower(Math.abs(speed));
            BR.setPower(Math.abs(speed));
            BL.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (FR.isBusy() && FL.isBusy() && BR.isBusy() && BL.isBusy())){
                // Display it for the driver
                telemetry.addData("Path1", "Running to %7d :%7d :%7d :%7d", newFLTarget, newFRTarget, newBLTarget, newBRTarget);
                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
                        FR.getCurrentPosition(),
                        FL.getCurrentPosition(),
                        BR.getCurrentPosition(),
                        BL.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion
            FR.setPower(0);
            FL.setPower(0);
            BR.setPower(0);
            BL.setPower(0);

            // Turn off RUN_TO_POSITION
            FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
