package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto", group="LinearOpMode")
public class Auto extends DriveTrain {

    private final ElapsedTime runtime = new ElapsedTime();
    private static final double conveyorDiameter = 2.5;
    private static final double conveyorCountsPerInch = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (conveyorDiameter * Math.PI);

    //Needs adjusting. Just set for preliminary testing
    static final double INCHES_FROM_GOAL = 64; //distance from starting  position to "C" wobble goal drop-off

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

    @Override
    public void setup() {
        super.setup();
        //Initialize motors and set directions
        launcher = hardwareMap.dcMotor.get("launcher");
        conveyor = hardwareMap.dcMotor.get("conveyor");

    }

    public void setLauncherPower(final double power) {
        launcher.setPower(-power);
    }
    public void conveyorDrive(final double moveInches , final double power) {
        //the precise number of inches needed to be moved every time. Needs testing to approximate.

        int newConveyorTarget = (int) (conveyor.getCurrentPosition() + conveyorCountsPerInch * moveInches);

        telemetry.addData("conveyorTarget", newConveyorTarget);
        telemetry.update();
        conveyor.setTargetPosition(newConveyorTarget);
        conveyor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        conveyor.setPower(power);

        while(opModeIsActive() && conveyor.isBusy()) {
            sleep(10);
        }

        conveyor.setPower(0);
        conveyor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

        /* Let's quickly recap how to use encoder drive.
         *             !These are just motor values, not what encoder drive should be!
         *             case N: encodeDrive(1.0,1.0,1.0,1.0);
         *             case S: encodeDrive(-1.0,-1.0,-1.0,-1.0);
         *             case E: encodeDrive(1.0,-1.0,-1.0,1.0);
         *             case W: encodeDrive(-1.0,1.0,1.0,-1.0);
         *             case NE: encodeDrive(1.0,0,0,1.0);
         *             case NW: encodeDrive(0,1.0,1.0,0);
         *             case SE: encodeDrive(0,-1.0,-1.0,0);
         *             case SW: encodeDrive(-1.0,0,0,-1.0);
         *             case ROTATER: encodeDrive(1.0,-1.0,1.0,-1.0);
         *             case ROTATEL: encodeDrive(-1.0,1.0,-1.0,1.0);
         *             case STOP:encodeDrive(0,0,0,0);
         */
        //FRENCH FRY
        //CODE GOES HERE:



}