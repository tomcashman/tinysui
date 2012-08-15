/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.theme;

import org.newdawn.slick.Color;

/**
 * The default theme
 * 
 * @author Thomas Cashman
 */
public class DefaultTheme extends Theme {
	public DefaultTheme() {
		m_windowBorder = new Color(133, 137, 144);
		m_windowBackground = new Color(0, 0, 0, 85);
		m_windowTitleForeground = new Color(182, 201, 221);

		m_buttonBackgroundNormal = Color.gray;
		m_buttonBackgroundClicked = m_buttonBackgroundNormal.darker();
		m_buttonBackgroundDisabled = m_buttonBackgroundNormal.brighter();
		m_buttonForegroundNormal = Color.white;
		m_buttonForegroundDisabled = Color.gray;
		m_buttonBorder = m_windowBorder;

		m_textboxBackgroundNormal = new Color(0, 0, 0, 10);
		m_textboxBackgroundDisabled = new Color(182, 201, 221);
		m_textboxBorderNormal = new Color(0, 0, 0);
		m_textboxBorderDisabled = new Color(0, 0, 0);
		m_textboxForegroundNormal = new Color(255, 255, 255);
		m_textboxForegroundDisabled = new Color(255, 255, 255);

		m_listboxBackground = new Color(0, 0, 0, 10);
		m_listboxScrollBarBackground = new Color(0, 0, 0);
		m_listboxScrollBarForeground = new Color(255, 255, 255);

		m_listItemBackgroundNormal = new Color(0, 0, 0, 25);
		m_listItemBackgroundSelected = new Color(0, 0, 0, 80);
		m_listItemForeground = new Color(255, 255, 255);
	}
}
