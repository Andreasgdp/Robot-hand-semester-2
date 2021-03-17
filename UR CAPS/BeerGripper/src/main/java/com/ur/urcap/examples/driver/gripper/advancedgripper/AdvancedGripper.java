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
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "getTitle()");
		return GRIPPER_NAME;
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "configureContribution()");
		configuration.setLogo(new ImageIcon(getClass().getResource("/logo/logo.png")));
	}

	@Override
	public void configureGripper(GripperConfiguration gripperConfiguration, GripperAPIProvider gripperAPIProvider) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "configureGripper()");
		GripperCapabilities gripperCapabilities = gripperConfiguration.getGripperCapabilities();

		registerForce(gripperCapabilities);
		registerWidth(gripperCapabilities);
		registerVacuum(gripperCapabilities);
		registerSpeed(gripperCapabilities);

		GripperFeedbackCapabilities fc = gripperConfiguration.getGripperFeedbackCapabilities();

		fc.registerGripDetectedCapability(new ScriptCodeGenerator<GripDetectedParameters>() {
			@Override
			public void generateScript(ScriptWriter scriptWriter, GripDetectedParameters parameters) {
				scriptWriter.appendLine("return get_standard_digital_in(0)");
			}
		});

		fc.registerReleaseDetectedCapability(new ScriptCodeGenerator<ReleaseDetectedParameters>() {
			@Override
			public void generateScript(ScriptWriter scriptWriter, ReleaseDetectedParameters parameters) {
				scriptWriter.appendLine("return get_standard_digital_in(1)");
			}
		});
	}

	@Override
	public void configureInstallation(CustomUserInputConfiguration configurationUIBuilder, SystemConfiguration systemConfiguration,
									  TCPConfiguration tcpConfiguration, GripperAPIProvider gripperAPIProvider) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "configureInstallation()");
		ControllableResourceModel resourceModel = systemConfiguration.getControllableResourceModel();

		resourceModel.requestControl(new ToolIOController());
	}

	@Override
	public void generatePreambleScript(ScriptWriter scriptWriter) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "generatePreambleScript()");
		// Intentionally left empty
	}

	@Override
	public void generateGripActionScript(ScriptWriter scriptWriter, GripActionParameters gripActionParameters) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "generateGripActionScript()");
		System.out.println("Grip action :" + printCapabilityParameters(gripActionParameters));
	}

	@Override
	public void generateReleaseActionScript(ScriptWriter scriptWriter, ReleaseActionParameters releaseActionParameters) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "generateReleaseActionScript()");
		System.out.println("Release action :" + printCapabilityParameters(releaseActionParameters));
	}

	private void registerWidth(GripperCapabilities capability) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "registerWidth()");
		capability.registerWidthCapability(40, 100, 50, 60, Length.Unit.MM);
	}

	private void registerForce(GripperCapabilities capability) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "registerForce()");
		capability.registerGrippingForceCapability(0, 100, 40, Force.Unit.N);
	}

	private void registerVacuum(GripperCapabilities capability) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "registerVacuum()");
		capability.registerGrippingVacuumCapability(0, 100, 70, Pressure.Unit.KPA);
	}

	private void registerSpeed(GripperCapabilities capability) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "registerSpeed()");
		capability.registerSpeedCapability(0, 100, 40, 50, Speed.Unit.MM_S);
	}

	private String printCapabilityParameters(GripActionParameters gripActionParameters) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "printCapabilityParameters(GripActionParameters gripActionParameters)");
		return "\n" +
				printWidthCapabilityParameter(gripActionParameters.getWidth()) + "\n" +
				printSpeedCapabilityParameter(gripActionParameters.getSpeed()) + "\n" +
				printForceCapabilityParameter(gripActionParameters.getForce()) + "\n" +
				printVacuumCapabilityParameter(gripActionParameters.getVacuum()) + "\n";
	}

	private String printCapabilityParameters(ReleaseActionParameters releaseActionParameters) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "printCapabilityParameters(ReleaseActionParameters releaseActionParameters)");
		return "\n" +
				printWidthCapabilityParameter(releaseActionParameters.getWidth()) + "\n" +
				printSpeedCapabilityParameter(releaseActionParameters.getSpeed()) + "\n";
	}

	String printWidthCapabilityParameter(Length width) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "printWidthCapabilityParameter()");
		return "Width: " + width.getAs(Length.Unit.MM) + " mm";
	}

	String printSpeedCapabilityParameter(Speed speed) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "printSpeedCapabilityParameter()");
		return "Speed: " + speed.getAs(Speed.Unit.MM_S) + " mm/s";
	}

	String printForceCapabilityParameter(Force force) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "printForceCapabilityParameter()");
		return "Force: " + force.getAs(Force.Unit.N) + " N";
	}

	String printVacuumCapabilityParameter(Pressure vacuum) {
		Logging logger = new Logging();
        logger.logCodeRun("AdvancedGripper", "printVacuumCapabilityParameter()");
		return "Vacuum: " + vacuum.getAs(Pressure.Unit.KPA) + " kPa";
	}
}