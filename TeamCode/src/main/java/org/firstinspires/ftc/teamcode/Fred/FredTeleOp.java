package org.firstinspires.ftc.teamcode.Fred;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Fred.Subsystems.Lift;

@TeleOp (name="Fred_TeleOp")
public class FredTeleOp extends CommandOpMode {

    GamepadEx driver1;
    Lift lift;

    @Override
    public void initialize() {
        lift = new Lift(hardwareMap);

        driver1 = new GamepadEx(gamepad1);


        new Trigger(()-> driver1.getButton(GamepadKeys.Button.RIGHT_BUMPER))
                .whenActive(
                        new SequentialCommandGroup(
                                new InstantCommand(lift::liftUp),
                                new WaitCommand(3000),
                                new InstantCommand(lift::liftDown)
                        )
                );


    }

    public void run(){
        super.run();

    }
}
