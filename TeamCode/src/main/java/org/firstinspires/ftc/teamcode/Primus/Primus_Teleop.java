package org.firstinspires.ftc.teamcode.Primus;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp

public class Primus_Teleop extends CommandOpMode {
    DcMotor mBR, mBL, mFR, mFL;
    GamepadEx driver1, driver2;

    @Override
    public void initialize() {

        mBL = hardwareMap.get(DcMotor.class,"mBL");
        mBR = hardwareMap.get(DcMotor.class,"mBR");
        mFL = hardwareMap.get(DcMotor.class,"mFL");
        mFR = hardwareMap.get(DcMotor.class,"mFL");

        mBR.setDirection(DcMotorSimple.Direction.REVERSE);
        mBL.setDirection(DcMotorSimple.Direction.REVERSE);

        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

    }

}
