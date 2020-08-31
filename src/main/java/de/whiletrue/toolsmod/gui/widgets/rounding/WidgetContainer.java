package de.whiletrue.toolsmod.gui.widgets.rounding;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;

public class WidgetContainer{

	// Saved widgets with their coordinates
	private Map<Widget, Integer[]> widgets = new HashMap<Widget, Integer[]>();

	//Parent screen
	private Screen parent;
	
	//Offset position
	private int x,y;
	
	public WidgetContainer(Screen parent) {
		this.parent=parent;
	}
	
	/**
	 * Clears all previously saved widgets
	 * 
	 * @return the fluid-api widget
	 */
	public WidgetContainer clear() {
		this.widgets.clear();
		return this;
	}

	/**
	 * Add a widget to the container
	 * 
	 * @param widget
	 *            the widget to add
	 * @return the fluid-api widget
	 */
	public WidgetContainer add(Widget widget) {
		this.parent.addButton(widget);
		// Saves the coordinates
		this.widgets.put(widget, new Integer[] { widget.x, widget.y });
		return this;
	}

	/**
	 * Moves the container widgets by x and y
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public void offset(int x, int y) {
		this.setOffset(this.x + x, this.y + y);
	}

	/**
	 * Sets the contains offset
	 * 
	 * @param x
	 *            the hard x coordinate
	 * @param y
	 *            the hard y coordinate
	 */
	public void setOffset(int x, int y) {
		this.x = x;
		this.y = y;

		// Moves the widgets
		this.widgets.forEach((w, p) -> {
			w.x = p[0] + x;
			w.y = p[1] + y;
		});
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
