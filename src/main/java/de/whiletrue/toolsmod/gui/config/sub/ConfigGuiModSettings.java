package de.whiletrue.toolsmod.gui.config.sub;

import de.whiletrue.toolsmod.gui.config.ConfigGui;
import de.whiletrue.toolsmod.gui.config.sub.settings.SettingView;
import de.whiletrue.toolsmod.gui.widgets.TmTextfield;
import de.whiletrue.toolsmod.gui.widgets.preset.TmWidget;
import de.whiletrue.toolsmod.gui.widgets.rounding.listmultirow.MultirowListView;
import de.whiletrue.toolsmod.mod.Toolsmod;
import de.whiletrue.toolsmod.settings.Setting;

public class ConfigGuiModSettings extends ConfigGui{

	//Search field
	private TmTextfield searchField = new TmTextfield(0, 0, 0, 0, "", txt->this.settingsList.updateValidation())
			.setPresetStringByKey("gui.config.modsettings.search");

	//List with all settings
	private MultirowListView<Setting<?,? extends TmWidget>, SettingView<?>> settingsList = new MultirowListView<Setting<?,? extends TmWidget>, SettingView<?>>(0,0,0,0)
			.setListFormatting(0, 15, 1, 30)
			.setScrollStrength(5)
			.setBackground(0x50000000)
			.setItems(
				Toolsmod.getInstance().getSettingsManager().getSettings().stream().map(i->new SettingView<>(i)).toArray(SettingView[]::new)
			).setValidator(i->i.isValid(this.searchField.getText()));
	
	public ConfigGuiModSettings() {
		super("gui.config.nav.modsettings");
	}
	
	@Override
	protected void init() {
		super.init();
		
		//Adds the listview
		this.addWidget(this.settingsList);
		
		//Updates the list widgets
		this.settingsList.getViews().forEach(i->i.handleUpdateWidget());

		//Positions the list view
		this.settingsList.move(
			this.width/2-100,
			this.startY+40,
			200,
			this.height-this.startY-40
		);
		
		//Positions the search field
		this.searchField.move(
			this.width/2-90,
			this.startY+12,
			180,
			16
		);
		
		//Adds the text search field
		this.addWidget(this.searchField);
	}

	@Override
	public void onClose() {
		//Saves the settings
		Toolsmod.getInstance().getSettingsManager().save();
		super.onClose();
	}
	
}
