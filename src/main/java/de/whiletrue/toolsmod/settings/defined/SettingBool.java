package de.whiletrue.toolsmod.settings.defined;

import de.whiletrue.toolsmod.gui.widgets.TmCheckbox;
import de.whiletrue.toolsmod.settings.Setting;

public class SettingBool extends Setting<Boolean,TmCheckbox>{

	@Override
	public String handleSave(){
		return super.value.toString();
	}

	@Override
	public boolean handleParse(String value) {
		try {
			super.value = Boolean.valueOf(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public TmCheckbox handleCreateWidget() {
		return new TmCheckbox(0,0,false,i->this.value=i);
	}

	@Override
	public float[] handleMoveWidget(int x, int y, int w, int h) {
		return new float[]{
			x+5f,
			y+h-20f,
			20,
			20
		};
	}

	@Override
	public void handleUpdateWidget(TmCheckbox widget) {
		widget.setChecked(this.value);
	}

	
}
