package de.whiletrue.toolsmod.settings;

import de.whiletrue.toolsmod.gui.widgets.preset.TmWidget;

public abstract class Setting<T,Widget extends TmWidget>{

	//Holds the value (Should be public for access purpose)
	public T value;
	
	//Name of the setting
	private String name;
	
	/**
	 * Sets the name
	 */
	@SuppressWarnings("unchecked")
	public<X extends Setting<T,Widget>> X name(String name){
		this.name=name;
		return (X)this;
	}
	
	/**
	 * Sets the default value
	 */
	@SuppressWarnings("unchecked")
	public<X extends Setting<T,Widget>> X standard(T value){
		this.value=value;
		return (X)this;
	}
	
	/**
	 * @param value the value as object
	 * @return the value as a string to save
	 */
	public abstract String handleSave();
	
	/**
	 * @param value the value as string
	 * @return if the parsing was successful
	 */
	public abstract boolean handleParse(String value);
	
	
	
	/**
	 * Creates the settings widget
	 * @return the widget for the setting type
	 */
	public abstract TmWidget handleCreateWidget();
	
	/**
	 * Calculates the position for the settings widget
	 * 
	 * @param x, y, w, h the coordinates where the item view is located
	 * @return the new position for the widget
	 */
	public abstract float[] handleMoveWidget(int x,int y,int w,int h);
	
	/**
	 * Updates the widgets value to the current
	 */
	public abstract void handleUpdateWidget(Widget widget);
	
	
	
	public String getName() {
		return this.name;
	}
}
