package com.ur.urcap.examples.driver.gripper.advancedgripper;

import com.ur.urcap.api.contribution.driver.general.tcp.TCPConfiguration;
import com.ur.urcap.api.contribution.driver.general.userinput.CustomUserInputConfiguration;
import com.ur.urcap.api.contribution.driver.general.userinput.TextComponent;
import com.ur.urcap.api.contribution.driver.general.userinput.UserInput;
import com.ur.urcap.api.contribution.driver.general.userinput.ValueChangedListener;
import com.ur.urcap.api.contribution.driver.general.userinput.enterableinput.StringUserInput;
import com.ur.urcap.api.contribution.driver.general.userinput.selectableinput.ElementResolver;
import com.ur.urcap.api.contribution.driver.gripper.ContributionConfiguration;
import com.ur.urcap.api.contribution.driver.gripper.GripActionParameters;
import com.ur.urcap.api.contribution.driver.gripper.GripperAPIProvider;
import com.ur.urcap.api.contribution.driver.gripper.GripperConfiguration;
import com.ur.urcap.api.contribution.driver.gripper.GripperContribution;
import com.ur.urcap.api.contribution.driver.gripper.ReleaseActionParameters;
import com.ur.urcap.api.contribution.driver.gripper.SystemConfiguration;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.userinteraction.inputvalidation.InputValidator;
import com.ur.urcap.api.contribution.driver.general.script.ScriptCodeGenerator;
import com.ur.urcap.api.contribution.driver.gripper.capability.GripDetectedParameters;
import com.ur.urcap.api.contribution.driver.gripper.capability.GripperCapabilities;
import com.ur.urcap.api.contribution.driver.gripper.capability.GripperFeedbackCapabilities;
import com.ur.urcap.api.contribution.driver.gripper.capability.ReleaseDetectedParameters;
import com.ur.urcap.api.domain.resource.ControllableResourceModel;
import com.ur.urcap.api.domain.value.simple.Force;
import com.ur.urcap.api.domain.value.simple.Length;
import com.ur.urcap.api.domain.value.simple.Pressure;
import com.ur.urcap.api.domain.value.simple.Speed;

import javax.swing.ImageIcon;
import java.util.Arrays;
import java.util.Locale;


public class CustomUserInputs implements GripperContribution {

	private static final String GRIPPER_NAME = "BeerGripper";

	private static final ImageIcon CONNECTED_ICON = new ImageIcon(CustomUserInputs.class.getResource("/logo/connected.png"));
	private static final ImageIcon DISCONNECTED_ICON = new ImageIcon(CustomUserInputs.class.getResource("/logo/disconnected.png"));

	private TextComponent ipStatus;
	private StringUserInput ipAddress;
	private UserInput<Integer> port;

	@Override
	public String getTitle(Locale locale) {
		return GRIPPER_NAME;
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// configuration.setLogo(new ImageIcon(getClass().getResource("/logo/logo.png")));
	}

	@Override
	public void configureGripper(GripperConfiguration gripperConfiguration, GripperAPIProvider gripperAPIProvider) {
		GripperCapabilities gripperCapabilities = gripperConfiguration.getGripperCapabilities();

		registerForce(gripperCapabilities);
		registerWidth(gripperCapabilities);
		registerSpeed(gripperCapabilities);

		GripperFeedbackCapabilities fc = gripperConfiguration.getGripperFeedbackCapabilities();

		fc.registerGripDetectedCapability(new ScriptCodeGenerator<GripDetectedParameters>() {
			@Override
			public void generateScript(ScriptWriter scriptWriter, GripDetectedParameters parameters) {
				
			}
		});

		fc.registerReleaseDetectedCapability(new ScriptCodeGenerator<ReleaseDetectedParameters>() {
			@Override
			public void generateScript(ScriptWriter scriptWriter, ReleaseDetectedParameters parameters) {

			}
		});
	}

	@Override
	public void configureInstallation(CustomUserInputConfiguration configuration,
									  SystemConfiguration systemConfiguration,
									  TCPConfiguration tcpConfiguration,
									  GripperAPIProvider gripperAPIProvider) {
		configuration.setDescriptionText("Configure IP-address and port.");			// Decription (Top of the screen)
		customizeInstallationScreen(configuration);

		ControllableResourceModel resourceModel = systemConfiguration.getControllableResourceModel();
		resourceModel.requestControl(new ToolIOController());
	}

	private void customizeInstallationScreen(CustomUserInputConfiguration configurationUIBuilder) {
		registerIPAddressInput(configurationUIBuilder);

		ipStatus = configurationUIBuilder.addText("Connection State:", "");
		updateConnectionStatusTextAndIcon(ipAddress.getValue());

		registerPortInput(configurationUIBuilder);
	}

	private void registerIPAddressInput(CustomUserInputConfiguration configuration) {
		ipAddress = configuration.registerIPAddressInput("ipAddress", "IP-address", "10.10.10.10");
		ipAddress.setErrorValidator(new InputValidator<String>() {
			@Override
			public boolean isValid(String value) {
				// Custom validation of the IP address
				if ("0.0.0.0".equals(value)) {		// TODO: Make so reserved ip's cant be used
					return false;
				}

				return true;
			}

			@Override
			public String getMessage(String value) {
				return "IP-address cannot be: " + value;
			}
		});
		ipAddress.setValueChangedListener(new ValueChangedListener<String>() {		// TODO: Establish connection when a change is registered 
			@Override
			public void onValueChanged(String value) {
				updateConnectionStatusTextAndIcon(value);
				System.out.println("IP-address changed to: " + value);
			}
		});
	}

	private void registerPortInput(CustomUserInputConfiguration configuration) {
		port = configuration.registerIntegerInput("port", "Port", "", 10000, 0, 65535);
		
		port.setValueChangedListener(new ValueChangedListener<Integer>() {
			@Override
			public void onValueChanged(Integer value) {		// TODO: Establish connection when a change is registered 
				System.out.println("Port changed to: " + value);
			}
		});
	}

	private void updateConnectionStatusTextAndIcon(String ipAddress) {
		if (pingIpAddress(ipAddress)) {
			ipStatus.setText("Connected");
			ipStatus.setIcon(CONNECTED_ICON);
		} else {
			ipStatus.setText("Not connected");
			ipStatus.setIcon(DISCONNECTED_ICON);
		}
	}

	private boolean pingIpAddress(String ipAddress) {		// TODO: Make a legit check
		// "Simulate" pinging the entered IP address using the 1st segment of the IP address
		String[] splitArray = ipAddress.split("\\.");
		int firstSegmentOfIpAddress = Integer.parseInt(splitArray[0]);

		// The result of the ping operation will be successful, if the first of the IP address is an even number
		return firstSegmentOfIpAddress % 2 == 0;
	}

	@Override
	public void generatePreambleScript(ScriptWriter scriptWriter) {
		// Intentionally left empty
	}

	@Override
	public void generateGripActionScript(ScriptWriter scriptWriter, GripActionParameters gripActionParameters) {	// TODO: Use connection to send grip values
		// Intentionally left empty
		System.out.println("Grip action :" + printCapabilityParameters(gripActionParameters));

	}
	
	@Override
	public void generateReleaseActionScript(ScriptWriter scriptWriter, ReleaseActionParameters releaseActionParameters) {	// TODO: Use connection to send release values
		// Intentionally left empty
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