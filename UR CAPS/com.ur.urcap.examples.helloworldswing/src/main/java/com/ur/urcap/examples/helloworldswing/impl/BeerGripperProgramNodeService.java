package com.ur.urcap.examples.beergripperswing.impl;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

import java.util.Locale;

public class BeerGripperProgramNodeService
		implements SwingProgramNodeService<BeerGripperProgramNodeContribution, BeerGripperProgramNodeView> {

	@Override
	public String getId() {
		return "BeerGripperSwingNode";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		configuration.setChildrenAllowed(true);
	}

	@Override
	public String getTitle(Locale locale) {
		String title = "Beer Gripper";
		if ("ru".equals(locale.getLanguage())) {
			title = "Привет мир";
		} else if ("de".equals(locale.getLanguage())) {
			title = "Hallo Welt";
		}
		return title;
	}

	@Override
	public BeerGripperProgramNodeView createView(ViewAPIProvider apiProvider) {
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		Style style = systemAPI.getSoftwareVersion().getMajorVersion() >= 5 ? new V5Style() : new V3Style();
		return new BeerGripperProgramNodeView(style);
	}

	@Override
	public BeerGripperProgramNodeContribution createNode(ProgramAPIProvider apiProvider,
			BeerGripperProgramNodeView view, DataModel model, CreationContext context) {
		return new BeerGripperProgramNodeContribution(apiProvider, view, model);
	}
}
