package org.firstinspires.ftc.teamcode.Optimus.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Arm extends SubsystemBase {

    public DcMotor mA;

    public TouchSensor armLimit;

    public double armPosition, positionDiff;

    public Arm(HardwareMap hardwareMap){



    }

    @Override

    public void periodic(){
        if (armLimit.isPressed()){armPosition = 3750;
            positionDiff = armPosition - mA.getCurrentPosition();
        }

        //if (armPosition>=3750 && driver2.left_stick_y<0) {mA.setPower(0);

       // } else if (armPosition<0 && driver2.left_stick_y>0) {mA.setPower(0);

        //} else {mA.setPower(driver2.left_stick_y);

       // }
        //telemetry.addData("armPower", mA.getPower());
       // telemetry.update();

    }

}

