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

    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor br = null;
    private DcMotor fr = null;

    @Override
    public void runOpMode(){
        setup();
        waitForStart();
        telemetry.addData("Status:", "Ready");
        telemetry.addData("opModeIsActive: ",opModeIsActive());
        telemetry.update();
        while(opModeIsActive()){
            telemetry.addData("stick",gamepad1.left_stick_x);
            telemetry.addData("stick of other:", gamepad2.left_stick_x);
            fl.setPower(.7 * -gamepad1.left_stick_y);
            bl.setPower(.7 * -gamepad1.left_stick_y);
            fr.setPower(.7 * -gamepad1.right_stick_y);
            br.setPower(.7 * -gamepad1.right_stick_y);
            telemetry.update();

    }


    }

    public void setup() {
        fr = hardwareMap.get(DcMotor.class, "Fr");
        fl = hardwareMap.get(DcMotor.class, "Fl");
        br = hardwareMap.get(DcMotor.class, "Br");
        bl = hardwareMap.get(DcMotor.class, "Bl");

        fr.setDirection(DcMotor.Direction.FORWARD);
        fl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);

    }
}
