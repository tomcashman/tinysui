/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.theme.ThemeManager;

/**
 * A textbox UI component. Can also act as a password field.
 * 
 * @author Thomas Cashman
 * @author Keith Cummins
 */
public class TextBox extends UiElement {
	public static final char CHARACTER_MASK = '*';
	private final int CURSOR_BLINK_TIMEOUT = 500;
	private final static int DEFAULT_WIDTH = 150;

	private boolean m_enabled;
	private boolean m_characterMaskEnabled;
	private String m_text;
	private int m_cursorIndex;
	private int m_stringWidth;
	private int m_padding = 4;

	private boolean m_showCursor = true;
	private int m_cursorToggleTimer;
	private Rectangle m_clip;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            X coordinate relative to parent
	 * @param y
	 *            Y coordinate relative to parent
	 * @param width
	 *            Textbox width
	 * @param height
	 *            Textbox height
	 */
	public TextBox(float x, float y, float width, float height) {
		super(x, y, width, height);
		m_enabled = true;
		m_characterMaskEnabled = false;
		m_text = "";
		m_cursorIndex = 0;
		m_stringWidth = 0;
		m_clip = new Rectangle(x, y, width, height);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.render(gc, g);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		m_clip.setX(m_renderX - 2);
		m_clip.setY(m_renderY - 2);
		m_clip.setWidth(m_width + 2);
		m_clip.setHeight(m_height + 2);
		g.setClip(m_clip);

		if (m_isVisible && m_font != null) {
			g.setFont(m_font);

			if (m_text.length() > 0)
				m_stringWidth = m_font.getWidth(m_text);
			else
				m_stringWidth = 0;

			if (m_enabled) {
				/* Render enabled textbox */
				/* Render background */
				g.setColor(ThemeManager.getTheme().getTextboxBackgroundNormal());
				g.fillRect(m_renderX, m_renderY, m_width, m_height);
				/* Render border */
				g.setColor(ThemeManager.getTheme().getTextboxBorderNormal());
				g.drawRect(m_renderX, m_renderY, m_width, m_height);
				/* Render text */
				g.setColor(ThemeManager.getTheme().getTextboxForegroundNormal());
				if (m_characterMaskEnabled) {
					g.drawString(m_text.replaceAll(".", "*"), m_renderX
							+ m_padding, m_renderY);
				} else {
					g.drawString(m_text, m_renderX + m_padding, m_renderY);
				}
				if (m_hasFocus) {
					/* Render blinking cursor */
					if (m_showCursor) {
						String s = m_text.substring(0, (int) m_cursorIndex);
						g.drawString("|", m_renderX + m_font.getWidth(s) + 2,
								m_renderY);
					}
				}
			} else {
				/* Render disabled textbox */
				/* Render background */
				g.setColor(ThemeManager.getTheme()
						.getTextboxBackgroundDisabled());
				g.fillRect(m_renderX, m_renderY, m_width, m_height);
				/* Render border */
				g.setColor(ThemeManager.getTheme().getTextboxBorderDisabled());
				g.drawRect(m_renderX, m_renderY, m_width, m_height);
				/* Render text */
				g.setColor(ThemeManager.getTheme()
						.getTextboxForegroundDisabled());
				if (m_characterMaskEnabled) {
					g.drawString(m_text.replaceAll(".", "*"), m_renderX
							+ m_padding, m_renderY);
				} else {
					g.drawString(m_text, m_renderX + m_padding, m_renderY);
				}
			}
		}
		g.clearClip();
	}

	@Override
	public void update(GameContainer gc, int delta) {
		/* Update the cursor flash */
		if (m_cursorToggleTimer < 0) {
			m_cursorToggleTimer = CURSOR_BLINK_TIMEOUT;
			m_showCursor = !m_showCursor;
		} else {
			m_cursorToggleTimer -= delta;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		this.update(gc, delta);
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (m_isVisible) {
			if (m_enabled && this.contains(mouseX, mouseY)) {
				if (m_text.length() > 0 && m_stringWidth > 0) {
					/* Rough guess cursor position */
					int charSize = m_stringWidth / m_text.length();
					m_cursorIndex = (int) (mouseX - m_renderX - 4) / charSize;
					if (m_cursorIndex > m_text.length()) {
						m_cursorIndex = m_text.length();
					}
				} else
					m_cursorIndex = 0;
				m_hasFocus = true;
				return this;
			}
		}
		return null;
	}

	public void handleInput(int key, char c) {
		switch (key) {
		case Input.KEY_LEFT:
			if (m_cursorIndex != 0) {
				m_cursorIndex--;
			}
			break;
		case Input.KEY_RIGHT:
			if (m_cursorIndex < m_text.length()) {
				m_cursorIndex++;
			}
			break;
		case Input.KEY_BACK:
			/* TODO Remove the character at the cursor */
			if (m_cursorIndex != 0) {
				m_text = m_text.substring(0, m_cursorIndex - 1)
						+ m_text.substring(m_cursorIndex);
				m_cursorIndex--;
			}
			break;
		case Input.KEY_RETURN:
			/* Do nothing */
			break;
		default:
			/* Add the character to the text field */
			if (m_cursorIndex == m_text.length()) {
				/* Append to end */
				m_text += c;
			} else if (m_cursorIndex == 0) {
				/* Add to start */
				m_text = c + m_text;
			} else {
				/* Add in middle */
				m_text = m_text.substring(0, m_cursorIndex) + c
						+ m_text.substring(m_cursorIndex);
			}
			m_cursorIndex++;
			break;
		}
	}

	/**
	 * Returns if the textbox is enabled
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return m_enabled;
	}

	/**
	 * Sets if the textbox is enabled
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		m_enabled = enabled;
	}

	/**
	 * Returns true if the character mask is enabled
	 * 
	 * @return True if box appears as password field
	 */
	public boolean isCharacterMaskEnabled() {
		return m_characterMaskEnabled;
	}

	/**
	 * Sets if the character mask is enabled
	 * 
	 * @param characterMaskEnabled
	 *            True if box should appear as password field
	 */
	public void setCharacterMaskEnabled(boolean characterMaskEnabled) {
		m_characterMaskEnabled = characterMaskEnabled;
	}

	/**
	 * Returns the text of the textbox
	 * 
	 * @return
	 */
	public String getText() {
		return m_text;
	}

	/**
	 * Sets the text of the textbox
	 * 
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		m_text = text;
	}
}
