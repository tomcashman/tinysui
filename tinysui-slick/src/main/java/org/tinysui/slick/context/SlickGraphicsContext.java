/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.slick.context;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.tinysui.core.component.ContainerUiElement;
import org.tinysui.core.component.Draggable;
import org.tinysui.core.context.GraphicsContext;

/**
 * Implements a {@link GraphicsContext} for Slick
 * 
 * @author Thomas Cashman
 */
public class SlickGraphicsContext extends ContainerUiElement implements
		GraphicsContext {
	private Graphics graphics;
	private GameContainer gameContainer;

	/**
	 * Constructor
	 * @param gc The game container to be rendered to
	 */
	public SlickGraphicsContext(GameContainer gc) {
		super();
		this.setGraphicsContext(this);
		this.graphics = gc.getGraphics();
		this.gameContainer = gc;
	}
	
	@Override
	public Draggable mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void drawText(String text, int renderX, int renderY) {
		graphics.drawString(text, renderX, renderY);
	}

	@Override
	public void drawRect(int renderX, int renderY, int width, int height) {
		graphics.drawRect(renderX, renderY, width, height);
	}

	@Override
	public void fillRect(int renderX, int renderY, int width, int height) {
		graphics.fillRect(renderX, renderY, width, height);
	}

	@Override
	public void drawRoundRect(int renderX, int renderY, int width, int height,
			int cornerRadius) {
		graphics.drawRoundRect(renderX, renderY, width, height, cornerRadius);
	}

	@Override
	public void fillRoundRect(int renderX, int renderY, int width, int height,
			int cornerRadius) {
		graphics.fillRoundRect(renderX, renderY, width, height, cornerRadius);
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		graphics.setClip(x, y, width, height);
	}

	@Override
	public void clearClip() {
		graphics.clearClip();
	}

	@Override
	public void setColor(int r, int g, int b) {
		graphics.setColor(new Color(r, g, b));
	}

	@Override
	public void setColor(int r, int g, int b, int a) {
		graphics.setColor(new Color(r, g, b, a));
	}
}
