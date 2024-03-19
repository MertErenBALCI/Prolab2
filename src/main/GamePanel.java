package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import math.Vector2D;

public class GamePanel extends JPanel {
	JPanel c = new JPanel();
	MouseInputs mouseInputs;
	
	private Game game;
	
	private final Color backgroundColor = new Color(12, 12, 23, 255);
	
	public GamePanel(Game game) {
		this.game = game;
		
		mouseInputs = new MouseInputs(this)
;		setSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
	}
	
	private void setSize() {
		Dimension size = new Dimension(1200, 800);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, 1200, 800);
		
		game.render(g);
	}

	public void update() {
		game.update();
	}

	
	public Game getGame() {
		return game;
	}
	
}
