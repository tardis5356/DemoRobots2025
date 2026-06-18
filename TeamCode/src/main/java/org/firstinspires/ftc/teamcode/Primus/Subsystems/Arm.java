package org.firstinspires.ftc.teamcode.Primus.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Arm extends SubsystemBase {

    public DcMotor mA;

    public TouchSensor armLimit;

    public double armPosition;

    public Arm(HardwareMap hardwareMap) {
        mA = hardwareMap.get(DcMotor.class, "mA");
        armLimit = hardwareMap.get(TouchSensor.class, "armlimit");
    }

    @Override

    public void periodic(){

        if (armLimit.isPressed()){
            armPosition = 0;
        }

    }

}
