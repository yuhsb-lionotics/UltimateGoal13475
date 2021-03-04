package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;




///@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Manual")

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Teleop")

public class TeleOp extends LinearOpMode{

    //Written by Emmett. I don't know what your DriveTrain class is supposed to do, so you might
    //want to delete the code I wrote and replace it with something relying on DriveTrain.

    DcMotor fl, bl, fr, br;

    @Override
    public void runOpMode(){
        setup();
        waitForStart();
        while(opModeIsActive()){
        fl.setPower(.7 * -gamepad1.left_stick_y);
        bl.setPower(.7 * -gamepad1.left_stick_y);
        fr.setPower(.7 * -gamepad1.right_stick_y);
        br.setPower(.7 * -gamepad1.right_stick_y);
        telemetry.addData("Stick x",this.gamepad1.left_stick_x) ;
        telemetry.addData("Stick y",this.gamepad1.left_stick_y);
    }}



    public void setup() {
        fl = hardwareMap.dcMotor.get("Fl");
        bl = hardwareMap.dcMotor.get("Bl");
        fr = hardwareMap.dcMotor.get("Fr");
        br = hardwareMap.dcMotor.get("Br");

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);

    }
}
