package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
//test
public class driveTrain extends LinearOpMode {
    protected DcMotor fr,fl,br,bl;
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

    public void setup(HardwareMap ahwmap){
        HardwareMap hwmap = ahwmap;
        if(isBlue){
            fl = hardwareMap.dcMotor.get("Fl");
            fr = hardwareMap.dcMotor.get("Fr");
            bl = hardwareMap.dcMotor.get("Bl");
            br = hardwareMap.dcMotor.get("Br");
        } else {
            fr = hardwareMap.dcMotor.get("Fl");
            fl = hardwareMap.dcMotor.get("Fr");
            br = hardwareMap.dcMotor.get("Bl");
            bl = hardwareMap.dcMotor.get("Br");
        }

    }

    public void driveForward(double power){
        fr.setPower(power);
        fl.setPower(power);
        br.setPower(power);
        bl.setPower(power);
    }

    public void turnLeft(double power){
        fr.setPower(power);
        fl.setPower(-power);
        br.setPower(power);
        bl.setPower(-power);
    }

    public void turnRight(double power){
        fr.setPower(-power);
        fl.setPower(power);
        br.setPower(-power);
        bl.setPower(power);
    }
}

