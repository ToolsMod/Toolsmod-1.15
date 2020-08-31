package de.whiletrue.toolsmod.gui.inject;

import net.minecraft.client.gui.screen.Screen;

public abstract class ScreenInjector{

	/**
	 * Executes before the screen inits
	 * @param screen the screen
	 */
	public abstract void preInit(Screen screen);
	
	/**
	 * Executes after the screen inits
	 * @param screen
	 */
	public abstract void postInit(Screen screen);
}
