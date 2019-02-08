package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MidwestCoast",group = "B")
public class MidwestCoast extends OpMode {
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor leverMotor;
    DcMotor slideMotor;
    CRServo intakeServo;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        leverMotor = hardwareMap.dcMotor.get("leverMotor");
        slideMotor = hardwareMap.dcMotor.get("slideMotor");
        intakeServo = hardwareMap.crservo.get("intakeServo");
    }

    @Override
    public void loop() {
        double y = gamepad1.left_stick_y;
        double z = gamepad1.right_stick_x;
        motorLeft.setPower(.5*-Math.signum(y)*Math.pow(y,2)-Math.signum(z)*Math.pow(z,2));
        motorRight.setPower(.5*Math.signum(y)*Math.pow(y,2)-Math.signum(z)*Math.pow(z,2));
        if(gamepad1.right_trigger>0){
            leverMotor.setPower(-gamepad1.right_trigger);
        }
        else if(gamepad1.left_trigger>0){
            leverMotor.setPower(gamepad1.left_trigger);
        }
        else{
            leverMotor.setPower(0);
        }
        if(gamepad1.right_bumper){
            slideMotor.setPower(-1);
        }
        else if(gamepad1.left_bumper){
            slideMotor.setPower(1);
        }
        else{
            slideMotor.setPower(0);
        }
        if(gamepad1.a){
            intakeServo.setPower(.5);
        }
        if(gamepad1.b){
            intakeServo.setPower(0);
        }
        if(gamepad1.y){
            intakeServo.setPower(-.5);
        }

    }
}
