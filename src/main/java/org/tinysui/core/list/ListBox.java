/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.list;

import org.tinysui.core.UiElement;
import org.tinysui.core.container.ScrollPanel;

/**
 * Implements a listbox control
 * 
 * @author Thomas Cashman
 */
public class ListBox extends ScrollPanel {
	private ListItem m_selectedItem;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            X coordinate relative to parent
	 * @param y
	 *            Y coordinate relative to parent
	 * @param width
	 *            Width of the box
	 * @param height
	 *            Height of the box
	 */
	public ListBox(float x, float y, float width, float height) {
		super(x, y, width, height);
		m_selectedItem = null;
	}

	/**
	 * Adds an item to the end of the list
	 * 
	 * @param item
	 */
	public void addItem(ListItem item) {
		item.setWidth(m_width);
		float yPos = 0;
		for (int i = 0; i < this.getChildren().size(); i++) {
			UiElement element = this.getChildren().get(i);
			yPos += element.getHeight() + 2;
		}
		item.setY(yPos);
		item.setDraggable(false);
		this.addChild(item);
	}

	/**
	 * Removes an item at the specified indexs
	 * 
	 * @param index
	 */
	public void removeItem(int index) {
		int count = 0;
		for (int i = 0; i < this.getChildren().size(); i++) {
			UiElement element = this.getChildren().get(i);
			if (count == index) {
				this.removeChild(element);
				break;
			}
			count++;
		}
	}

	/**
	 * Remove a specified item from the list
	 * 
	 * @param item
	 */
	public void removeItem(ListItem item) {
		for (int i = 0; i < this.getChildren().size(); i++) {
			UiElement element = this.getChildren().get(i);
			if (element.compareTo(item) == 0) {
				this.removeChild(element);
				break;
			}
		}
	}

	/**
	 * Returns the selected item
	 * 
	 * @return
	 */
	public ListItem getSelectedItem() {
		return m_selectedItem;
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (m_isVisible) {
			UiElement focus = super.grabsFocus(mouseX, mouseY);
			if (focus != null && focus instanceof ListItem) {
				/* Deselect previous item */
				if (m_selectedItem != null)
					m_selectedItem.setSelected(false);

				/* Select current item */
				m_selectedItem = (ListItem) focus;
				m_selectedItem.setSelected(true);
			}
			return focus;
		}
		return null;
	}
}
