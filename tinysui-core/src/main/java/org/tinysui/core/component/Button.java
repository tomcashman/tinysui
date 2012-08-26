/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;

import org.tinysui.core.context.GraphicsContext;
import org.tinysui.core.listener.KeyListener;

public class Button extends UiElement {
	private boolean isMouseHovering;
	private boolean clickStarted;

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		GraphicsContext graphics = this.getGraphicsContext();
	}

	@Override
	public void mouseHover(int x, int y) {
		if(this.contains(x, y)) {
			this.isMouseHovering = true;
			notifyHoverListener();
		} else {
			this.isMouseHovering = false;
		}
	}

	@Override
	public Draggable mousePressed(int button, int x, int y) {
		if(this.contains(x, y)) {
			this.clickStarted = true;
		}
		return null;
	}

	@Override
	public KeyListener mouseReleased(int button, int x, int y) {
		if(this.clickStarted && this.contains(x, y)) {
			notifyClickListener();
		}
		this.clickStarted = false;
		return null;
	}

}
