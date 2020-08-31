package de.whiletrue.toolsmod.settings.defined;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import de.whiletrue.toolsmod.gui.widgets.TmSliderValues;
import de.whiletrue.toolsmod.settings.Setting;

public class SettingEnum<X extends Enum<?>> extends Setting<X, TmSliderValues<X>>{

	//Function to get the identity of the given enum
	private Function<X, String> onIdentify,
	//Function to get the display for an enum type
	onDisplay;
	
	public SettingEnum(Function<X, String> onIdentify,Function<X, String> onDisplay) {
		this.onIdentify=onIdentify;
		this.onDisplay=onDisplay;
	}
	
	@Override
	public String handleSave() {
		return this.onIdentify.apply(this.value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean handleParse(String value) {
		//Gets all value of the enum
		Optional<?> x = Arrays.stream(this.value.getDeclaringClass().getEnumConstants())
		//Filters for the right value
		.filter(i->this.onIdentify.apply((X) i).equals(value))
		//Gets the enum value
		.findFirst();
		
		//Updates the value if any got found
		x.ifPresent(i->this.value=(X) i);
		
		return x.isPresent();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TmSliderValues<X> handleCreateWidget() {
		//Creates the slider
		return new TmSliderValues<X>(
			0, 0, 0, 0,
			//Gets all enum values
			(X[]) this.value.getDeclaringClass().getEnumConstants(),
			0,
			(val,index)->{
				//Updates the value
				this.value=val;
				//Sets the new display
				return this.onDisplay.apply(val);
			}
		);
	}

	@Override
	public float[] handleMoveWidget(int x, int y, int w, int h) {
		return new float[] {
			x+5f,
			y+h-16f,
			w-10f,
			15f
		};
	}

	@Override
	public void handleUpdateWidget(TmSliderValues<X> widget) {
		widget.setStateByValue(this.value);
		widget.updateDisplay();
	}

}
