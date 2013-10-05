package jab.lejos.liverobots.brity.fsm.states;

import jab.lejos.liverobots.brity.fsm.BrityFSM;
import jab.lejos.liverobots.brity.model.Robot;

import org.apache.log4j.Logger;

public class DetectWall {

	Logger logger = Logger.getLogger(DriveForward.class);
	
	int voltage = 0;
	int voltageThreshold = 200;
	
	public DetectWall(BrityFSM brityFSM, Robot robot){
		logger.info("STATE: DetectWall");
		
		voltage = robot.getVoltage();
		logger.info("Voltage: " + voltage);
		if(voltage < voltageThreshold){
			logger.info("STATE: Disconnect");
			brityFSM.fireEvent("lowBattery");
		}
		
		logger.info("Go backward");
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		logger.info("Turn left");
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		
		brityFSM.fireEvent("continueDriving");
		
	}
}