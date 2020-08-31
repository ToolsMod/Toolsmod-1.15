package de.whiletrue.toolsmod.settings.defined;

import de.whiletrue.toolsmod.gui.widgets.TmKeybindButton;
import de.whiletrue.toolsmod.settings.Setting;
import de.whiletrue.toolsmod.util.Keybind;
import net.minecraftforge.client.settings.KeyModifier;

public class SettingKeybind extends Setting<Keybind, TmKeybindButton>{

	@Override
	public String handleSave() {
		//Saves using the format (KEYCODE-MODIFIERS)
		return String.format("%d-%s",this.value.getKeyCode(),this.value.getModifiers());
	}

	@Override
	public boolean handleParse(String value) {
		try {
			//Splits into the segments
			String[] split = value.split("-");
			
			//Gets the key
			int key = Integer.valueOf(split[0]);
			
			//Gets the modifiers
			KeyModifier mod = KeyModifier.valueOf(split[1]);
			
			//Updates the keybind
			this.value.update(key, mod);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TmKeybindButton handleCreateWidget() {
		return new TmKeybindButton(0, 0, 0, 0, this.value, i->{});
	}

	@Override
	public float[] handleMoveWidget(int x, int y, int w, int h) {
		return new float[] {
			x+5,
			y+h-20,
			80,
			20
		};
	}

	@Override
	public void handleUpdateWidget(TmKeybindButton widget) {
		//Updates the display
		widget.updateDisplay();
	}

	
}
