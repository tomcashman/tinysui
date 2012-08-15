/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.list;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.UiElement;
import org.tinysui.core.theme.Theme;
import org.tinysui.core.theme.ThemeManager;

/**
 * Abstract list item class
 * 
 * @author Thomas Cashman
 */
public abstract class ListItem extends UiElement {
	private Object m_value;
	protected boolean m_isSelected;

	/**
	 * Constructor
	 * 
	 * @param width
	 *            Width of the item
	 * @param height
	 *            Height of the item
	 */
	public ListItem(float width, float height) {
		super(0, 0, width, height);
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (this.contains(mouseX, mouseY)) {
			return this;
		}
		return null;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		Theme theme = ThemeManager.getTheme();
		if (m_isSelected) {
			if (theme.getListItemBackgroundSelected() != null) {
				g.setColor(theme.getListItemBackgroundSelected());
				g.fillRect(m_renderX, m_renderY, m_width, m_height);
			}
		} else {
			if (theme.getListItemBackgroundNormal() != null) {
				g.setColor(theme.getListItemBackgroundNormal());
				g.fillRect(m_renderX, m_renderY, m_width, m_height);
			}
		}
		super.render(gc, g);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.render(gc, g);
	}

	/**
	 * Returns the value of the item
	 * 
	 * @return
	 */
	public Object getValue() {
		return m_value;
	}

	/**
	 * Sets the value of the item
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		m_value = value;
	}

	/**
	 * Returns if the item is selected
	 * 
	 * @return True if selected
	 */
	public boolean isSelected() {
		return m_isSelected;
	}

	/**
	 * Sets if the item is selected
	 * 
	 * @param isSelected
	 */
	public void setSelected(boolean isSelected) {
		m_isSelected = isSelected;
	}
}
