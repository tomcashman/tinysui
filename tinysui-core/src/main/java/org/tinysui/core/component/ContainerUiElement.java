/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;

import org.tinysui.core.listener.KeyListener;
import org.tinysui.core.util.ConcurrentArrayList;

/**
 * Implements a base class for UI elements which can contain children
 * 
 * @author Thomas Cashman
 */
public abstract class ContainerUiElement extends UiElement {
	private ConcurrentArrayList<UiElement> children;

	/**
	 * Constructor
	 */
	public ContainerUiElement() {
		super();
		children = new ConcurrentArrayList<UiElement>();
	}

	/**
	 * Updates the UI element
	 * 
	 * @param delta
	 *            Time in milliseconds since the last update
	 */
	public void update(int delta) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).update(delta);
		}
	}

	/**
	 * Renders the UI element
	 */
	public void render() {
		if(isVisible()) {
			for (int i = 0; i < children.size(); i++) {
				children.get(i).render();
			}
		}
	}

	/**
	 * Adds a child {@link UiElement}
	 * @param element An instance of {@link UiElement} to be added
	 */
	public void addChild(UiElement element) {
		element.setParent(this);
		element.setGraphicsContext(getGraphicsContext());
		children.add(element);
	}
	
	/**
	 * Gets the child {@link UiElement} at a given index
	 * @param index The element index
	 * @return The element at the index
	 */
	public UiElement getChild(int index) {
		return children.get(index);
	}
	
	/**
	 * Removes the child {@link UiElement} at a given index
	 * @param index The element index
	 * @return The element removed
	 */
	public UiElement removeChild(int index) {
		return children.removeAt(index);
	}
	
	/**
	 * Returns the amount of child elements
	 * @return 0 if there are none
	 */
	public int getChildCount() {
		return children.size();
	}
	
	@Override
	public void setX(int x) {
		super.setX(x);
		for(int i = 0; i < children.size(); i++)
			children.get(i).setParent(this);
	}
	
	@Override
	public void setY(int y) {
		super.setY(y);
		for(int i = 0; i < children.size(); i++)
			children.get(i).setParent(this);
	}
	
	@Override
	public void mouseHover(int x, int y) {
		for(int i = 0; i < this.getChildCount(); i++) {
			this.getChild(i).mouseHover(x, y);
		}
	}

	@Override
	public KeyListener mouseReleased(int button, int x, int y) {
		KeyListener result = null;
		for(int i = 0; i < this.getChildCount(); i++) {
			result = this.getChild(i).mouseReleased(button, x, y);
			if(result != null)
				break;
		}
		return result;
	}
}
