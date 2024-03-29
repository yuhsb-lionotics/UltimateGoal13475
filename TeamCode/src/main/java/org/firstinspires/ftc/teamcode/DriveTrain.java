
/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveTrain extends LinearOpMode {
    protected DcMotor frontLeft, backLeft, frontRight, backRight, launcher, conveyor;
    private final ElapsedTime runtime = new ElapsedTime();

    public boolean isBlueAlliance() { return true; } //Set to false if red alliance

    public static final double COUNTS_PER_MOTOR_REV = 420;    // eg: NEVEREST60 Motor Encoder
    public static final double DRIVE_GEAR_REDUCTION = 0.5;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14);
    private static final double conveyorDiameter = 2.5;
    private static final double conveyorCountsPerInch = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (conveyorDiameter * Math.PI);

    @Override
    public void runOpMode() { }

    public void setup() {

        frontLeft = hardwareMap.dcMotor.get("Fl");
        backLeft = hardwareMap.dcMotor.get("Bl");
        frontRight = hardwareMap.dcMotor.get("Fr");
        backRight = hardwareMap.dcMotor.get("Br");
        conveyor = hardwareMap.dcMotor.get("conveyor");
        launcher = hardwareMap.dcMotor.get("launcher");



        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        //Set motors to break whenever they are stopped
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        conveyor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void drive(double leftPower, double rightPower) {
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
    }
    public void driveForward(double power) {
        drive(power, power);
    }




    //Set each motor to drive a certain distance.
    //maxPower is the greatest absolute value of the power for any of the motors.
    //frInches, etc. can be positive or negative, but not 0.
    //timeoutS is the maximum number of seconds to run the OpMode before a hard stop.
    //The point is to avoid an infinite loop.
    //It should be much higher than the actual length of time it should take, e.g. 10.
    protected void encoderDrive(double maxPower, // 0 < maxPower <= 1
                                double frInches, double flInches, double brInches, double blInches, // + or -
                                double timeoutS) {
        int newFRTarget;
        int newFLTarget;
        int newBLTarget;
        int newBRTarget;

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFRTarget = frontRight.getCurrentPosition()     + (int) (frInches * COUNTS_PER_INCH);
            newFLTarget = frontLeft.getCurrentPosition()     + (int) (flInches * COUNTS_PER_INCH);
            newBLTarget = backLeft.getCurrentPosition()     + (int) (blInches * COUNTS_PER_INCH);
            newBRTarget = backRight.getCurrentPosition()     + (int) (brInches * COUNTS_PER_INCH);










            //Determine wheel powers
            //Power for each wheel is proportional to the maximum power and distance travelled
            double maxInches = Math.max( Math.max(Math.abs(frInches), Math.abs(flInches)) ,
                    Math.max(Math.abs(brInches), Math.abs(blInches)) );
            double powerFR = maxPower * frInches / maxInches;
            double powerFL = maxPower * flInches / maxInches;
            double powerBR = maxPower * brInches / maxInches;
            double powerBL = maxPower * blInches / maxInches;

            telemetry.addData("EncoderDrivePowerFR", powerFR);
            telemetry.addData("EncoderDrivePowerFL", powerFL);
            telemetry.addData("EncoderDrivePowerBR", powerBR);
            telemetry.addData("EncoderDrivePowerBL", powerBL);
            telemetry.update();
            // reset the timeout time and start motion.


            frontRight.setTargetPosition(newFRTarget);
            frontLeft.setTargetPosition(newFLTarget);
            backLeft.setTargetPosition(newBLTarget);
            backRight.setTargetPosition(newBRTarget);
            // Turn On RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            frontRight.setPower(Math.abs(maxPower));
            frontLeft.setPower(Math.abs(maxPower));
            backLeft.setPower(Math.abs(maxPower));
            backRight.setPower(Math.abs(maxPower));

            // Display it for the driver.

            telemetry.addData("fr ", frontRight.isBusy());
            telemetry.addData("fl ", frontLeft.isBusy());
            telemetry.addData("br ", backRight.isBusy());
            telemetry.addData("bl ", backLeft.isBusy());

            telemetry.update();
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER/ANY motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH/ALL motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontRight.isBusy() && frontLeft.isBusy() && backLeft.isBusy() && backRight.isBusy())){
                // Display it for the driver.
                telemetry.addData("fr ", frontRight.isBusy());
                telemetry.addData("fl ", frontLeft.isBusy());
                telemetry.addData("br ", backRight.isBusy());
                telemetry.addData("bl ", backLeft.isBusy());

                telemetry.update();
            }
            //Display the time elapsed
            telemetry.addData("Encoder Drive", "Finished in %.2f s/%f", runtime.seconds(), timeoutS);
            telemetry.update();

            // Stop all motion;
            frontRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    //this is me to the best of my ability trying to rewrite the encoderDrive above but for the launcher, bare with me here

    public void conveyorDrive(double moveInches , double power) {
        //the precise number of inches needed to be moved every time. Needs testing to approximate.

        int newConveyorTarget = (int) (conveyor.getCurrentPosition() + conveyorCountsPerInch * moveInches);
        telemetry.addData("conveyorTarget", newConveyorTarget);
        telemetry.update();
        conveyor.setTargetPosition(newConveyorTarget);
        conveyor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        conveyor.setPower(power);

        while(opModeIsActive() && conveyor.isBusy()) {
            sleep(10);
        }

        conveyor.setPower(0);
        conveyor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    public void setLauncherPower(double power) {
        launcher.setPower(-power);
    }

}
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveTrain extends LinearOpMode {
    protected DcMotor fl, bl, fr, br, launcher, conveyor;
    private final ElapsedTime runtime = new ElapsedTime();

    public boolean getIsBlueAlliance() { return true; } //Set to false if red alliance

    private static final double COUNTS_PER_MOTOR_REV = 420;    // eg: NEVEREST60 Motor Encoder
    private static final double DRIVE_GEAR_REDUCTION = 0.5;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14);

    @Override
    public void runOpMode() {
    }

    public void setup() {
        //Initialize motors and set directions
        launcher = hardwareMap.dcMotor.get("launcher");
        conveyor = hardwareMap.dcMotor.get("conveyor");

        if (getIsBlueAlliance()) {
            fl = hardwareMap.dcMotor.get("Fl");
            bl = hardwareMap.dcMotor.get("Bl");
            fr = hardwareMap.dcMotor.get("Fr");
            br = hardwareMap.dcMotor.get("Br");

            fl.setDirection(DcMotor.Direction.FORWARD);
            bl.setDirection(DcMotor.Direction.FORWARD);
            fr.setDirection(DcMotor.Direction.REVERSE);
            br.setDirection(DcMotor.Direction.REVERSE);


        } else { //Mirror image for red alliance
            fr = hardwareMap.dcMotor.get("Fl");
            br = hardwareMap.dcMotor.get("Bl");
            fl = hardwareMap.dcMotor.get("Fr");
            bl = hardwareMap.dcMotor.get("Br");

            fr.setDirection(DcMotor.Direction.REVERSE);
            br.setDirection(DcMotor.Direction.REVERSE);
            fl.setDirection(DcMotor.Direction.FORWARD);
            bl.setDirection(DcMotor.Direction.FORWARD);
        }

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set motors to brake whenever they are stopped
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        conveyor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void drive(double leftPower, double rightPower) {
        fl.setPower(leftPower);
        bl.setPower(leftPower);
        fr.setPower(rightPower);
        br.setPower(rightPower);
    }
    public void driveForward(double power) {
        drive(power, power);
    }




    //Set each motor to drive a certain distance.
    //maxPower is the greatest absolute value of the power for any of the motors.
    //frInches, etc. can be positive or negative, but not 0.
    //timeoutS is the maximum number of seconds to run the OpMode before a hard stop.
    //The point is to avoid an infinite loop.
    //It should be much higher than the actual length of time it should take, e.g. 10.
    protected void encoderDrive(double maxPower, // 0 < maxPower <= 1
                                double frInches, double flInches, double brInches, double blInches, // + or -
                                double timeoutS) {
        int newFRTarget;
        int newFLTarget;
        int newBLTarget;
        int newBRTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFRTarget = fr.getCurrentPosition()     + (int) (frInches * COUNTS_PER_INCH);
            newFLTarget = fl.getCurrentPosition()     + (int) (flInches * COUNTS_PER_INCH);
            newBLTarget = bl.getCurrentPosition()     + (int) (blInches * COUNTS_PER_INCH);
            newBRTarget = br.getCurrentPosition()     + (int) (brInches * COUNTS_PER_INCH);










            //Determine wheel powers
            //Power for each wheel is proportional to the maximum power and distance travelled
            double maxInches = Math.max( Math.max(Math.abs(frInches), Math.abs(flInches)) ,
                    Math.max(Math.abs(brInches), Math.abs(blInches)) );
            double powerFR = maxPower * frInches / maxInches;
            double powerFL = maxPower * flInches / maxInches;
            double powerBR = maxPower * brInches / maxInches;
            double powerBL = maxPower * blInches / maxInches;

            telemetry.addData("EncoderDrivePowerFR", powerFR);
            telemetry.addData("EncoderDrivePowerFL", powerFL);
            telemetry.addData("EncoderDrivePowerBR", powerBR);
            telemetry.addData("EncoderDrivePowerBL", powerBL);
            telemetry.update();
            // reset the timeout time and start motion.


            fr.setTargetPosition(newFRTarget);
            fl.setTargetPosition(newFLTarget);
            bl.setTargetPosition(newBLTarget);
            br.setTargetPosition(newBRTarget);
            // Turn On RUN_TO_POSITION
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            fr.setPower(Math.abs(maxPower));
            fl.setPower(Math.abs(maxPower));
            bl.setPower(Math.abs(maxPower));
            br.setPower(Math.abs(maxPower));

            // Display it for the driver.

            telemetry.addData("fr ",fr.isBusy());
            telemetry.addData("fl ",fl.isBusy());
            telemetry.addData("br ",br.isBusy());
            telemetry.addData("bl ",bl.isBusy());

            telemetry.update();
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER/ANY motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH/ALL motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fr.isBusy() && fl.isBusy() && bl.isBusy() && br.isBusy())){
                // Display it for the driver.
                telemetry.addData("fr ",fr.isBusy());
                telemetry.addData("fl ",fl.isBusy());
                telemetry.addData("br ",br.isBusy());
                telemetry.addData("bl ",bl.isBusy());

                telemetry.update();
            }
            //Display the time elapsed
            telemetry.addData("Encoder Drive", "Finished in %.2f s/%f", runtime.seconds(), timeoutS);
            telemetry.update();

            // Stop all motion;
            fr.setPower(0);
            fl.setPower(0);
            bl.setPower(0);
            br.setPower(0);


            // Turn off RUN_TO_POSITION
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    //this is me to the best of my ability trying to rewrite the encoderDrive above but for the launcher, bare with me here

    public void setLauncherPower(double power){
        launcher.setPower(-power);
    }

    public void conveyorDrive(double moveInches , double power) {
        //the precise number of inches needed to be moved every time. Needs testing to approximate.

        int newConveyerTarget = (int) (conveyor.getCurrentPosition() + COUNTS_PER_INCH * moveInches);
        telemetry.addData("conveyerTarget", newConveyerTarget);
        telemetry.update();
        conveyor.setTargetPosition(newConveyerTarget);
        conveyor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        conveyor.setPower(power);
        while(opModeIsActive() && conveyor.isBusy()) {
            sleep(10);
        }
        conveyor.setPower(0);
        conveyor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}
