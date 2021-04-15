package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Go forward")
public class GoForward extends DriveTrain {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        setup();
        telemetry.addData("Status:","Initialized");
        //telemetry.addData("FL Stop mode",FL.getZeroPowerBehavior());
        //telemetry.addData("FR Stop mode", fr.getZeroPowerBehavior());
        telemetry.update();
        waitForStart();

        telemetry.addData("Status:", "Running");
        telemetry.update();
        //encoderDrive(0.7,42,10, 10, 10, 2);
        while (opModeIsActive()) {
            telemetry.addData("stick",gamepad1.left_stick_x);
            telemetry.addData("stick of other:", gamepad2.left_stick_x);
            telemetry.update();
            fl.setPower(.7 * -gamepad1.left_stick_y);
            bl.setPower(.7 * -gamepad1.left_stick_y);
            fr.setPower(.7 * -gamepad1.right_stick_y);
            br.setPower(.7 * -gamepad1.right_stick_y);

        }

        //encoderDrive(1,1,1,1,16*Math.PI, 15);

    }
}