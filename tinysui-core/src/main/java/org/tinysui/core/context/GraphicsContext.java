/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.context;

/**
 * A common interface to graphics implementations
 * 
 * @author Thomas Cashman
 */
public interface GraphicsContext {
	
	public void setClip(int x, int y, int width, int height);
	
	public void clearClip();
	
	public void setColor(int r, int g, int b);
	
	public void setColor(int r, int g, int b, int a);
	
	public void drawText(String text, int renderX, int renderY);

	public void drawRect(int renderX, int renderY, int width, int height);

	public void fillRect(int renderX, int renderY, int width, int height);

	public void drawRoundRect(int renderX, int renderY, int width, int height,
			int cornerRadius);

	public void fillRoundRect(int renderX, int renderY, int width, int height,
			int cornerRadius);
}
