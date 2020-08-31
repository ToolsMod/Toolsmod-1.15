package de.whiletrue.toolsmod.module.defined.world;

import de.whiletrue.toolsmod.module.ModuleCategory;
import de.whiletrue.toolsmod.module.defined.Module;
import net.minecraftforge.event.TickEvent.ClientTickEvent;

public class ModuleFastbreak extends Module{

	public ModuleFastbreak() {
		super("Fastbreak", ModuleCategory.WORLD, true);
	}
	
	@Override
	public void onTick(ClientTickEvent event) {
		//Resets the block hit delay
        this.mc.playerController.blockHitDelay=0;
	}

}