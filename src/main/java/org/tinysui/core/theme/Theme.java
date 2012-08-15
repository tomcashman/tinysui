/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.theme;

import org.newdawn.slick.Color;

/**
 * Base class for themes
 * 
 * @author Thomas Cashman
 */
public abstract class Theme {
	protected Color m_windowBorder;
	protected Color m_windowBackground;
	protected Color m_windowTitleForeground;

	protected Color m_buttonBackgroundNormal;
	protected Color m_buttonBackgroundClicked;
	protected Color m_buttonBackgroundDisabled;
	protected Color m_buttonForegroundNormal;
	protected Color m_buttonForegroundDisabled;
	protected Color m_buttonBorder;

	protected Color m_textboxBackgroundNormal;
	protected Color m_textboxBackgroundDisabled;
	protected Color m_textboxBorderNormal;
	protected Color m_textboxBorderDisabled;
	protected Color m_textboxForegroundNormal;
	protected Color m_textboxForegroundDisabled;

	protected Color m_listboxBackground;
	protected Color m_listboxScrollBarBackground;
	protected Color m_listboxScrollBarForeground;

	protected Color m_listItemBackgroundNormal;
	protected Color m_listItemBackgroundSelected;
	protected Color m_listItemForeground;

	protected Color m_labelForeground;

	public Color getWindowBorder() {
		return m_windowBorder;
	}

	public void setWindowBorder(Color windowBorder) {
		m_windowBorder = windowBorder;
	}

	public Color getWindowBackground() {
		return m_windowBackground;
	}

	public void setWindowBackground(Color windowBackground) {
		m_windowBackground = windowBackground;
	}

	public Color getWindowTitleForeground() {
		return m_windowTitleForeground;
	}

	public void setWindowTitleForeground(Color windowTitleForeground) {
		m_windowTitleForeground = windowTitleForeground;
	}

	public Color getButtonBackgroundNormal() {
		return m_buttonBackgroundNormal;
	}

	public void setButtonBackgroundNormal(Color buttonBackgroundNormal) {
		m_buttonBackgroundNormal = buttonBackgroundNormal;
	}

	public Color getButtonBackgroundClicked() {
		return m_buttonBackgroundClicked;
	}

	public void setButtonBackgroundClicked(Color buttonBackgroundClicked) {
		m_buttonBackgroundClicked = buttonBackgroundClicked;
	}

	public Color getButtonBackgroundDisabled() {
		return m_buttonBackgroundDisabled;
	}

	public void setButtonBackgroundDisabled(Color buttonBackgroundDisabled) {
		m_buttonBackgroundDisabled = buttonBackgroundDisabled;
	}

	public Color getButtonForegroundNormal() {
		return m_buttonForegroundNormal;
	}

	public void setButtonForegroundNormal(Color buttonForegroundNormal) {
		m_buttonForegroundNormal = buttonForegroundNormal;
	}

	public Color getButtonForegroundDisabled() {
		return m_buttonForegroundDisabled;
	}

	public void setButtonForegroundDisabled(Color buttonForegroundDisabled) {
		m_buttonForegroundDisabled = buttonForegroundDisabled;
	}

	public Color getButtonBorder() {
		return m_buttonBorder;
	}

	public void setButtonBorder(Color buttonBorder) {
		m_buttonBorder = buttonBorder;
	}

	public Color getTextboxBackgroundNormal() {
		return m_textboxBackgroundNormal;
	}

	public void setTextboxBackgroundNormal(Color textboxBackgroundNormal) {
		m_textboxBackgroundNormal = textboxBackgroundNormal;
	}

	public Color getTextboxBackgroundDisabled() {
		return m_textboxBackgroundDisabled;
	}

	public void setTextboxBackgroundDisabled(Color textboxBackgroundDisabled) {
		m_textboxBackgroundDisabled = textboxBackgroundDisabled;
	}

	public Color getTextboxBorderNormal() {
		return m_textboxBorderNormal;
	}

	public void setTextboxBorderNormal(Color textboxBorderNormal) {
		m_textboxBorderNormal = textboxBorderNormal;
	}

	public Color getTextboxBorderDisabled() {
		return m_textboxBorderDisabled;
	}

	public void setTextboxBorderDisabled(Color textboxBorderDisabled) {
		m_textboxBorderDisabled = textboxBorderDisabled;
	}

	public Color getTextboxForegroundNormal() {
		return m_textboxForegroundNormal;
	}

	public void setTextboxForegroundNormal(Color textboxForegroundNormal) {
		m_textboxForegroundNormal = textboxForegroundNormal;
	}

	public Color getTextboxForegroundDisabled() {
		return m_textboxForegroundDisabled;
	}

	public void setTextboxForegroundDisabled(Color textboxForegroundDisabled) {
		m_textboxForegroundDisabled = textboxForegroundDisabled;
	}

	public Color getListboxBackground() {
		return m_listboxBackground;
	}

	public void setListboxBackground(Color listboxBackground) {
		m_listboxBackground = listboxBackground;
	}

	public Color getListboxScrollBarBackground() {
		return m_listboxScrollBarBackground;
	}

	public void setListboxScrollBarBackground(Color listboxScrollBarBackground) {
		m_listboxScrollBarBackground = listboxScrollBarBackground;
	}

	public Color getListboxScrollBarForeground() {
		return m_listboxScrollBarForeground;
	}

	public void setListboxScrollBarForeground(Color listboxScrollBarForeground) {
		m_listboxScrollBarForeground = listboxScrollBarForeground;
	}

	public Color getListItemBackgroundNormal() {
		return m_listItemBackgroundNormal;
	}

	public void setListItemBackgroundNormal(Color listItemBackgroundNormal) {
		m_listItemBackgroundNormal = listItemBackgroundNormal;
	}

	public Color getListItemBackgroundSelected() {
		return m_listItemBackgroundSelected;
	}

	public void setListItemBackgroundSelected(Color listItemBackgroundSelected) {
		m_listItemBackgroundSelected = listItemBackgroundSelected;
	}

	public Color getListItemForeground() {
		return m_listItemForeground;
	}

	public void setListItemForeground(Color listItemForeground) {
		m_listItemForeground = listItemForeground;
	}

	public Color getLabelForeground() {
		return m_labelForeground;
	}

	public void setLabelForeground(Color labelForeground) {
		m_labelForeground = labelForeground;
	}
}
