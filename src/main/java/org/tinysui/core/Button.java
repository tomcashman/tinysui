/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.theme.Theme;
import org.tinysui.core.theme.ThemeManager;

/**
 * Represents a UI button
 * 
 * @author Thomas Cashman
 * @author Keith Cummins
 */
public class Button extends UiElement {
	private boolean m_enabled;
	private ActionListener m_clickListener;
	private int m_downTimer;
	private String m_text;
	private float m_textX, m_textY;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            Button x coordinate relative to parent
	 * @param y
	 *            Button y coordinate relative to parent
	 * @param width
	 *            Button width
	 * @param height
	 *            Button height
	 */
	public Button(float x, float y, float width, float height) {
		super(x, y, width, height);
		m_enabled = true;
		m_downTimer = 0;
		m_textX = -1;
		m_textY = -1;
		this.setDraggable(false);
	}

	public Button(float x, float y, String text, Font font) {
		super(x, y, font.getWidth(text) + 8, font.getHeight(text) + 8);
		m_text = text;
		m_enabled = true;
		m_downTimer = 0;
		m_textX = -1;
		m_textY = -1;
		m_font = font;
		this.setDraggable(false);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		/*
		 * Down timer is used to animate button being pressed down but also
		 * prevents multiple clicks
		 */
		if (m_downTimer > 0) {
			m_downTimer -= delta;
		} else {
			m_downTimer = 0;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		this.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		Theme theme = ThemeManager.getTheme();
		if (m_isVisible) {
			Color tempColor = g.getColor();
			g.setFont(m_font);

			if (m_enabled) {
				if (m_downTimer > 0) {
					/* Render button as pressed down */
					g.setColor(theme.getButtonBackgroundClicked());
					g.fillRoundRect(m_renderX, m_renderY, getWidth(),
							getHeight(), 2);
				} else {
					/* Render button as normal */
					g.setColor(theme.getButtonBackgroundNormal());
					g.fillRoundRect(m_renderX, m_renderY, getWidth(),
							getHeight(), 5);
				}
				g.setColor(theme.getButtonForegroundNormal());
			} else {
				/* Render button as disabled */
				g.setColor(theme.getButtonBackgroundDisabled());
				g.fillRoundRect(m_renderX, m_renderY, getWidth(), getHeight(),
						2);
				g.setColor(theme.getButtonForegroundDisabled());
			}

			if (m_text != null) {
				m_textX = m_renderX
						+ ((getWidth() / 2) - (g.getFont().getWidth(m_text) / 2));
				m_textY = m_renderY
						+ ((getHeight() / 2) - (g.getFont().getLineHeight() / 2));
				g.drawString(m_text, m_textX, m_textY);
			}

			g.setColor(theme.getButtonBorder());
			g.drawRoundRect(m_renderX, m_renderY, getWidth(), getHeight(), 2);

			g.setColor(tempColor);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.render(gc, g);
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (m_isVisible) {
			if (m_enabled && m_downTimer <= 0 && this.contains(mouseX, mouseY)) {
				/* Call click listener */
				if (m_clickListener != null)
					m_clickListener.handleEvent(this);

				/* Set state to render the button as pressed down */
				m_downTimer = 350;
			}
		}
		/* Button never takes focus */
		return null;
	}

	/**
	 * Returns if the button is enabled
	 * 
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return m_enabled;
	}

	/**
	 * Sets if the button is enabled
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		m_enabled = enabled;
	}

	/**
	 * Sets the click listener for the button
	 * 
	 * @param clickListener
	 */
	public void setClickListener(ActionListener clickListener) {
		m_clickListener = clickListener;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return m_text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		m_text = text;
		m_textX = -1;
		m_textY = -1;
	}

}
