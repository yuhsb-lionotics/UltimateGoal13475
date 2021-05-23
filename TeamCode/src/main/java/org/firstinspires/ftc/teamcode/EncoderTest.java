package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Encoder test")

public class EncoderTest extends DriveTrain {

    private final ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1680;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 0.5;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14159);

    @Override
    public void runOpMode() {
        setup();
        waitForStart();
        try {
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            int newFRTarget = frontRight.getCurrentPosition() + (int) (30.0 * COUNTS_PER_INCH);
            int newFLTarget = frontLeft.getCurrentPosition() + (int) (30.0 * COUNTS_PER_INCH);
            int newBLTarget = backLeft.getCurrentPosition() + (int) (30.0 * COUNTS_PER_INCH);
            int newBRTarget = backRight.getCurrentPosition() + (int) (30.0 * COUNTS_PER_INCH);

            frontRight.setTargetPosition(2000);
            frontLeft.setTargetPosition(2000);
            backLeft.setTargetPosition(2000);
            backRight.setTargetPosition(2000);
            runtime.reset();
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setPower(0.4);
            frontLeft.setPower(0.4);
            backLeft.setPower(0.4);
            backRight.setPower(0.4);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER/ANY motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH/ALL motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < 10) &&
                    (frontRight.isBusy() && frontLeft.isBusy() && backLeft.isBusy() && backRight.isBusy())) {
                sleep(1);
            }

            //Display the time elapsed
            telemetry.addData(
                    "Encoder Drive",
                    "Finished in %.2f s/%f",
                    runtime.seconds(),
                    10.0
            );
            telemetry.update();

            // Stop all motion;
            frontRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
        catch (Exception exception){
            telemetry.addData("exception",exception.getStackTrace());
            telemetry.update();
        }

}
}
