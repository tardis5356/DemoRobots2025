package org.firstinspires.ftc.teamcode.OldDemoCode.DemoBots.optimus;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

//@Disabled
@TeleOp(name="Optimus_TeleOpCampTARDIS2", group="demo")
public class Optimus_TeleOpCampTARDIS2 extends LinearOpMode {

    // create objects and give them classes
    DcMotor mL;
    DcMotor mR;
    DcMotor mA;

    Servo sG;
    Servo sW;
    TouchSensor limit;
    double wristPosition = .7;
    double ArmPosition;
    double PositionDiff;
    boolean FarBack;
    double LeftstickY;
    double RightstickX;
    boolean GripperState = false;
    double GripperToggle;
    double GripperEO;
    double Rabs;
    double Labs;

// initialization

    @Override
    public void runOpMode() {
// map objects to motors/servos
        mL = hardwareMap.get(DcMotor.class, "mL");
        mR = hardwareMap.get(DcMotor.class, "mR");
        mA = hardwareMap.get(DcMotor.class, "mA");
        sG = hardwareMap.get(Servo.class, "sG");
        sW = hardwareMap.get(Servo.class, "sW");
        limit = hardwareMap.get(TouchSensor.class, "armLimit");

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();


// program has startedz
        waitForStart();

        while (opModeIsActive()) {

            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);


            //chasis
            boolean dpU = gamepad1.dpad_up;
            boolean dpD = gamepad1.dpad_down;
            boolean dpR1 = gamepad1.dpad_right;
            boolean dpL1 = gamepad1.dpad_left;

            //TODO Write drivetrain code using the joysticks

            mL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x);
            mR.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x);

            ArmPosition = mA.getCurrentPosition() + PositionDiff;

            if ((currentGamepad2.left_bumper && !previousGamepad2.left_bumper) || (currentGamepad2.right_bumper && !previousGamepad2.right_bumper)) {
                GripperState = !GripperState;
            }

            //gripper
            boolean RightTrigger = gamepad2.right_bumper;
            boolean LeftTrigger = gamepad2.left_bumper;

            //TODO Write an if/else statement using the Gripper state variable
            // to make an open/close toggle for the gripper

            //gripper positions
            // close = .37
            //open = .6

            if (GripperState) {
                sG.setPosition(.37);

            } else if (GripperState == false) {
                sG.setPosition(.6);

            }


            //wrist
            boolean rB2 = gamepad2.dpad_down;
            boolean lB2 = gamepad2.dpad_up;

            //decide if to do this part

            //TODO write code that moves the wrist by setting the wrist position double
            //down = 1 up = .7


            if (lB2) {

            } else if (rB2) {

            }
        }
        sW.setPosition(wristPosition);
        //arm

        double RightstickY2 = gamepad2.right_stick_y;
        double LeftstickY2 = gamepad2.left_stick_y;

        Rabs = Math.abs(RightstickY2);
        Labs = Math.abs(LeftstickY2);

        //TODO Explain the logic here and fill out the limit switch true or false

        if (limit.isPressed() == false && ArmPosition >= 0) {
            if (Rabs > Labs) {
                mA.setPower((-RightstickY2) / 2);
            } else {
                mA.setPower((-LeftstickY2) / 2);
            }
//                mA.setPower((-RightstickY2)/2);
//                mA.setPower((-LeftstickY2)/2);
            FarBack = false;
        } else if (ArmPosition < -100 && limit.isPressed() == false) {
            FarBack = true;
            mA.setPower(.5);
        } else if (ArmPosition >= -100 && ArmPosition < 0 && FarBack == true) {
            mA.setPower(.5);
        } else if (ArmPosition >= -100 && ArmPosition < 0 && FarBack == false) {
            if (Rabs > Labs) {
                mA.setPower((-RightstickY2));
            } else {
                mA.setPower((-LeftstickY2));
            }
        } else if (limit.isPressed() == true) {
            mA.setPower(-.5);
            ArmPosition = 3750;
            PositionDiff = ArmPosition - mA.getCurrentPosition();
        }
        if (ArmPosition >= 3850) {
            mA.setPower(-.5);
        }


        telemetry.addData("trigger", gamepad2.right_trigger);
        telemetry.addData("lbump", gamepad2.left_bumper);
        telemetry.addData("rbump", gamepad2.right_bumper);
        telemetry.addData("WristPosition", wristPosition);
        telemetry.addData("limit", limit.isPressed());
        telemetry.addData("ArmPosition", ArmPosition);
        telemetry.addData("TrueArmPosition", mA.getCurrentPosition());
        telemetry.update();


        }


    }

