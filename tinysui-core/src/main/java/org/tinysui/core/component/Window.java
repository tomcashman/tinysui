/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;


/**
 * Implements a window
 * 
 * @author Thomas Cashman
 */
public class Window extends ContainerUiElement implements Draggable {
	/**
	 * Constructor
	 */
	public Window() {
		super();
	}

	@Override
	public Draggable mousePressed(int button, int x, int y) {
		if (this.contains(x, y)) {
			Draggable result = this;
			for (int i = 0; i < this.getChildCount(); i++) {
				Draggable draggable = this.getChild(i).mousePressed(button, x,
						y);
				if (draggable != null) {
					result = draggable;
					break;
				}
			}
			return result;
		}
		return null;
	}
}
