package de.whiletrue.toolsmod.gui.config.sub.settings;

import de.whiletrue.toolsmod.gui.widgets.TmTextWidget;
import de.whiletrue.toolsmod.gui.widgets.preset.TmWidget;
import de.whiletrue.toolsmod.gui.widgets.rounding.listmultirow.MultirowListItem;
import de.whiletrue.toolsmod.settings.Setting;
import de.whiletrue.toolsmod.util.TextAlign;
import de.whiletrue.toolsmod.util.classes.TextUtil;

public class SettingView<Widget extends TmWidget> extends MultirowListItem<Setting<?, Widget>> {

	// Widget of the setting
	private TmWidget widget;
	// Title widget
	private TmTextWidget title;

	//Current text for the validator
	private String text;
	
	//Description
	private String titleKey;
	
	public SettingView(Setting<?, Widget> item) {
		super(item);
		
		//Title key
		this.titleKey = "global.settings." + item.getName();
		
		// Creates the widget
		this.widgets.add(this.widget = item.handleCreateWidget());
		// Creates the title
		this.widgets.add(this.title = new TmTextWidget(0, 0, "", 0xFFffffff)
				.setAlignX(TextAlign.BEFORE));
	}

	@Override
	public void handlChangePosition(int x, int y, int w, int h) {
		// Value widget
		this.widget.move(this.item.handleMoveWidget(x, y, w, h));
		// Title
		this.title.move(x+5, y);
	}

	/**
	 * Handler once the gui opens
	 */
	@SuppressWarnings("unchecked")
	public void handleUpdateWidget() {
		// Sets the widgets value
		this.item.handleUpdateWidget((Widget) this.widget);
	
		//Updates the title
		this.title.setText(this.text = TextUtil.getInstance().getByKey(this.titleKey,this.item.getName()));
	}
	
	/**
	 * @return if the current item matches the term
	 */
	public boolean isValid(String term) {
		return this.text.toLowerCase().contains(term.toLowerCase());
	}

}
