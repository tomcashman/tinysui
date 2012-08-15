/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.container;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.UiElement;
import org.tinysui.core.util.ConcurrentArrayList;

/**
 * Implements a scrollable panel
 * 
 * @author Thomas Cashman
 */
public class ScrollPanel extends UiElement {
	private ScrollBar m_scrollBar;
	private UiElement m_scrollPane;
	private float m_scrollRenderY;
	private Rectangle m_clip;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            Panel's visual X coordinate
	 * @param y
	 *            Panel's visual Y coordinate
	 * @param width
	 *            Panel's visual width
	 * @param height
	 *            Panel's visual height
	 */
	public ScrollPanel(float x, float y, float width, float height) {
		super(x, y, width, height);
		m_clip = new Rectangle(x, y, width, height);
		m_scrollRenderY = 0;

		m_scrollBar = new ScrollBar(width - 16, 0, 16, height);
		m_scrollPane = new UiElement(0, 0, width, height) {

			@Override
			public UiElement grabsFocus(float mouseX, float mouseY) {
				if (this.m_children != null) {
					for (int i = 0; i < this.m_children.size(); i++) {
						UiElement focus = this.m_children.get(i).grabsFocus(
								mouseX, mouseY);
						if (focus != null) {
							return focus;
						}
					}
				}
				return null;
			}
		};
		m_children.add(m_scrollBar);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		m_clip.setX(m_renderX);
		m_clip.setY(m_renderY);

		/* Update content render coordinates relative to scroll bar position */
		m_scrollRenderY = m_scrollBar.getScrollRenderY()
				* m_scrollPane.getHeight();
		m_scrollPane.updateRenderPosition(m_renderX, m_renderY
				- m_scrollRenderY);
		m_scrollPane.update(gc, delta);

		/* Update scroll bar */
		m_scrollBar.updateRenderPosition(this);
		m_scrollBar.update(gc, delta);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		this.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (m_isVisible) {
			/* Render scrolled content */
			g.setClip(m_clip);
			m_scrollPane.render(gc, g);
			g.clearClip();

			/* Render scroll bar */
			m_scrollBar.render(gc, g);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		if (m_isVisible) {
			/* Render scrolled content */
			g.setClip(m_clip);
			m_scrollPane.render(gc, game, g);
			g.clearClip();

			/* Render scroll bar */
			m_scrollBar.render(gc, game, g);
		}
	}

	@Override
	public void addChild(UiElement element) {
		m_scrollPane.addChild(element);
		float targetHeight = element.getX() + element.getHeight();
		if (targetHeight > m_scrollPane.getHeight()) {
			m_scrollPane.setHeight(targetHeight);
		}
	}

	@Override
	public void removeChild(UiElement element) {
		m_scrollPane.removeChild(element);
	}

	@Override
	public ConcurrentArrayList<UiElement> getChildren() {
		return m_scrollPane.getChildren();
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (m_isVisible) {
			UiElement focus = m_scrollBar.grabsFocus(mouseX, mouseY);
			if (focus == null) {
				focus = m_scrollPane.grabsFocus(mouseX, mouseY);
			}
			return focus;
		}
		return null;
	}

	@Override
	public void updateRenderPosition(UiElement parent) {
		super.updateRenderPosition(parent);

		/* Update content render coordinates relative to scroll bar position */
		m_scrollRenderY = m_scrollBar.getScrollRenderY()
				* m_scrollPane.getHeight();
		m_scrollPane.updateRenderPosition(m_renderX, m_renderY
				- m_scrollRenderY);
	}
}
