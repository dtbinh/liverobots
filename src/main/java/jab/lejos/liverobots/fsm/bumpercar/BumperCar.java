package jab.lejos.liverobots.fsm.bumpercar;

import jab.lejos.liverobots.brity.model.Robot;
import jab.lejos.liverobots.brity.model.RobotFactory;
import jab.lejos.liverobots.brity.model.RobotFactoryException;
import jab.lejos.liverobots.brity.model.RobotType;

import jab.lejos.liverobots.fsm.FSM;
import jab.lejos.liverobots.fsm.bumpercar.states.DriveForward;
import jab.lejos.liverobots.fsm.bumpercar.states.DetectWall;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

/**
 * 
 * Based on this example:
 * http://commons.apache.org/proper/commons-scxml/xref-test/org/apache/commons/scxml/env/StopWatch.html
 *  
 * @author jabrena
 *
 */
public class BumperCar extends FSM{

	Logger logger = Logger.getLogger(BumperCar.class);
	
	private static final String SCXML_CONFIG = "./lib/BumperCar.scxml";

	private Robot robot;
	
	public BumperCar(RobotType rt) throws MalformedURLException, RobotFactoryException{
		super(new File(SCXML_CONFIG).toURI().toURL());
		robot = RobotFactory.getRobot(rt);
	}
	
	public void autoFireEvent() {
		this.fireEvent(this.getStatus().toString());
	}
	
	public Robot getRobot(){
		return this.robot;
	}
	
	private Transitions status = null;
	
	public Transitions getStatus(){
		return status;
	}

	public void setStatus(Transitions status){
		this.status = status;
	}
	
	//FSM Methods
	public void Iddle() {
		Logger logger = Logger.getLogger(BumperCar.class);
		logger.info("STATE: Iddle");
	}
	
	int voltage = 0;
	int voltageThreshold = 200;
	int distance = 0;
	int distanceThreshold = 100;
	
	public void DriveForward() {
		logger.info("STATE: DriveForward");

		DriveForward df = new DriveForward(this);
		df.action();
	}
	
	public void DetectWall() {
		logger.info("STATE: DetectWall");
		
		DetectWall dw = new DetectWall(this);
		dw.action();
	}
	
	public void Disconnect() {
		logger.info("STATE: Disconnected");
	}
	
}
