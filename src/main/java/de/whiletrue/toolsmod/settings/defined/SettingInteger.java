package de.whiletrue.toolsmod.settings.defined;

import de.whiletrue.toolsmod.gui.widgets.TmSlider;
import de.whiletrue.toolsmod.gui.widgets.TmTextfield;
import de.whiletrue.toolsmod.gui.widgets.preset.TmWidget;
import de.whiletrue.toolsmod.settings.Setting;

public class SettingInteger extends Setting<Integer,TmWidget>{
	
	//If the widget is a slider
	private boolean isSlider;
	private String sliderSuffix;
	
	//Max an min value of the setting
	private Float max,min;
	
	public SettingInteger max(float value) {
		this.max=value;
		return this;
	}
	public SettingInteger min(float value) {
		this.min=value;
		return this;
	}
	public SettingInteger slider(String suffix) {
		this.isSlider=true;
		this.sliderSuffix=suffix;
		return this;
	}
	
	@Override
	public String handleSave() {
		return super.value.toString();
	}

	@Override
	public boolean handleParse(String value) {
		try {
			//Parses the value
			Integer val = Integer.valueOf(value);
			
			//Checks if a max value is given and if the value is above that
			if(this.max!=null && val>this.max)
				return false;
			
			//Checks if a min value is given and if the value is above that
			if(this.min!=null && val<this.min)
				return false;
			
			//Update the value
			this.value=val;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public TmWidget handleCreateWidget() {
		//If the widget is a slider
		final boolean slider = this.isSlider && this.min!=null && this.max!=null;
		
		//Check which type the widget is
		if(slider)
			return new TmSlider(0, 0, 0, 0, this.min, this.max, this.value, (slid,val)->{
				//Updates the value
				this.value=(int)val;
				//Formats the slider
				return String.valueOf(this.value)+this.sliderSuffix;
			});
		else
			return new TmTextfield()
			//Only allows floats or empty values
			.setValidator(i->i.isEmpty() || this.handleParse(i));
	}

	@Override
	public float[] handleMoveWidget(int x, int y, int w, int h) {
		return new float[]{
			x+5f,
			y+10,
			w-10f,
			15f
		};
	}

	@Override
	public void handleUpdateWidget(TmWidget widget) {
		//Checks if the widget is a slider or a textfield
		if(widget instanceof TmTextfield)
			((TmTextfield)widget).setText(this.handleSave());
		else {
			//Updates the value and the display
			((TmSlider)widget)
			.setByValue(this.value)
			.updateDisplay();
		}
	}

	
}
