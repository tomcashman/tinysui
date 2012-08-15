/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.theme.ThemeManager;

/**
 * Implements a text label
 * 
 * @author Thomas Cashman
 */
public class Label extends UiElement {
	private float m_offsetX, m_offsetY;
	private String m_text;

	public Label(float x, float y, float width, float height) {
		super(x, y, width, height);
		m_offsetX = 0;
		m_offsetY = 0;
	}

	public Label(float x, float y, String text, Font font) {
		super(x, y, font.getWidth(text), font.getHeight(text));
		m_text = text;
		m_font = font;
		m_offsetX = 0;
		m_offsetY = 0;
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		return null;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (m_isVisible) {
			g.setColor(ThemeManager.getTheme().getLabelForeground());
			if (m_text != null && m_font != null) {
				m_font.drawString(m_renderX + m_offsetX, m_renderY + m_offsetY,
						m_text);
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.render(gc, g);
	}

	public String getText() {
		return m_text;
	}

	public void setText(String text) {
		m_text = text;
	}

	public void setTextOffset(float x, float y) {
		m_offsetX = x;
		m_offsetY = y;
	}

}
