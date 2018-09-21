

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/*
	Holonomic concepts from:
	http://www.vexforum.com/index.php/12370-holonomic-drives-2-0-a-video-tutorial-by-cody/0
   Robot wheel mapping:
          X FRONT X
        X           X
      X  FL       FR  X
              X
             XXX
              X
      X  BL       BR  X
        X           X
          X       X
*/
@TeleOp(name = "Full Tele Op", group = "Tele Op")
public class full_tele_op extends OpMode {

    DcMotor motorRight;
    DcMotor motorLeft;
    int driveSwitch = 0;





//    double changeFactor = .9;
//    double turnChangeFactor = .9;

    @Override
    public void init()
    {
        motorRight = hardwareMap.dcMotor.get("right motor");
        motorLeft = hardwareMap.dcMotor.get("left motor");
    }
    @Override
    public void loop() {

        //basic controls being write to robot
        switch (driveSwitch) {
            case 0: {
                motorRight = gamepad1.right_stick_y;
                motorLeft = gamepad1.left_stick_y;
            }
            case 1: {
                ///ok!
            }
        }


//        float gamepad1LeftY = -gamepad1.left_stick_y;
//        float gamepad1LeftX = gamepad1.left_stick_x;
//        float gamepad1RightX = gamepad1.right_stick_x;
//        float gamepad2LeftY = gamepad2.left_stick_y;
//        float gamepad2RightY = gamepad2.right_stick_y;
//        // holonomic formulas
//        float FrontLeft = (float)((-gamepad1LeftY - gamepad1LeftX - gamepad1RightX * turnChangeFactor/*trying to decrease impact of turning*/) * changeFactor); // 9/10ths of the power
//        float FrontRight = (float)((gamepad1LeftY - gamepad1LeftX - gamepad1RightX * turnChangeFactor) * changeFactor);
//        float BackRight = (float)((gamepad1LeftY + gamepad1LeftX - gamepad1RightX * turnChangeFactor) * changeFactor);
//        float BackLeft = (float)((-gamepad1LeftY + gamepad1LeftX - gamepad1RightX * turnChangeFactor) * changeFactor);
//        if(gamepad1.left_stick_button || gamepad1.right_stick_button) { //scaling power of motors
//            if(changeFactor == .9) {
//                changeFactor = .5;
//                turnChangeFactor = .5;
//            }
//            else {
//                changeFactor = .9;
//                turnChangeFactor = .9;
//            }
//        }
//        // clip the right/left values so that the values never exceed +/- 1
//        FrontRight = Range.clip(FrontRight, -1, 1);
//        FrontLeft = Range.clip(FrontLeft, -1, 1);
//        BackLeft = Range.clip(BackLeft, -1, 1);
//        BackRight = Range.clip(BackRight, -1, 1);
//        // write the values to the motors
//        motorFrontRight.setPower(FrontRight);
//        motorFrontLeft.setPower(FrontLeft);
//        motorBackLeft.setPower(BackLeft);
//        motorBackRight.setPower(BackRight);

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", gamepad1LeftX) + " " +
                String.format("%.2f", gamepad1LeftY) + " " +  String.format("%.2f", gamepad1RightX));
        telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", motorLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", motorRight));

    }
    @Override
    public void stop() {
    }
    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
