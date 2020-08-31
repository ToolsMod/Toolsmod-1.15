package de.whiletrue.toolsmod.gui.inject.screen;

import de.whiletrue.toolsmod.gui.config.ConfigGui;
import de.whiletrue.toolsmod.gui.inject.ScreenInjector;
import de.whiletrue.toolsmod.mod.Toolsmod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;

public class MainMenuInjector extends ScreenInjector{

	@Override
	public void preInit(Screen screen) {}

	@Override
	public void postInit(Screen screen) {
		screen.addButton(new Button(screen.width - 80, 10, 70, 20, Toolsmod.NAME, btn -> ConfigGui.CONFIG_SCREENS.open()));
	}
}
