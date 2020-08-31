package de.whiletrue.toolsmod.settings.defined;

import java.awt.Color;
import java.util.Optional;

import de.whiletrue.toolsmod.gui.widgets.TmTextfield;
import de.whiletrue.toolsmod.settings.Setting;
import de.whiletrue.toolsmod.util.classes.JavaUtil;

public class SettingColor extends Setting<Color,TmTextfield>{
	
	//Character before the value
	private static final char PRESET_CHARACTER = '#';
	
	@Override
	public boolean handleParse(String value) {
		//Tries to parse the color
		Optional<Color> parse = JavaUtil.getInstance().getColorFromString(value);
		
		//Updates the value
		if(parse.isPresent()) {
			super.value=parse.get();
			return true;
		}
		return false;
	}
	
	@Override
	public String handleSave() {
		return PRESET_CHARACTER+(Integer.toHexString(super.value.getRGB()-0xff000000));
	}

	@Override
	public TmTextfield handleCreateWidget() {
		return new TmTextfield()
		//Only allows floats or empty values
		.setValidator(i->i.isEmpty() || this.handleParse(i));
	}

	@Override
	public float[] handleMoveWidget(int x, int y, int w, int h) {
		return new float[]{
			x+5f,
			y+h-16f,
			w-10f,
			15f
		};
	}

	@Override
	public void handleUpdateWidget(TmTextfield widget) {
		widget.setText(this.handleSave());
	}
}
