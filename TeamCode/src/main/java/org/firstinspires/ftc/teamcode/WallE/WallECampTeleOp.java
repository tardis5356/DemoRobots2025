package org.firstinspires.ftc.teamcode.WallE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp (name = "Wall-E_Camp_Tele")
public class WallECampTeleOp extends LinearOpMode {


    DcMotor Billy,Bobette;

    ColorSensor Josh,Jamal;
    @Override
    public void runOpMode(){
   Billy=hardwareMap.get(DcMotor.class,"mL");
        Bobette=hardwareMap.get(DcMotor.class,"mR");
        waitForStart();

        while(opModeIsActive())   {
Billy.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);
         Bobette.setPower(gamepad1.left_stick_y+ gamepad1.right_stick_x);
        }
    }
}
