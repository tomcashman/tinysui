/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.tinysui.core.ActionListener;
import org.tinysui.core.Button;
import org.tinysui.core.Label;
import org.tinysui.core.TextBox;
import org.tinysui.core.UiElement;
import org.tinysui.core.container.RootUiElement;
import org.tinysui.core.container.Window;
import org.tinysui.core.list.BasicListItem;
import org.tinysui.core.list.ListBox;

/**
 * Implements a demo of all UI components
 * 
 * @author Thomas Cashman
 */
public class TinySUIDemo extends BasicGame {
	private RootUiElement m_rootUiElement;

	public TinySUIDemo() {
		super("TinySUI 0.1 - Demo");
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		m_rootUiElement = new RootUiElement(gc);
		gc.getInput().enableKeyRepeat();
		gc.setTargetFrameRate(30);
		gc.setVSync(true);
		elementSetup(gc);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		m_rootUiElement.update(gc, delta);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		m_rootUiElement.render(gc, g);
	}
	
	private void elementSetup(GameContainer gc) {
		Window window = new Window(32, 32, 360, 320);
		window.setTitle("Test window");
		window.setDraggable(true);
		m_rootUiElement.addChild(window);
		
		Label label = new Label(8, 8, 128, 24);
		label.setText("This is a label. Below is a textbox.");
		window.addChild(label);
		
		TextBox normalTextBox = new TextBox(8, 32, 128, 24);
		window.addChild(normalTextBox);
		
		label = new Label(8, 64, 128, 24);
		label.setText("Below is a password box.");
		window.addChild(label);
		
		TextBox passwordBox = new TextBox(8, 92, 128, 24);
		passwordBox.setCharacterMaskEnabled(true);
		window.addChild(passwordBox);
		
		ListBox listBox = new ListBox(8, 120, 128, 96);
		BasicListItem listItem = new BasicListItem("Test text", "Test value");
		listBox.addItem(listItem);
		window.addChild(listBox);
		
		Button button = new Button(8, 224, 128, 24);
		button.setText("Button");
		button.setClickListener(new ActionListener() {
			@Override
			public void handleEvent(UiElement source) {
				System.out.println("Button clicked!");
			}
		});
		window.addChild(button);
	}
	
	public static void main(String [] args) {
		try {
			AppGameContainer gc = new AppGameContainer(new TinySUIDemo(), 800, 600, false);
			gc.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_ESCAPE)
			System.exit(0);
		m_rootUiElement.handleInput(key, c);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		m_rootUiElement.mouseMoved(oldx, oldy, newx, newy);
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		m_rootUiElement.mouseMoved(oldx, oldy, newx, newy);
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		m_rootUiElement.mousePressed(button, x, y);
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		m_rootUiElement.mouseReleased(button, x, y);
	}
	
	@Override
	public void mouseWheelMoved(int change) {
		m_rootUiElement.mouseWheelMoved(change);
	}
}
