package de.whiletrue.toolsmod.mod;

import org.lwjgl.glfw.GLFW;

import de.whiletrue.toolsmod.settings.defined.SettingInteger;
import de.whiletrue.toolsmod.settings.defined.SettingKeybind;
import de.whiletrue.toolsmod.util.Keybind;
import net.minecraftforge.client.settings.KeyModifier;

public class ModSettings {
	
	//Speed of which the quick access gui scales
	public static final SettingInteger quickAccessGuiSpeed = new SettingInteger()
			.min(1)
			.max(15)
			.slider("")
			.standard(3)
			.name("qaGuiSpeed");

	//Keybind of the quickaccess gui
	public static final SettingKeybind quickAccessKeybind = new SettingKeybind()
			.standard(new Keybind(GLFW.GLFW_KEY_X, KeyModifier.NONE))
			.name("qaGuiBind");
}
