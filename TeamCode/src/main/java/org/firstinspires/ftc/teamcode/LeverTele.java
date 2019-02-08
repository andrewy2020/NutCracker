package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "LeverTele",group = "A")
public class LeverTele extends OpMode{
    MechTeleMap bot = new MechTeleMap();
    double xpow;
    double ypow;
    double zpow;
    int count = 0;
    @Override
    public void init() {
        //initalizes hardware map
        bot.init(hardwareMap);
    }

    public void readGamePad() {
        //assigns joystick values to variables
        zpow = gamepad1.right_stick_x;
        ypow = gamepad1.left_stick_y;
        xpow = -gamepad1.left_stick_x;

        //creates a deadzone for left stick y
        if (Math.abs(ypow) < .1) {
            ypow = 0;

        }
        //creates a deadzone for left stick x
        if (Math.abs(xpow) < .1) {
            xpow = 0;

        }
    }

    @Override
    public void loop() {
        //takes the joystick values and converts to motor speeds through holonomic calculations
        readGamePad();

        double theta = Math.atan2(ypow, xpow); //angle of joystick
        double power = Math.pow(Math.max(Math.abs(xpow),Math.abs(ypow)),2); //logarithmic drive
        double zpower = Math.pow(Math.abs(zpow),2);
        // offset of pi/4 makes wheels strafe correctly at cardinal and intermediate directions
        double x = Math.cos(theta);
        double y= Math.sin(theta);

        double z = Math.signum(zpow);

        bot.motorLF.setPower(power * (-y-x) + zpower*z);
        bot.motorRF.setPower(power * (y-x) + zpower*z);
        bot.motorRB.setPower(power * (y+x) + zpower*z);
        bot.motorLB.setPower(power * (-y+x) + zpower*z);

        telemetry.addData("xpow",xpow);
        telemetry.addData("ypow",ypow);
        telemetry.addData("zpow",zpow);
        telemetry.addData("power",power);
        telemetry.addData("x",x);
        telemetry.addData("y",y);
        telemetry.addData("motorLF",bot.motorLF.getPower());
        telemetry.addData("motorRF",bot.motorRF.getPower());
        telemetry.addData("motorLB",bot.motorLB.getPower());
        telemetry.addData("motorRB",bot.motorRB.getPower());
        telemetry.addData("hangMotor",bot.hangMotor.getPower());
        telemetry.addData("count",count);
        telemetry.update();

        if(gamepad2.a)
        {
            bot.intakeServo.setPower(-.5);
        }

        if(gamepad2.b)
        {
            bot.intakeServo.setPower(0);
        }
        if(gamepad2.y) {
            bot.intakeServo.setPower(.5);
        }

        bot.slideMotor.setPower(gamepad2.right_stick_y);

        //triggers return -1.0 when up and 1.0 when down
        if(gamepad1.right_trigger>0){
            bot.hangMotor.setPower(-gamepad1.right_trigger);
        }
        else if(gamepad1.left_trigger>0){
            bot.hangMotor.setPower(gamepad1.left_trigger);
        }
        else{
            bot.hangMotor.setPower(0);
        }
        bot.leverMotorRight.setPower(gamepad2.left_stick_y);
        bot.leverMotorLeft.setPower(-gamepad2.left_stick_y);

    }
}
