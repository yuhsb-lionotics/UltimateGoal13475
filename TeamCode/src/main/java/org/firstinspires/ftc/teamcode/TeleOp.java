package org.firstinspires.ftc.teamcode;

// DONT USE THIS CODE! USE BasicOpModeLinear


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
        telemetry.addData("Status:", "DONT use this opmode. Use BasicOpModeLinear");
        telemetry.addData("opModeIsActive: ",opModeIsActive());
        telemetry.update();
        telemetry.addData("opmodeisacttive",opModeIsActive());
        telemetry.update();


        while (opModeIsActive()) {
            telemetry.addData("stick",gamepad1.left_stick_y);
            telemetry.addData("stick of other:", gamepad2.left_stick_y);
            frontLeft.setPower(.6 * -gamepad1.left_stick_y);
            backLeft.setPower(.6 * -gamepad1.left_stick_y);
            frontRight.setPower(.6 * -gamepad1.right_stick_y);
            backRight.setPower(.6 * -gamepad1.right_stick_y);
            telemetry.update();


            if(gamepad1.a){ //low power
                double power = 0; //amount of power to apply to launcher
                launcher.setPower(power);
            }
//            else if(gamepad1.b){ //high power
//                double power = 0; //amount of power to apply to
//                launcher.setPower(power);l
//            }
            }

    }


    }

//    public void setup() {
//        /*
//        fr = hardwareMap.get(DcMotor.class, "Fr");
//        fl = hardwareMap.get(DcMotor.class, "Fl");
//        br = hardwareMap.get(DcMotor.class, "Br");
//        bl = hardwareMap.get(DcMotor.class, "Bl");
//
//        fr.setDirection(DcMotor.Direction.FORWARD);
//        fl.setDirection(DcMotor.Direction.REVERSE);
//        br.setDirection(DcMotor.Direction.FORWARD);
//        bl.setDirection(DcMotor.Direction.REVERSE);
//
//         */
//
//    }
//}
