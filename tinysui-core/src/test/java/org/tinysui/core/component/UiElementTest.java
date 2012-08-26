/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.tinysui.core.listener.KeyListener;

/**
 * Implements unit tests for {@link UiElement}
 * 
 * @author Thomas Cashman
 */
public class UiElementTest {
	private UiElement element;
	private UiElement parent;
	private Random random;
	
	@Before
	public void setup() {
		random = new Random();
		
		element = new UiElement() {
			@Override
			public void update(int delta) {}

			@Override
			public void render() {}

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
		};
		parent = new UiElement() {
			@Override
			public void update(int delta) {}

			@Override
			public void render() {}

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
		};
		parent.setX(random.nextInt());
		parent.setY(random.nextInt());
	}
	
	@Test
	public void testId() {
		Assert.assertEquals(0, element.getId());
		Assert.assertTrue(element.getId() != parent.getId());
		Assert.assertTrue(element.compareTo(parent) != 0);
	}
	
	@Test
	public void testX() {
		int x = random.nextInt();
		
		element.setX(x);
		Assert.assertEquals(x, element.getX());
		
		element.setParent(parent);
		Assert.assertEquals(x, element.getX());
		Assert.assertEquals(parent.getX() + x, element.getRenderX());
		
		x = random.nextInt();
		element.setX(x);
		Assert.assertEquals(x, element.getX());
		Assert.assertEquals(parent.getX() + x, element.getRenderX());
	}
	
	@Test
	public void testY() {
		int y = random.nextInt();
		
		element.setY(y);
		Assert.assertEquals(y, element.getY());
		
		element.setParent(parent);
		Assert.assertEquals(y, element.getY());
		Assert.assertEquals(parent.getY() + y, element.getRenderY());
		
		y = random.nextInt();
		element.setY(y);
		Assert.assertEquals(y, element.getY());
		Assert.assertEquals(parent.getY() + y, element.getRenderY());
	}
	
	@Test
	public void testWidth() {
		int width = random.nextInt();
		
		element.setWidth(width);
		Assert.assertEquals(width, element.getWidth());
	}
	
	@Test
	public void testHeight() {
		int height = random.nextInt();
		
		element.setHeight(height);
		Assert.assertEquals(height, element.getHeight());
	}
	
	@Test
	public void testVisible() {
		element.setVisible(true);
		Assert.assertEquals(true, element.isVisible());
		
		element.setVisible(false);
		Assert.assertEquals(false, element.isVisible());
	}
	
	@Test
	public void testParent() {
		element.setParent(parent);
		Assert.assertNotNull(element.getParent());
		Assert.assertTrue(element.getParent().compareTo(parent) == 0);
	}
}
