package org.firstinspires.ftc.teamcode.Fred.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter extends SubsystemBase {

    public CRServo mS;

    public Shooter(HardwareMap hardwareMap){
        mS = hardwareMap.get(CRServo.class, "mS");
    }

    @Override
    public void periodic(){

    }

    public void spin(){
        mS.setPower(-0.85);
    }

    public void stop(){
        mS.setPower(0.0);
    }
}
