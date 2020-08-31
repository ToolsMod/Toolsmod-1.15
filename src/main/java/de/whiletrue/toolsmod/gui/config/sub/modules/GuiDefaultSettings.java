package de.whiletrue.toolsmod.gui.config.sub.modules;

import de.whiletrue.toolsmod.gui.config.ConfigGui;
import de.whiletrue.toolsmod.gui.modules.GuiQAModules;
import de.whiletrue.toolsmod.gui.quickaccess.GuiQuickAccess;
import de.whiletrue.toolsmod.gui.widgets.*;
import de.whiletrue.toolsmod.gui.widgets.preset.TmWidget;
import de.whiletrue.toolsmod.gui.widgets.rounding.listmultirow.MultirowListView;
import de.whiletrue.toolsmod.mod.Toolsmod;
import de.whiletrue.toolsmod.module.defined.Module;
import de.whiletrue.toolsmod.settings.Setting;
import de.whiletrue.toolsmod.util.TextAlign;

public class GuiDefaultSettings extends GuiQuickAccess{

	//If the gui got open ingame or in the main menu
	private boolean isIngame;
	
	//Reference to the module
	private Module mod;
	
	//List with all settings
	private MultirowListView<Setting<?,? extends TmWidget>, SettingView<?>> list = new MultirowListView<Setting<?,? extends TmWidget>, SettingView<?>>(0,0,0,0)
			.setListFormatting(0, 10, 1, 30)
			.setScrollStrength(5);
	
	public GuiDefaultSettings(Module mod) {
		super("#"+mod.getName(),"#"+mod.getName(),320, 190);
		this.mod=mod;
		
		//Adds all items
		this.list.setItems(
			mod.getSettings().stream().map(i-> new SettingView<>(mod,i)).toArray(SettingView[]::new)
		);
	}
	
	@Override
	protected void init() {
		//If the gui got open ingame or in the main menu
		this.isIngame = this.minecraft.world!=null;
		
		if(this.isIngame) {
			
			//Appends the ingame list
			this.addWidgetWithListener(this.list, (x,y)->new float[] {
					this.width/2-x/2+50+20,
					this.height/2-y/2+25,
					x-50-40,
					y-50
			});
			
			//Appends the close button
			this.addWidgetWithListener(new TmButton(0, 0, 0, 0, "", i->this.onClose())
					.setDisplayByKey("settings.back"), (x,y)->new float[] {
				this.width/2,
				this.height/2+y/2-45,
				50,
				20
			});
		}else{
			//Appends the upper view
			this.addWidget(new TmTextWidget(this.width/2, 20, this.mod.getName(), 0xFFffffff)
					.setScale(2)
					.setAlignY(TextAlign.MIDDLE));
			
			//Appends the dark background render
			this.addWidget(new TmBackgroundWidget(0,40,this.width, this.height-40, 0xa0000000));

			//Appends the close button
			this.addWidget(new TmButton(this.width-55, 10, 50, 20,null, i->this.onClose())
					.setDisplayByKey("settings.back"));
			
			//Move the list to the position
			this.list.move(this.width*.25f,50,this.width*.5f,this.height-50);
			//Appends the menu list
			this.addWidget(this.list);
		}
		
		//Updates all widget-values
		this.list.getViews().forEach(i->i.handleUpdateWidget());
	}

	@Override
	public void render(int mouseX, int mouseY, float p_render_3_) {
		if(!this.isIngame)
			this.renderDirtBackground(0);
		
		super.render(mouseX, mouseY, p_render_3_);
	}
	
	@Override
	protected boolean mouseClickedQuickAccessQui(double mX, double mY) {
		return this.isIngame ? super.mouseClickedQuickAccessQui(mX, mY) : false;
	}
	
	@Override
	protected void renderQuickAccessGui() {
		if(this.isIngame)
			super.renderQuickAccessGui();
	}
	
	@Override
	public void onClose() {
		
		if(this.isIngame) {			
			//Saves the settings
			Toolsmod.getInstance().getModuleManager().save();
			//Opens the module gui
			GuiQAModules.MODULES_GUIS.open();
		}else
			//Opens the old settings
			ConfigGui.CONFIG_SCREENS.open();
	}
}
