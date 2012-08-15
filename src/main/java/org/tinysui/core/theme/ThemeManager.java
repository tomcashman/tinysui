/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.theme;

/**
 * Static access to UI theme management
 * 
 * @author Thomas Cashman
 */
public class ThemeManager {
	private static Theme m_theme;

	/**
	 * Returns the current theme
	 * 
	 * @return
	 */
	public static Theme getTheme() {
		if (m_theme == null) {
			m_theme = new DefaultTheme();
		}
		return m_theme;
	}

	/**
	 * Sets the current theme
	 * 
	 * @param theme
	 */
	public static void setTheme(Theme theme) {
		m_theme = theme;
	}
}
