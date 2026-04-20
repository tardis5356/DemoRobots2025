package org.firstinspires.ftc.teamcode.Fred;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Fred.Subsystems.Lift;
import org.firstinspires.ftc.teamcode.Fred.Subsystems.Shooter;

@TeleOp (name="Fred_TeleOp")
public class FredTeleOp extends CommandOpMode {

    GamepadEx driver1;
    Lift lift;
    Shooter shooter;
    double FB, LR, Rotation;
    CRServo a;
    CRServo mI;
    double mFLPower;
    double mFRPower;
    double mBLPower;
    double mBRPower;

    DcMotorEx mFL, mFR, mBR, mBL;

    @Override
    public void initialize() {
        lift = new Lift(hardwareMap);

        driver1 = new GamepadEx(gamepad1);

        shooter = new Shooter(hardwareMap);

        a = hardwareMap.get(CRServo.class,"mI");

        mFL = hardwareMap.get(DcMotorEx.class, "mFL");
        mFR = hardwareMap.get(DcMotorEx.class, "mFR");
        mBL = hardwareMap.get(DcMotorEx.class, "mBL");
        mBR = hardwareMap.get(DcMotorEx.class, "mBR");

        mFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        mBL.setDirection(DcMotorSimple.Direction.REVERSE);
        mFL.setDirection(DcMotorSimple.Direction.REVERSE);
        new Trigger(()-> driver1.getButton(GamepadKeys.Button.RIGHT_BUMPER))
                .whenActive(
                        new SequentialCommandGroup(
                                new InstantCommand(lift::liftUp),
                                new WaitCommand(3000),
                                new InstantCommand(lift::liftDown)
                        )
                );

        new Trigger (()-> driver1.getButton(GamepadKeys.Button.Y))
                .toggleWhenActive(shooter::spin, shooter::stop);


        new Trigger (()-> driver1.getButton(GamepadKeys.Button.X))
                .toggleWhenActive(new InstantCommand(()->a.setPower(1)),new InstantCommand(()->a.setPower(0)));

        new Trigger (()-> driver1.getButton(GamepadKeys.Button.B))
                .toggleWhenActive(new InstantCommand(()->a.setPower(-1)),new InstantCommand(()->a.setPower(0)));
        


    }

    public void run(){
        super.run();
        Rotation = -cubicScaling(gamepad1.left_trigger - gamepad1.right_trigger) * 0.75;
        FB = gamepad1.left_stick_y;
        LR = gamepad1.left_stick_x;
        mFLPower = FB + LR + Rotation;
        mFRPower = FB - LR - Rotation;
        mBLPower = FB - LR + Rotation;
        mBRPower = FB + LR - Rotation;


        mFL.setPower(mFLPower );
        mFR.setPower(mFRPower);
        mBL.setPower(mBLPower );
        mBR.setPower(mBRPower );
//        shooter.mS.setPower(.3);
//        a.setPower(.3);

        telemetry.addData("shooterPower", shooter.mS.getPower());
        telemetry.update();
    }
    private double cubicScaling(float joystickValue) {
        //store 5% of the joystick value + 95% of the joystick value to the 3rd power
        double v = 0.05 * joystickValue + 0.95 * Math.pow(joystickValue, 3);
        if (joystickValue > 0.02)
            //if the joystick is positive, return positive .1 + the stored value
            return 0.1 + v;
        else if (joystickValue < -0.02)
            //if the joystick is negative, return -.1 plus the stored value
            return -0.1 + v;
            // theres a range where this won't do either, which is a good counter against stick drift (because you can never escape stick drift)
        else
            return 0;
    }
}
