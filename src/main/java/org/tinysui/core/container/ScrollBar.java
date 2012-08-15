/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.container;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.ActionListener;
import org.tinysui.core.UiElement;
import org.tinysui.core.theme.ThemeManager;

/**
 * Implements a scroll bar
 * 
 * @author Thomas Cashman
 */
public class ScrollBar extends UiElement {
	private UiElement m_draggedContainer;
	private ActionListener m_movementListener;

	public ScrollBar(float x, float y, float width, float height) {
		super(x, y, width, height);
		initDraggyBit();
	}

	private void initDraggyBit() {
		m_draggedContainer = new UiElement(0, 0, m_width, 32) {

			@Override
			public UiElement grabsFocus(float mouseX, float mouseY) {
				return null;
			}
		};
		m_draggedContainer.setBoundMaxX(m_width);
		m_draggedContainer.setBoundMaxY(m_height);
		m_draggedContainer.setDraggable(true);

		/* Add it to the list of children */
		m_children.add(m_draggedContainer);
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (m_draggedContainer.contains(mouseX, mouseY)) {
			m_draggedContainer.setHasFocus(true);
			return m_draggedContainer;
		}
		return null;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		/* Render background of the bar */
		g.setColor(ThemeManager.getTheme().getListboxScrollBarBackground());
		g.fillRect(m_renderX, m_renderY, m_width, m_height);

		/* Render the draggy bit */
		g.setColor(ThemeManager.getTheme().getListboxScrollBarForeground());
		g.fillRect(m_draggedContainer.getRenderX(),
				m_draggedContainer.getRenderY(), m_draggedContainer.getWidth(),
				m_draggedContainer.getHeight());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.render(gc, g);
	}

	/**
	 * Returns the percentage the scrollbar has scrolled
	 * 
	 * @return
	 */
	public float getScrollRenderY() {
		return m_draggedContainer.getY() / m_height;
	}
}
