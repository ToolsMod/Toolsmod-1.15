package de.whiletrue.toolsmod.module.defined.world;

import de.whiletrue.toolsmod.module.ModuleCategory;
import de.whiletrue.toolsmod.module.defined.Module;
import net.minecraftforge.event.TickEvent.ClientTickEvent;

public class ModuleFastplace extends Module{

	public ModuleFastplace() {
		super("Fastplace", ModuleCategory.WORLD, true);
	}
	
	@Override
	public void onTick(ClientTickEvent event) {
		//Resets the block click delay
		this.mc.rightClickDelayTimer=0;
	}

}
