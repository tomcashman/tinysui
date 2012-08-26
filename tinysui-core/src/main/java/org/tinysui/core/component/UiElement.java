/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;

import org.tinysui.core.context.GraphicsContext;
import org.tinysui.core.listener.ActionListener;
import org.tinysui.core.listener.MouseListener;

/**
 * A base class for user interface elements
 * 
 * @author Thomas Cashman
 */
public abstract class UiElement implements Comparable<UiElement>, MouseListener {
	private static transient long ID_COUNTER = 0;
	
	private transient long id;
	private transient int x;
	private transient int y;
	private transient int renderX;
	private transient int renderY;
	private transient int width;
	private transient int height;
	private transient boolean isVisible;
	private UiElement parent;
	private GraphicsContext graphicsContext;
	
	private ActionListener clickListener;
	private ActionListener hoverListener;
	
	/**
	 * Constructor
	 */
	public UiElement() {
		id = ID_COUNTER;
		ID_COUNTER++;
		isVisible = true;
	}
	
	/**
	 * Returns if this element contains the provided on-screen coordinates
	 * @param screenX The on-screen X coordinate
	 * @param screenY The on-screen Y coordinate
	 * @return True if the element contains the coordinates
	 */
	public boolean contains(int screenX, int screenY) {
		if(screenX < getRenderX())
			return false;
		else if(screenY < getRenderY())
			return false;
		else if(screenX > (getRenderX() + width))
			return false;
		else if(screenY > (getRenderY() + height))
			return false;
		return true;
	}

	/**
	 * Updates the UI element
	 * @param delta Time in milliseconds since the last update
	 */
	public abstract void update(int delta);
	
	/**
	 * Renders the UI element
	 */
	public abstract void render();
	
	/**
	 * The UI element's unique identifier
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Returns the x coordinate of this element relative to its parent
	 * @return 0 by default
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of this element relative to its parent
	 * @param x The value to be set
	 */
	public void setX(int x) {
		this.x = x;
		if(this.parent == null)
			this.renderX = x;
		else
			this.renderX = parent.getRenderX() + x;
	}

	/**
	 * Returns the y coordinate of this element relative to its parent
	 * @return 0 by default
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of this element relative to its parent
	 * @param y The value to be set
	 */
	public void setY(int y) {
		this.y = y;
		if(parent == null)
			this.renderY = y;
		else
			this.renderY = parent.getRenderY() + y;
	}

	/**
	 * Returns the x coordinate this element is to be rendered at on-screen
	 * @return 0 by default
	 */
	public int getRenderX() {
		return renderX;
	}

	/**
	 * Returns the y coordinate this element is to be rendered at on-screen
	 * @return 0 by default
	 */
	public int getRenderY() {
		return renderY;
	}

	/**
	 * Returns the width of the element
	 * @return 0 by default
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the element
	 * @param width The value to be set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Returns the height of the element
	 * @return 0 by default
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the element
	 * @param height The value to be set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns if this element is visible
	 * @return True if visible
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * Sets if this element is visible
	 * @param isVisible True if visible
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Returns the parent {@link UiElement} for this element
	 * @return Null if there is no parent
	 */
	public UiElement getParent() {
		return parent;
	}

	/**
	 * Sets the parent for this element and updates render coordinates
	 * @param parent An instance of {@link UiElement}
	 */
	public void setParent(UiElement parent) {
		this.parent = parent;
		this.renderX = parent.getRenderX() + this.x;
		this.renderY = parent.getRenderY() + this.y;
	}

	/**
	 * Returns the {@link GraphicsContext} implementation to render the element with
	 * @return 
	 */
	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	/**
	 * Sets the {@link GraphicsContext} implementation to render the element with
	 * @param graphicsContext An implementation of {@link GraphicsContext}
	 */
	public void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}
	
	public void onClick(ActionListener listener) {
		this.clickListener = listener;
	}
	
	protected void notifyClickListener() {
		if(this.clickListener != null)
			this.clickListener.onAction(this);
	}
	
	public void onHover(ActionListener listener) {
		this.hoverListener = listener;
	}
	
	protected void notifyHoverListener() {
		if(this.hoverListener != null)
			this.hoverListener.onAction(this);
	}

	public int compareTo(UiElement element) {
		if(id < element.getId())
			return -1;
		else if(id > element.getId())
			return 1;
		return 0;
	}
}
