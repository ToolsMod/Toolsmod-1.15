package de.whiletrue.toolsmod.settings.defined;

import de.whiletrue.toolsmod.gui.widgets.TmTextfield;
import de.whiletrue.toolsmod.settings.Setting;

public class SettingString extends Setting<String,TmTextfield>{

	@Override
	public boolean handleParse(String value) {
		this.value=value;
		return true;
	}
	
	@Override
	public String handleSave() {
		return super.value;
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
