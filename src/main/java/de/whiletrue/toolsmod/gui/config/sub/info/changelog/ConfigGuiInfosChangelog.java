package de.whiletrue.toolsmod.gui.config.sub.info.changelog;

import java.util.Arrays;

import de.whiletrue.toolsmod.gui.TmScreen;
import de.whiletrue.toolsmod.gui.config.ConfigGui;
import de.whiletrue.toolsmod.gui.widgets.TmButton;
import de.whiletrue.toolsmod.gui.widgets.TmTextWidget;
import de.whiletrue.toolsmod.gui.widgets.rounding.listscaleable.ScaleableListView;
import de.whiletrue.toolsmod.mod.Toolsmod;
import de.whiletrue.toolsmod.util.TextAlign;

public class ConfigGuiInfosChangelog extends TmScreen{

	//List with all changes
	private ScaleableListView<LogView> list;
	
	public ConfigGuiInfosChangelog() {
		//Map all logs to the views
		LogView[] logs = Arrays.stream(Toolsmod.getInstance().getUpdater().getChangelog().getEntries())
				.map(LogView::new)
				.toArray(LogView[]::new);
		
		//Creates the list
		this.list = new ScaleableListView<LogView>(0, 0, 0, 0)
				.setSpaceY(10)
				.setItems(logs);
	}
	
	@Override
	protected void init() {
		//Adds the list
		this.addWidget(this.list);
		
		//Positions the list
		this.list.move((int) (this.width * .05), 50, (int) (this.width * .9), this.height - 55);
		
		//Adds the changelog title
		this.addWidget(new TmTextWidget(this.width/2, 20, null, 0xff0093ff)
				.setAlignX(TextAlign.MIDDLE)//Sets the x align
				.setAlignY(TextAlign.MIDDLE)//Sets the y align
				.setScale(2)//Updates the scale
				.setTextByKey("gui.config.info.changelog"));//Sets the text
		

		// Adds the close button
		this.addWidget(
				new TmButton(this.width - 55, 10, 50, 20, "", i -> this.onClose()).setDisplayByKey("gui.config.changelog.done"));

	}
	
	@Override
	public void render(int mX, int mY, float ticks) {
		//Renders the background
		this.renderDirtBackground(0);
		
		//Renders the dark background
		fill(0, 40, this.width, this.height, 0xa0000000);
		super.render(mX, mY, ticks);
	}
	
	@Override
	public void onClose() {
		//Opens the config screen
		ConfigGui.CONFIG_SCREENS.open();
	}
}
