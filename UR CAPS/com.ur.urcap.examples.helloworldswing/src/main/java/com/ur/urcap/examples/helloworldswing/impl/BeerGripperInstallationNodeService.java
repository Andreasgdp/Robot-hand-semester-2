package com.ur.urcap.examples.beergripperswing.impl;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

import java.util.Locale;

public class BeerGripperInstallationNodeService implements
		SwingInstallationNodeService<BeerGripperInstallationNodeContribution, BeerGripperInstallationNodeView> {

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
	}

	@Override
	public String getTitle(Locale locale) {
		return "Beer Gripper";
	}

	@Override
	public BeerGripperInstallationNodeView createView(ViewAPIProvider apiProvider) {
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		Style style = systemAPI.getSoftwareVersion().getMajorVersion() >= 5 ? new V5Style() : new V3Style();
		return new BeerGripperInstallationNodeView(style);
	}

	@Override
	public BeerGripperInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			BeerGripperInstallationNodeView view, DataModel model, CreationContext context) {
		return new BeerGripperInstallationNodeContribution(apiProvider, model, view);
	}
}
