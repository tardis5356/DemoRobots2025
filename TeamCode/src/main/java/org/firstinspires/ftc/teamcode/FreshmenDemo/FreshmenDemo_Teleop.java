package org.firstinspires.ftc.teamcode.FreshmenDemo;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FreshmenDemo.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.FreshmenDemo.SubSystems.Shooter;
import org.firstinspires.ftc.teamcode.FreshmenDemo.SubSystems.FreshmenDemoBotPositions;

@TeleOp(name = "FreshmenDemo_Teleop")
public class FreshmenDemo_Teleop extends CommandOpMode {

    DcMotor mFR, mFL, mBR, mBL, mI, mS;
    //I = intake
    //S = shooter

    Servo sT;
    //T = transfer

    GamepadEx driver1, driver2;

    public double ForwardBackward, Rotation;

    @Override
    public void initialize() {
        //hardware map

        mFR = hardwareMap.get(DcMotor.class, "mFR");
        mFL = hardwareMap.get(DcMotor.class, "mFL");
        mBR = hardwareMap.get(DcMotor.class, "mBR");
        mBL = hardwareMap.get(DcMotor.class, "mBL");

        mFL.setDirection(DcMotorSimple.Direction.REVERSE);
        mBL.setDirection(DcMotorSimple.Direction.REVERSE);

        mI = hardwareMap.get(DcMotor.class,"mI");

        mS = hardwareMap.get(DcMotor.class,"mS");

        sT = hardwareMap.get(Servo.class, "sT");

        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

        //triggers




    }

    @Override
    public void run() {
        //teleop specific logic
        super.run();

        ForwardBackward = antiDrift(driver1.getLeftY());
        Rotation = antiDrift(driver1.getRightX());

        mFR.setPower(ForwardBackward - Rotation);
        mFL.setPower(ForwardBackward + Rotation);
        mBR.setPower(ForwardBackward - Rotation);
        mBL.setPower(ForwardBackward + Rotation);


        telemetry.addData("FB", ForwardBackward);
        telemetry.addData("Rotation", Rotation);
        telemetry.addData("mFR_Power", mFR.getPower());
        telemetry.addData("mFL_Power", mFL.getPower());
        telemetry.addData("mBR_Power", mBR.getPower());
        telemetry.addData("mBL_Power", mBL.getPower());
        telemetry.addData("LeftStickY", driver1.getLeftY());
        telemetry.addData("RightStickX", driver1.getRightX());
        telemetry.update();
    }

//Stick drift combat
    private double antiDrift(double stickVal) {

        if (stickVal < -.02 || stickVal > .02) {
            return stickVal;
        } else {
            return 0;
        }
    }
}
