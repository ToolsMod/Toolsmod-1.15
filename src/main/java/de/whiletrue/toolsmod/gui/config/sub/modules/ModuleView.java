package de.whiletrue.toolsmod.gui.config.sub.modules;

import de.whiletrue.toolsmod.gui.config.ConfigSettingsButton;
import de.whiletrue.toolsmod.gui.widgets.TmCheckbox;
import de.whiletrue.toolsmod.gui.widgets.preset.TmWidget;
import de.whiletrue.toolsmod.gui.widgets.rounding.listmultirow.MultirowListItem;
import de.whiletrue.toolsmod.module.defined.Module;

public class ModuleView extends MultirowListItem<Module> {

	public ModuleView(Module item) {
		super(item);

		// Adds the settings button
		this.widgets.add(new ConfigSettingsButton(0, 0, i -> item.getQuickAccessGui().open())
				//Enabled the settings if the settings gui exists
				.setEnabled(this.item.getQuickAccessGui() != null)
						.setTooltippByKey("settings.button"));
		
		//Adds the allowed widget
		this.widgets.add(new TmCheckbox(0, 0, item.isAllowed(), i->{
			item.setAllowed(i);
			item.disable();
			return i;
		}).setTooltippByKey("gui.config.modules.allowed"));
	}

	@Override
	public void handlChangePosition(int x, int y, int w, int h) {
		super.handlChangePosition(x, y, w, h);

		// Settings button
		this.widgets.get(0).move(x + w - 25, y + h/2-10, 20, 20);
		//Allowed checkbox
		this.widgets.get(1).move(x+w-25-25,y+h/2-10,20,20);
	}

	@Override
	public void render(int mX, int mY, float ticks, TmWidget focused) {
		// If the element is hovered
		boolean hovered = mX >= this.x && mX <= this.x + this.width && mY > this.y && mY < this.y + this.height;

		// Renders the background
		RENDERER.renderRect(this.x, this.y, this.width, this.height, 0xff8C8C8C);
		// Renders the outline
		RENDERER.renderOutline(this.x, this.y, this.width, this.height, 1, hovered ? 0xffff8f00 : 0xffffffff);

		// Renders the text
		GAME.fontRenderer.drawStringWithShadow(this.item.getName(), this.x + 10, this.y + this.height / 2 - 4,
				0xFFffffff);
		super.render(mX, mY, ticks, focused);
	}

	/**
	 * Checks if this item matches the search criteria
	 * 
	 * @param text
	 *            the text
	 * @return if the item matches
	 */
	public boolean isValid(String text) {
		return this.item.getName().toLowerCase().startsWith(text.toLowerCase());
	}
}