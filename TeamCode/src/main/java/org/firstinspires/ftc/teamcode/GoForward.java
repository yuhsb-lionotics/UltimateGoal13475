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
        conveyerDrive(1,0.8);

   //encoderDrive(1,1,1,1,16*Math.PI, 15);

    }
}