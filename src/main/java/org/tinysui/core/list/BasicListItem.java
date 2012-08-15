/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.list;

import org.tinysui.core.Label;

/**
 * A basic list item which stores display text and a value
 * 
 * @author Thomas Cashman
 */
public class BasicListItem extends ListItem {
	private Label m_label;

	/**
	 * Constructor
	 * 
	 * @param value
	 */
	public BasicListItem(String text, String value) {
		super(128, 32);
		m_label = new Label(0, 0, 128, 32);
		m_label.setText(text);
		this.addChild(m_label);
		this.setValue(value);
	}

	/**
	 * Sets the text of the list item
	 * 
	 * @param text
	 */
	public void setText(String text) {
		m_label.setText(text);
		m_label.setWidth(m_width);
	}

	/**
	 * Returns the text of the list item
	 * 
	 * @return
	 */
	public String getText() {
		return m_label.getText();
	}

	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		m_label.setWidth(width);
	}

	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		m_label.setHeight(height);
	}
}
