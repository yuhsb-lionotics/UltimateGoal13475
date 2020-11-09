package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Manual")
public class TeleOp extends LinearOpMode {

    //Written by Emmett. I don't know what your DriveTrain class is supposed to do, so you might
    //want to delete the code I wrote and replace it with something relying on DriveTrain.

    DcMotor fl, bl, fr, br;

    @Override
    public void runOpMode(){
        setup();
        waitForStart();
        fl.setPower(.7 * -gamepad1.left_stick_y);
        bl.setPower(.7 * -gamepad1.left_stick_y);
        fr.setPower(.7 * -gamepad1.right_stick_y);
        br.setPower(.7 * -gamepad1.right_stick_y);
    }

    public void setup() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);

    }
}
