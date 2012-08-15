/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.container;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.Label;
import org.tinysui.core.UiElement;
import org.tinysui.core.theme.ThemeManager;
import org.tinysui.core.util.ConcurrentArrayList;

/**
 * Implements a window
 * 
 * @author Thomas Cashman
 * @author Keith Cummin
 */
public class Window extends UiElement {
	protected Label m_title;
	protected UiElement m_container;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            X coordinate of the window
	 * @param y
	 *            Y coordinate of the window
	 * @param width
	 *            Width of the window
	 * @param height
	 *            Height of the window
	 */
	public Window(float x, float y, float width, float height) {
		super(x, y, width, height);
		m_isDraggable = true;

		m_title = new Label(0, 0, m_width, 24);
		m_title.setTextOffset(4, 2);
		m_children.add(m_title);

		m_container = new UiElement(0, m_title.getHeight(), width, height
				- m_title.getHeight()) {

			@Override
			public UiElement grabsFocus(float mouseX, float mouseY) {
				for (int i = 0; i < m_children.size(); i++) {
					UiElement focus = m_children.get(i).grabsFocus(mouseX,
							mouseY);
					if (focus != null) {
						return focus;
					}
				}
				return null;
			}

		};
		m_children.add(m_container);
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (m_isVisible) {
			if (m_title.contains(mouseX, mouseY)) {
				return this;
			} else {
				for (int i = 0; i < m_children.size(); i++) {
					UiElement focus = m_children.get(i).grabsFocus(mouseX,
							mouseY);
					if (focus != null) {
						return focus;
					}
				}
			}
		}
		return null;
	}

	private void baseRender(GameContainer gc, Graphics g) {
		g.setFont(m_font);
		m_title.setFont(m_font);
		/* Render window */
		/* Render background */
		g.setColor(ThemeManager.getTheme().getWindowBackground());
		g.fillRect(m_renderX, m_renderY, m_width, m_height);
		/* Render border */
		g.setColor(ThemeManager.getTheme().getWindowBorder());
		g.drawRect(m_renderX, m_renderY, m_width, m_height);

		/* Title border */
		g.drawRect(m_renderX, m_renderY, m_width, 24);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (m_isVisible) {
			baseRender(gc, g);

			/* Render children */
			super.render(gc, g);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		if (m_isVisible) {
			baseRender(gc, g);

			/* Render children */
			super.render(gc, game, g);
		}
	}

	/**
	 * Sets the text of the Title Label
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		if (m_title != null) {
			m_title.setText(title);
		}
	}

	@Override
	public void addChild(UiElement element) {
		if (element.getFont() == null)
			element.setFont(m_font);

		m_container.addChild(element);
	}

	@Override
	public void removeChild(UiElement element) {
		m_container.removeChild(element);
	}

	@Override
	public ConcurrentArrayList<UiElement> getChildren() {
		return m_container.getChildren();
	}

	/**
	 * Returns the Title Label
	 * 
	 * @return
	 */
	public Label getTitle() {
		return m_title;
	}
}
