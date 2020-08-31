package de.whiletrue.toolsmod.module.defined.movement;

import de.whiletrue.toolsmod.module.ModuleCategory;
import de.whiletrue.toolsmod.module.defined.Module;
import net.minecraftforge.event.TickEvent.ClientTickEvent;

public class ModuleSprint extends Module{

	public ModuleSprint() {
		super("Sprint", ModuleCategory.MOVEMENT, true);
	}

	@Override
	public void onTick(ClientTickEvent event) {
		//Checks if the player can spring
        if(this.mc.player.moveForward>0 &&
                !this.mc.player.collidedHorizontally &&
                !this.mc.player.isSneaking() &&
                !this.mc.player.isOnLadder()){

            //Sets the player sprinting
            this.mc.player.setSprinting(true);
        }
	}
}
