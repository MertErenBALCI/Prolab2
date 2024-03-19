package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javax.imageio.ImageIO;

import inputs.MouseInputs;
import kullanicilar.Bilgisayar;
import kullanicilar.Kullanici;
import kullanicilar.OyunOynatici;
import kullanicilar.Oyuncu;
import math.Vector2D;
import nesneler.*;


public class Game implements Runnable {
	
	private GameWindow gamewindow;
	private GamePanel gamepanel;
	private Thread gameDrawLoop;
	
	private Random randomizer = new Random();
	
	private final int TARGET_FPS = 120;
	private final int TARGET_UPS = 200;
	
	private boolean running = true;
	
	private final float sure = 1;
	
	private float mouseX;
	private float mouseY;
	private boolean mouseLeft;
	//----------------------------------
	
	private OyunOynatici m = null;
	private boolean oyunBasladi = false;
	
	public Game() {
		LoadImage();
		InitClasses();
		
		gamepanel = new GamePanel(this);
		gamewindow = new GameWindow(gamepanel);
		gamepanel.setFocusable(true);
		gamepanel.requestFocus();
		
		startGameDraw();
		
		
	}
	
	private void LoadImage() {
	}
	
	private void startGameDraw() {
		gameDrawLoop = new Thread(this);
		gameDrawLoop.start();
	}
	
	private void InitClasses() {
		
	}

	public void update() {
		setMousePos(mouseX, mouseY);
		setLeftClick(mouseLeft);
		
		if(!oyunBasladi) {
			if(mouseLeft) {
				if(mouseX > 600 - 20 && mouseX < 660 && mouseY > 400 - 30 && mouseY < 400) {
					m = BilgisayarVSBilgisayar();
					m.oyunHiziAyarla(sure);
					oyunBasladi = true;
				}
				if(mouseX > 600 - 20 && mouseX < 660 && mouseY > 400 && mouseY < 400 + 30 ) {
					m = KullaniciVSBilgisayar();
					m.oyunHiziAyarla(sure);
					oyunBasladi = true;
				}
			}
		}
		
		if(m != null) {
			oyunBasladi = !(m.yenile(mouseX, mouseY, mouseLeft));
			if(oyunBasladi == false) {
				m.yazdir();
				m = null;
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(!oyunBasladi) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("PC vs PC", 600 - 20, 400 - 20);
			g2d.drawString("Kullanici vs PC", 600 - 20, 400 + 20);
		}
		if(m != null) {
			m.goster(g2d);
		}
	}
	
	@Override
	public void run() {
		
		double timePerFrame = 1000000000.d / TARGET_FPS;
		double timePerUpdate = 1000000000.d / TARGET_UPS;
		
		long currentTime = System.nanoTime();
		long LastTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		
		long lastTimeMilli = System.currentTimeMillis();
		long currentTimeMilli = System.currentTimeMillis();
		
		double deltaUpdate = 0;
		double deltaFrames = 0;
		while(running) {
			
			currentTime = System.nanoTime();
			currentTimeMilli = System.currentTimeMillis();
			
			deltaUpdate += (currentTime - LastTime) / timePerUpdate;
			deltaFrames += (currentTime - LastTime) / timePerFrame;
			
			LastTime = currentTime;
			
			if(deltaFrames >= 1) {
				gamepanel.repaint();
				frames++;
				deltaFrames--;
			}
			
			if(deltaUpdate >= 1) {
				gamepanel.update();
				updates++;
				deltaUpdate--;
			}
			
			if(currentTimeMilli - lastTimeMilli >= 1000) {
				lastTimeMilli = currentTimeMilli;
				
				//System.out.println("FPS: " + frames + " | Updates: " + updates);
				
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public OyunOynatici BilgisayarVSBilgisayar() {
		return new OyunOynatici();
	}
	public OyunOynatici KullaniciVSBilgisayar() {
		return new OyunOynatici(new Kullanici(1, "KULLANICI", 0, false), new Bilgisayar(2));
	}
	
	
	public void setMousePos(float x, float y) {
		mouseX = x;
		mouseY = y;
	}
	
	public void setLeftClick(boolean click) {
		mouseLeft = click;
	}
	
	public Color getRandomColor() {
		int r = randomizer.nextInt(255);
		int g = randomizer.nextInt(255);
		int b = randomizer.nextInt(255);
		return new Color(r, g, b);
	}
}
