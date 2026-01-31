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

import org.firstinspires.ftc.teamcode.Fred.Subsystems.Lift;
import org.firstinspires.ftc.teamcode.Fred.Subsystems.Shooter;

@TeleOp (name="Fred_TeleOp")
public class FredTeleOp extends CommandOpMode {

    GamepadEx driver1;
    Lift lift;
    Shooter shooter;

    CRServo a;

    @Override
    public void initialize() {
        lift = new Lift(hardwareMap);

        driver1 = new GamepadEx(gamepad1);

        shooter = new Shooter(hardwareMap);

        a = hardwareMap.get(CRServo.class,"mI");


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


    }

    public void run(){
        super.run();

//        shooter.mS.setPower(.3);
//        a.setPower(.3);

        telemetry.addData("shooterPower", shooter.mS.getPower());
        telemetry.update();
    }
}
