package de.whiletrue.toolsmod.settings.defined;

import java.util.Optional;

import de.whiletrue.toolsmod.gui.widgets.TmTextfield;
import de.whiletrue.toolsmod.settings.Setting;
import de.whiletrue.toolsmod.util.classes.ItemUtil;
import net.minecraft.block.Block;

public class SettingBlock extends Setting<Block,TmTextfield>{

	@Override
	public String handleSave() {
		return super.value.getTranslationKey().substring(6).replace(".", ":");
	}

	@Override
	public boolean handleParse(String value) {
		//Loads the block from its name
		Optional<Block> parse = ItemUtil.getInstance().getBlockFromName(value);
		
		//Updates the value
		if(parse.isPresent()) {
			this.value=parse.get();
			return true;
		}
		return false;
		
	}

	@Override
	public TmTextfield handleCreateWidget() {
		return new TmTextfield()
		//Only allows doubles or empty values
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
