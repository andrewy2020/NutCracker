package org.firstinspires.ftc.teamcode.Misc;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Robotics on 9/22/2017.
 */
public class Hardwaremap{
    public DcMotor motorRF;
    public DcMotor motorLF;
    public DcMotor motorLB;
    public DcMotor motorRB;
    public Servo servo1;
    public Servo servo2;
    public ColorSensor color;
    public ModernRoboticsI2cRangeSensor range;
    public HardwareMap hwMap = null;
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;

    public void init(HardwareMap ahwmap) {
        hwMap = ahwmap;
        motorRF = hwMap.dcMotor.get("motorRF");
        motorLF = hwMap.dcMotor.get("motorLF");
        motorLB = hwMap.dcMotor.get("motorLB");
        motorRB = hwMap.dcMotor.get("motorRB");
        servo1 = hwMap.servo.get("servo1");
        servo2 = hwMap.servo.get("servo2");
        color = hwMap.get(ModernRoboticsI2cColorSensor.class,"color");
        range = hwMap.get(ModernRoboticsI2cRangeSensor.class,"range");
        motorRF.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLF.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLB.setDirection(DcMotorSimple.Direction.FORWARD);
        motorRB.setDirection(DcMotorSimple.Direction.FORWARD);
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.FORWARD);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }
}
