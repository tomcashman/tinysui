/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;

import org.tinysui.core.listener.KeyListener;

/**
 * Implements a text label
 * 
 * @author Thomas Cashman
 */
public class Label extends UiElement {
	private String text;
	
	/**
	 * Constructor
	 */
	public Label() {
		super();
		text = "";
	}

	@Override
	public void update(int delta) {}

	@Override
	public void render() {
		this.getGraphicsContext().drawText(text, getRenderX(), getRenderY());
	}

	@Override
	public void mouseHover(int x, int y) {}

	@Override
	public Draggable mousePressed(int button, int x, int y) {
		return null;
	}

	@Override
	public KeyListener mouseReleased(int button, int x, int y) {
		return null;
	}
	
	/**
	 * Returns the text of this label
	 * @return An empty string if there is no text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Sets the text of this label
	 * @param text The text to be set
	 */
	public void setText(String text) {
		if(text != null) {
			this.text = text;
		}
	}
}
