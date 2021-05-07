package com;

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

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import javax.swing.ImageIcon;
import java.util.Arrays;
import java.util.Locale;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class CustomUserInputs implements GripperContribution {

	private static final String GRIPPER_NAME = "BeerGripper";

	private static final ImageIcon CONNECTED_ICON = new ImageIcon(CustomUserInputs.class.getResource("/logo/connected.png"));
	private static final ImageIcon DISCONNECTED_ICON = new ImageIcon(CustomUserInputs.class.getResource("/logo/disconnected.png"));

	private TextComponent ipStatus;
	private StringUserInput ipAddress;
	private UserInput<Integer> port;
	private XmlRpcClient client;


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
	}

	private void customizeInstallationScreen(CustomUserInputConfiguration configurationUIBuilder) {
		registerIPAddressInput(configurationUIBuilder);

		ipStatus = configurationUIBuilder.addText("Connection State:", "");
		updateConnectionStatusTextAndIcon(ipAddress.getValue());

		registerPortInput(configurationUIBuilder);
	}

	private void registerIPAddressInput(CustomUserInputConfiguration configuration) {
		ipAddress = configuration.registerIPAddressInput("ipAddress", "IP-address", "127.0.0.0");
		ipAddress.setErrorValidator(new InputValidator<String>() {
			@Override
			public boolean isValid(String value) {
				// Custom validation of the IP address
				if ("0.0.0.0".equals(value)) {
					return false;
				}

				return true;
			}

			@Override
			public String getMessage(String value) {
				return "IP-address cannot be: " + value;
			}
		});
		ipAddress.setValueChangedListener(new ValueChangedListener<String>() {
			@Override
			public void onValueChanged(String value) {
				updateConnectionStatusTextAndIcon(value);
				System.out.println("IP-address changed to: " + value);

			}
		});
	}

	private void registerPortInput(CustomUserInputConfiguration configuration) {
		port = configuration.registerIntegerInput("port", "Port", "", 5000, 0, 65535);
		
		port.setValueChangedListener(new ValueChangedListener<Integer>() {
			@Override
			public void onValueChanged(Integer value) {
				updateConnectionStatusTextAndIcon(String.valueOf(value));
				System.out.println("Port changed to: " + value);
			}
		});
	}

	private void updateConnectionStatusTextAndIcon(String value) {
		try {		
			if (isReachable(ipAddress.getValue(), port.getValue())) {
				ipStatus.setText("Connected");
				ipStatus.setIcon(CONNECTED_ICON);
			} else {
				ipStatus.setText("Not connected");
				ipStatus.setIcon(DISCONNECTED_ICON);
			}
			
		} catch (Exception e) {
			System.out.println("Failed: " + e);
		}
	}

	public boolean isReachable(String host, int port) {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setEnabledForExtensions(true);
		try {
			config.setServerURL(new URL("http://" + host + ":" + port + "/RPC2"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		config.setConnectionTimeout(1000); //1s
		client = new XmlRpcClient();
		client.setConfig(config);
		try {
			client.execute("connCheck", new ArrayList<String>());
			return true;
		} catch (XmlRpcException e) {
			return false;
		}
	}

	@Override
	public void generatePreambleScript(ScriptWriter scriptWriter) {
		scriptWriter.assign("XMLRPC_VARIABLE", "rpc_factory(\"xmlrpc\", \"http://" + ipAddress.getValue() + ":" + port.getValue() + "/RPC2\")");
	}
	
	@Override
	public void generateGripActionScript(ScriptWriter scriptWriter, GripActionParameters gripActionParameters) {
		scriptWriter.appendLine("XMLRPC_VARIABLE.grip(\""+ gripActionParameters.getWidth() + "\", \"" + gripActionParameters.getSpeed().getAs(Speed.Unit.MM_S) + " %" + "\", \"" + gripActionParameters.getForce() + "\")");
		System.out.println("Grip action :" + printCapabilityParameters(gripActionParameters));
	}
	
	@Override
	public void generateReleaseActionScript(ScriptWriter scriptWriter, ReleaseActionParameters releaseActionParameters) {
		scriptWriter.appendLine("XMLRPC_VARIABLE.release(\""+ releaseActionParameters.getWidth() + "\", \"" + releaseActionParameters.getSpeed().getAs(Speed.Unit.MM_S) + " %" + "\")");
		System.out.println("Release action :" + printCapabilityParameters(releaseActionParameters));
	}

	private void registerWidth(GripperCapabilities capability) {
		capability.registerWidthCapability(40, 100, 50, 60, Length.Unit.MM);
	}
	
	private void registerSpeed(GripperCapabilities capability) {
		capability.registerSpeedCapability(0, 100, 40, 50, Speed.Unit.MM_S);
	}

	private void registerForce(GripperCapabilities capability) {
		capability.registerGrippingForceCapability(0, 100, 40, Force.Unit.N);
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
		return speed.getAs(Speed.Unit.MM_S) + " %";
	}

	String printForceCapabilityParameter(Force force) {
		return force.getAs(Force.Unit.N) + " N";
	}
}