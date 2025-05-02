package org.firstinspires.ftc.teamcode.Optimus;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "Optimus_Teleop")

public class Optimus_Teleop extends CommandOpMode {
    //declaration of parts
    DcMotor mR, mL;

    GamepadEx driver1, driver2;

    public double FB, Rotation;

    @Override
    public void initialize(){
        //hardware map and triggers
        mR = hardwareMap.get(DcMotor.class,"mR");
        mL = hardwareMap.get(DcMotor.class,"mL");

        mR.setDirection(DcMotorSimple.Direction.REVERSE);

        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);
    }

    @Override
    public void run(){
        //teleop specific logic
        super.run();

        FB = driver1.getLeftY();
        Rotation = driver1.getRightX();

        mR.setPower(FB - Rotation);
        mL.setPower(FB + Rotation);

        telemetry.addData("FB", FB);
        telemetry.addData("Rotation", Rotation);
        telemetry.addData("mR_Power", mR.getPower());
        telemetry.addData("mL_Power", mL.getPower());
        telemetry.update();
    }
}

