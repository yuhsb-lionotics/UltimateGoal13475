
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


public class driveTrain extends LinearOpMode {
    private DcMotor FR;
    private DcMotor FL;
    private DcMotor BR;
    private DcMotor BL;

    HardwareMap hwMap = null;
    private ElapsedTime time = new ElapsedTime();

    public boolean isBlue = false;

    private static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    private static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);


    @Override
    public void runOpMode(){

    }

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        if(isBlue){
            FL = hardwareMap.dcMotor.get("Fl");
            FR = hardwareMap.dcMotor.get("Fr");
            BL = hardwareMap.dcMotor.get("Bl");
            BR = hardwareMap.dcMotor.get("Br");
        } else {
            FR = hardwareMap.dcMotor.get("Fl");
            FL = hardwareMap.dcMotor.get("Fr");
            BR = hardwareMap.dcMotor.get("Bl");
            BL = hardwareMap.dcMotor.get("Br");
        }

    }

    public void driveForward(double power){
        FR.setPower(power);
        FL.setPower(power);
        BR.setPower(power);
        BL.setPower(power);
    }

    public void turnLeft(double power){
        FR.setPower(power);
        FL.setPower(-power);
        BR.setPower(power);
        BL.setPower(-power);
    }

    public void turnRight(double power){
        FR.setPower(-power);
        FL.setPower(power);
        BR.setPower(-power);
        BL.setPower(power);
    }
    public void driveBackword(double power){
        FR.setPower(-power);
        FL.setPower(-power);
        BL.setPower(-power);
        BR.setPower(-power);

    }
    void setup() {
        FR = hardwareMap.get(DcMotor.class, "Fr");
        FL = hardwareMap.get(DcMotor.class, "Fl");
        BR = hardwareMap.get(DcMotor.class, "Br");
        BL = hardwareMap.get(DcMotor.class, "Bl");

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

            FR.setTargetPosition(newFRTarget);
            FL.setTargetPosition(newFLTarget);
            BL.setTargetPosition(newBLTarget);
            BR.setTargetPosition(newBRTarget);

            //Turn on RUN_TO_POSITION
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);



            // reset timeout and start motion
            //runtime.reset();
            FL.setPower(Math.abs(speed));
            FR.setPower(Math.abs(speed));
            BL.setPower(Math.abs(speed));
            BR.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy())){
                // Display it for the driver
                telemetry.addData("Path1", "Running to %7d :%7d :%7d :%7d", newFLTarget, newFRTarget, newBLTarget, newBRTarget);
                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
                        FL.getCurrentPosition(),
                        FR.getCurrentPosition(),
                        BL.getCurrentPosition(),
                        BR.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion
            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);

            // Turn off RUN_TO_POSITION
            FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
//More to Come!

}


