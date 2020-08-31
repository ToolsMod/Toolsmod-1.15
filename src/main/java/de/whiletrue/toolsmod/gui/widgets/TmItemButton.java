package de.whiletrue.toolsmod.gui.widgets;

import java.util.Optional;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import de.whiletrue.toolsmod.gui.widgets.preset.TmSizeWidget;
import de.whiletrue.toolsmod.gui.widgets.preset.TmWidget;
import de.whiletrue.toolsmod.util.classes.TextUtil;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TmItemButton extends TmSizeWidget{

	//Click event handler
	private IUpdateable onClick;
	
    //Stack that should be rendered
    private ItemStack stack;
    
    //Model for the item
    private IBakedModel model;
    
    //If the widget is enabled
    private boolean enabled=true;
    
    //Tooltipp
    private String tooltipp;

	public TmItemButton(int x, int y,ItemStack item,IUpdateable onClick) {
		super(x, y, 20, 20);
		this.setStack(item);
		this.onClick=onClick;
	}
	
	public TmItemButton(int x, int y,Item item,IUpdateable onClick) {
		this(x, y, new ItemStack(item),onClick);
	}

	public TmItemButton setTooltipp(String tooltip) {
		this.tooltipp=tooltip;
		return this;
	}

	public TmItemButton setTooltippByKey(String key,Object... args) {
		this.tooltipp=TextUtil.getInstance().getByKey(key,args);
		return this;
	}
	
	public TmItemButton setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public TmItemButton setStack(ItemStack stack) {
		//Sets the stack
		this.stack=stack;
		//Gets the model
		this.model=this.mc.getItemRenderer().getItemModelWithOverrides(this.stack, null, this.mc.player);
		return this;
	}
	
	@Override
	public Optional<TmWidget> onMouseClicked(double mX, double mY, int ticks) {
		if(this.hovered)
			//Executes the click event
			this.onClick.execute(this);
		return Optional.empty();
	}
	
	@Override
	public void onRender(int mX, int mY, float ticks, TmWidget focused) {
		super.onRender(mX, mY, ticks, focused);
		
		//Renders the default button
		this.renderButton();
		
		//Renders the item
		this.renderer.renderItem(this.stack, this.model, this.x+2, this.y+2);
	}
	
	@Override
	public boolean onPostRender(int mX, int mY) {
		if(this.hovered&&this.enabled&&this.tooltipp!=null) {
			this.mc.getItemRenderer().zLevel=0;
			//Renders the tooltipp
			this.mc.currentScreen.renderTooltip(this.tooltipp,mX,mY);
			return true;
		}
		return false;
	}
	
	/**
	 * Renders the default game button
	 */
	private void renderButton() {
		this.mc.getTextureManager().bindTexture(Widget.WIDGETS_LOCATION);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1);
		int i = this.enabled ? this.hovered ? 2 : 1 : 0;
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		
		int blitOffset = -90;
		
		AbstractGui.blit(this.x, this.y, blitOffset, 0, 46 + i * 20, this.width / 2, this.height,256,256);
		AbstractGui.blit(this.x + this.width / 2, this.y, blitOffset, 200 - this.width / 2, 46 + i * 20, this.width / 2,
				this.height, 256,256);
	}
	
	@FunctionalInterface
    public interface IUpdateable{
        void execute(TmItemButton button);
    }
}