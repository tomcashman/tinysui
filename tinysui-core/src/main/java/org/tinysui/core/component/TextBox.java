/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;

import org.tinysui.core.listener.KeyListener;

public class TextBox extends UiElement implements KeyListener {

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseHover(int x, int y) {}

	@Override
	public Draggable mousePressed(int button, int x, int y) {
		return null;
	}

	@Override
	public KeyListener mouseReleased(int button, int x, int y) {
		if(this.contains(x, y)) {
			return this;
		}
		return null;
	}

	@Override
	public void keyPressed(int key, char c) {
		
	}

	@Override
	public void keyReleased(int key, char c) {
		
	}

}
