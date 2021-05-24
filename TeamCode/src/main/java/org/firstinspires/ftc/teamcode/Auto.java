package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto", group="LinearOpMode")
public class Auto extends DriveTrain {

    private final ElapsedTime runtime = new ElapsedTime();

    //Needs adjusting. Just set for preliminary testing
    static final double INCHES_FROM_GOAL = 10; //distance from starting  position to "C" wobble goal drop-off

    @Override
    public void runOpMode() {
        setup();
        waitForStart();
        runtime.reset();
        //encoderDrive(0.6, 10, 10, 10, 10, 10);
        //requestOpModeStop();

        // for moving wobble goal:
        encoderDrive(0.2, INCHES_FROM_GOAL, INCHES_FROM_GOAL, INCHES_FROM_GOAL, INCHES_FROM_GOAL, 10);
        // move back to park:
   //encoderDrive(0.4, -12, -12, -12, -12, 10); //12 inches is just a guess, make adjustments if necessary





}
}