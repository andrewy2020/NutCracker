package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot.Mode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.RobotProcessor.RobotProcessor;

@Autonomous(name = "NewarkDepot", group = "tensor")
public class NewarkDepot extends LinearOpMode {

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {
        RobotProcessor proc = new RobotProcessor(this,hardwareMap,Mode.Auto,telemetry);

        proc.displayINIT();
        waitForStart();

        proc.identifyLocation();
        telemetry.addData("location", proc.locationMineral);

        proc.descend();
        proc.driveTrainProcessor.goAngle(4,90,.3);
        proc.driveTrainProcessor.goAngle(4,180 ,.3);
        proc.driveTrainProcessor.goAngle(3,-90,.3);
        proc.turntoGold();
        proc.intakeProcessor.intakeOn();
        proc.driveTrainProcessor.goAngle(35,90,.3);
        sleep(2000);
        proc.intakeProcessor.intakeOff();
        proc.driveTrainProcessor.align(-45);
        proc.driveTrainProcessor.goAngle(15,180,.3);
        proc.dropMarker();
        proc.driveTrainProcessor.align(135);
        proc.driveTrainProcessor.goAngle(150,90,.8);
    }
}
