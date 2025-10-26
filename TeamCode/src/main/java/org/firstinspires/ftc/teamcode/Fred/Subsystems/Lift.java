package org.firstinspires.ftc.teamcode.Fred.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift extends SubsystemBase {
    Servo sL;

    public Lift(HardwareMap hardwareMap){
        sL = hardwareMap.get(Servo.class,"sL");

    }

    @Override
    public void periodic(){

    }

    public void liftUp(){
        sL.setPosition(.62);
    }

    public void liftDown(){
        sL.setPosition(0.25);
    }
}