package org.firstinspires.ftc.teamcode.Optimus;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Optimus.Subsystems.Gripper;

@TeleOp (name = "Optimus_Teleop")

public class Optimus_Teleop extends CommandOpMode {
    //declaration of parts
    DcMotor mR, mL;

    GamepadEx driver1, driver2;

    Gripper gripper;

    public double ForwardBackward, Rotation;

    @Override
    public void initialize(){
        //hardware map
        mR = hardwareMap.get(DcMotor.class,"mR");
        mL = hardwareMap.get(DcMotor.class,"mL");

        mL.setDirection(DcMotorSimple.Direction.REVERSE);

        gripper = new Gripper(hardwareMap);

        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

        //triggers
        new Trigger(()-> driver2.getButton(GamepadKeys.Button.RIGHT_BUMPER) || driver2.getButton(GamepadKeys.Button.LEFT_BUMPER))
                .toggleWhenActive(gripper::open,gripper::close);

        new Trigger(()-> driver2.getButton(GamepadKeys.Button.DPAD_UP))
                .whenActive(gripper::up);

        new Trigger(()-> driver2.getButton(GamepadKeys.Button.DPAD_LEFT))
                .whenActive(gripper::middle);

        new Trigger(()-> driver2.getButton(GamepadKeys.Button.DPAD_DOWN))
                .whenActive(gripper::down);

    }

    @Override
    public void run(){
        //teleop specific logic
        super.run();

        ForwardBackward = antiDrift(driver1.getLeftY());
        Rotation = antiDrift(driver1.getRightX());

        mR.setPower(ForwardBackward - Rotation);
        mL.setPower(ForwardBackward + Rotation);

        telemetry.addData("FB", ForwardBackward);
        telemetry.addData("Rotation", Rotation);
        telemetry.addData("mR_Power", mR.getPower());
        telemetry.addData("mL_Power", mL.getPower());
        telemetry.addData("LeftStickY", driver1.getLeftY());
        telemetry.addData("RightStickX", driver1.getRightX());
        telemetry.update();
    }

    private double antiDrift(double stickVal){

        if(stickVal < -.02 || stickVal > .02){
            return stickVal;
        }
        else{
            return 0;
        }
    }
}
