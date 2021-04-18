package org.firstinspires.ftc.teamcode;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Manual")
public class TeleOp extends DriveTrain {

 /*
    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor br = null;
    private DcMotor fr = null;
*/
    @Override
    public void runOpMode(){
        setup();
        waitForStart();
        telemetry.addData("Status:", "Ready");
        telemetry.addData("opModeIsActive: ",opModeIsActive());
        telemetry.update();
        telemetry.addData("opmodeisacttive",opModeIsActive());
        telemetry.update();


        while (opModeIsActive()) {
            telemetry.addData("stick",gamepad1.left_stick_y);
            telemetry.addData("stick of other:", gamepad2.left_stick_y);
            fl.setPower(.7 * -gamepad1.left_stick_y);
            bl.setPower(.7 * -gamepad1.left_stick_y);
            fr.setPower(.7 * -gamepad1.right_stick_y);
            br.setPower(.7 * -gamepad1.right_stick_y);
            telemetry.update();

    }


    }

    public void setup() {
        /*
        fr = hardwareMap.get(DcMotor.class, "Fr");
        fl = hardwareMap.get(DcMotor.class, "Fl");
        br = hardwareMap.get(DcMotor.class, "Br");
        bl = hardwareMap.get(DcMotor.class, "Bl");

        fr.setDirection(DcMotor.Direction.FORWARD);
        fl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);

         */

    }
}
