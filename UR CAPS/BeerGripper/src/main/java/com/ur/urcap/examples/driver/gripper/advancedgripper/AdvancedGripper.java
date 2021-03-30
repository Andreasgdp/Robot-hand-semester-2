package com.ur.urcap.examples.driver.gripper.advancedgripper;

import com.ur.urcap.api.contribution.driver.general.script.ScriptCodeGenerator;
import com.ur.urcap.api.contribution.driver.general.tcp.TCPConfiguration;
import com.ur.urcap.api.contribution.driver.general.userinput.CustomUserInputConfiguration;
import com.ur.urcap.api.contribution.driver.gripper.ContributionConfiguration;
import com.ur.urcap.api.contribution.driver.gripper.GripActionParameters;
import com.ur.urcap.api.contribution.driver.gripper.GripperAPIProvider;
import com.ur.urcap.api.contribution.driver.gripper.GripperConfiguration;
import com.ur.urcap.api.contribution.driver.gripper.GripperContribution;
import com.ur.urcap.api.contribution.driver.gripper.ReleaseActionParameters;
import com.ur.urcap.api.contribution.driver.gripper.SystemConfiguration;
import com.ur.urcap.api.contribution.driver.gripper.capability.GripDetectedParameters;
import com.ur.urcap.api.contribution.driver.gripper.capability.GripperCapabilities;
import com.ur.urcap.api.contribution.driver.gripper.capability.GripperFeedbackCapabilities;
import com.ur.urcap.api.contribution.driver.gripper.capability.ReleaseDetectedParameters;
import com.ur.urcap.api.domain.resource.ControllableResourceModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.value.simple.Force;
import com.ur.urcap.api.domain.value.simple.Length;
import com.ur.urcap.api.domain.value.simple.Pressure;
import com.ur.urcap.api.domain.value.simple.Speed;

import javax.swing.ImageIcon;
import java.util.Locale;

public class AdvancedGripper implements GripperContribution {

	private static final String GRIPPER_NAME = "Advanced Gripper";

	@Override
	public String getTitle(Locale locale) {
		return GRIPPER_NAME;
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		configuration.setLogo(new ImageIcon(getClass().getResource("/logo/logo.png")));
	}

	@Override
	public void configureGripper(GripperConfiguration gripperConfiguration, GripperAPIProvider gripperAPIProvider) {
		GripperCapabilities gripperCapabilities = gripperConfiguration.getGripperCapabilities();

		registerForce(gripperCapabilities);
		registerWidth(gripperCapabilities);
		registerSpeed(gripperCapabilities);

		GripperFeedbackCapabilities fc = gripperConfiguration.getGripperFeedbackCapabilities();
		
		// boolean closeStatus = (GripperStatus.gripperPosStatus == "closed") ? true : false;

		fc.registerGripDetectedCapability(new ScriptCodeGenerator<GripDetectedParameters>() {
			@Override
			public void generateScript(ScriptWriter scriptWriter, GripDetectedParameters parameters) {
				/*
				if (closeStatus) {
					scriptWriter.appendLine("return 1");
				} else {
					scriptWriter.appendLine("return 0");
				}
				*/
			}
		});
		
		boolean openStatus = (GripperStatus.gripperPosStatus == "open") ? true : false;
		fc.registerReleaseDetectedCapability(new ScriptCodeGenerator<ReleaseDetectedParameters>() {
			@Override
			public void generateScript(ScriptWriter scriptWriter, ReleaseDetectedParameters parameters) {
				/*
				if (openStatus) {
					scriptWriter.appendLine("return 1");
				} else {
					scriptWriter.appendLine("return 0");
				}
				*/
			}
		});
	}

	@Override
	public void configureInstallation(CustomUserInputConfiguration configurationUIBuilder,
			SystemConfiguration systemConfiguration, TCPConfiguration tcpConfiguration,
			GripperAPIProvider gripperAPIProvider) {
		ControllableResourceModel resourceModel = systemConfiguration.getControllableResourceModel();

		resourceModel.requestControl(new ToolIOController());
	}

	@Override
	public void generatePreambleScript(ScriptWriter scriptWriter) {
		// GripperStatus.client.reconfigureClient("192.168.0.20", 2050);
		// //this function tries to connect to the Gripper
		// try {
		// 	GripperStatus.client.connect();
		// } catch (Exception e) {
		// 	System.out.println("Cannot connect to Gripper. ERR: " + e);
		// }
	}

	// Added API support for querying the enablement state of the grip/release detection option in the Gripper program node when generating script code for grip and release actions (in calls to the ‘generateGripActionScript(ScriptWriter, GripActionParameters)’ and ‘generateReleaseActionScript(ScriptWriter, ReleaseActionParameters)’ methods in the ‘GripperContribution’ interface):

	@Override
	public void generateGripActionScript(ScriptWriter scriptWriter, GripActionParameters gripActionParameters) {

		// GripperStatus.client.reconnect();
		// writeSuccess = GripperStatus.client.write("Close" + printCapabilityParameters(gripActionParameters));

		// if (writeSuccess) {
		// 	String waitVariable = null;
		// 	while (waitVariable.compareTo("gripperClosed") != 0) {
		// 		waitVariable = GripperStatus.client.read();
		// 	}
		// 	GripperStatus.client.reconnect();
		// }

		// GripperStatus.gripperPosStatus = "closed";

		System.out.println("Grip action :" + printCapabilityParameters(gripActionParameters));
	}

	@Override
	public void generateReleaseActionScript(ScriptWriter scriptWriter, ReleaseActionParameters releaseActionParameters) {
		// GripperStatus.client.reconnect();
		// writeSuccess = GripperStatus.client.write("Open" + printCapabilityParameters(releaseActionParameters));

		// if (writeSuccess) {
		// 	String waitVariable = null;
		// 	while (waitVariable.compareTo("gripperOpened") != 0) {
		// 		waitVariable = GripperStatus.client.read();
		// 	}
		// 	GripperStatus.client.reconnect();
		// }
		// GripperStatus.gripperPosStatus = "open";
		// System.out.println("I did it boss, I did the thing boss");
		
		System.out.println("Release action :" + printCapabilityParameters(releaseActionParameters));
	}

	private void registerWidth(GripperCapabilities capability) {
		capability.registerWidthCapability(40, 100, 50, 60, Length.Unit.MM);
	}

	private void registerForce(GripperCapabilities capability) {
		capability.registerGrippingForceCapability(0, 100, 40, Force.Unit.N);
	}

	private void registerSpeed(GripperCapabilities capability) {
		capability.registerSpeedCapability(0, 100, 40, 50, Speed.Unit.MM_S);
	}

	private String printCapabilityParameters(GripActionParameters gripActionParameters) {
		return "?" + printWidthCapabilityParameter(gripActionParameters.getWidth()) + "?"
				+ printSpeedCapabilityParameter(gripActionParameters.getSpeed()) + "?"
				+ printForceCapabilityParameter(gripActionParameters.getForce()) + "?";
	}

	private String printCapabilityParameters(ReleaseActionParameters releaseActionParameters) {
		return "?" + printWidthCapabilityParameter(releaseActionParameters.getWidth()) + "?"
				+ printSpeedCapabilityParameter(releaseActionParameters.getSpeed()) + "?";
	}

	String printWidthCapabilityParameter(Length width) {
		return width.getAs(Length.Unit.MM) + " mm";
	}

	String printSpeedCapabilityParameter(Speed speed) {
		return speed.getAs(Speed.Unit.MM_S) + " mm/s";
	}

	String printForceCapabilityParameter(Force force) {
		return force.getAs(Force.Unit.N) + " N";
	}
}