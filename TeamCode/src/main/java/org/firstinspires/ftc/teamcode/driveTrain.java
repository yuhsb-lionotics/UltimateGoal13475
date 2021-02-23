package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


public class driveTrain extends LinearOpMode {
    private DcMotor motorFrontRight;
    private DcMotor motorFrontLeft;
    private DcMotor motorBackRight;
    private DcMotor motorBackLeft;

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
            motorFrontLeft = hardwareMap.dcMotor.get("Fl");
            motorFrontRight = hardwareMap.dcMotor.get("Fr");
            motorBackLeft = hardwareMap.dcMotor.get("Bl");
            motorBackRight = hardwareMap.dcMotor.get("Br");
        } else {
            motorFrontRight = hardwareMap.dcMotor.get("Fl");
            motorFrontLeft = hardwareMap.dcMotor.get("Fr");
            motorBackRight = hardwareMap.dcMotor.get("Bl");
            motorBackLeft = hardwareMap.dcMotor.get("Br");
        }

    }

    public void driveForward(double power){
        motorFrontRight.setPower(power);
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
    }

    public void turnLeft(double power){
        motorFrontRight.setPower(power);
        motorFrontLeft.setPower(-power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(-power);
    }

    public void turnRight(double power){
        motorFrontRight.setPower(-power);
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(power);
    }
    public void driveBackword(double power){
        motorFrontRight.setPower(-power);
        motorFrontLeft.setPower(-power);
        motorBackLeft.setPower(-power);
        motorBackRight.setPower(-power);

    }
//More to Come!

}


