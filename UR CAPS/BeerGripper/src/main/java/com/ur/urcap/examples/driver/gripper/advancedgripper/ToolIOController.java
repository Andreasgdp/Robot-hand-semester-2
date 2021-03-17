package com.ur.urcap.examples.driver.gripper.advancedgripper;

import com.ur.urcap.api.domain.resource.tooliointerface.ToolIOInterface.OutputVoltage;
import com.ur.urcap.api.domain.resource.tooliointerface.control.ToolIOInterfaceControlEvent;
import com.ur.urcap.api.domain.resource.tooliointerface.control.ToolIOInterfaceControllable;
import com.ur.urcap.api.domain.resource.tooliointerface.control.ToolIOInterfaceController;


class ToolIOController implements ToolIOInterfaceController {

	private static final OutputVoltage REQUIRED_VOLTAGE = OutputVoltage.OUTPUT_VOLTAGE_24V;
	private static final OutputVoltage SHUTDOWN_VOLTAGE = OutputVoltage.OUTPUT_VOLTAGE_0V;

	private ToolIOInterfaceControllable controllableInstance;

	@Override
	public void onControlGranted(ToolIOInterfaceControlEvent event) {
		Logging logger = new Logging();
        logger.logCodeRun("ToolIOController", "onControlGranted()");
		controllableInstance = event.getControllableResource();
		powerOnGripper();
	}

	@Override
	public void onControlToBeRevoked(ToolIOInterfaceControlEvent event) {
		Logging logger = new Logging();
        logger.logCodeRun("ToolIOController", "onControlToBeRevoked()");
		// Shutdown device "gracefully"
		shutDownGripper();
	}

	private void powerOnGripper() {
		Logging logger = new Logging();
        logger.logCodeRun("ToolIOController", "powerOnGripper()");
		controllableInstance.setOutputVoltage(REQUIRED_VOLTAGE);
	}

	private void shutDownGripper() {
		Logging logger = new Logging();
        logger.logCodeRun("ToolIOController", "shutDownGripper()");
		controllableInstance.setOutputVoltage(SHUTDOWN_VOLTAGE);
	}
}
