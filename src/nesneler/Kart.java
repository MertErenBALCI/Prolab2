package nesneler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import math.Functions;
import math.Vector2D;

public class Kart {
	float xH;
	float yH;
	float hedefX;
	float hedefY;
	
	Vector2D ilkPos;
	public Vector2D pos;
	public Vector2D boyut;
	public BufferedImage gorsel = null;
	
	public Color color;
	
	public Kart(float x, float y,  float en, float boy) {
		pos = new Vector2D(x, y);
		ilkPos = new Vector2D(x, y);
		boyut = new Vector2D(en, boy);
		color = Color.RED;
		
		gorsel = new BufferedImage(100, 150, BufferedImage.TYPE_INT_ARGB);
		
		xH = 0;
		yH = 0;
	}
	
	public Kart() {
		this(0, 0, 0, 0);
	}
	
	public boolean fareUstundeMi(float x, float y) {
		return x >= pos.x && x <= pos.x + boyut.x && y >= pos.y && y <= pos.y + boyut.y;
	}
	
	public void gorselDegistir(BufferedImage gorsel) {
		this.gorsel = gorsel;
	}
	
	public void renkDegistir() {
		if(color == Color.RED) {
			color = Color.YELLOW;
		}
		else {
			color = Color.RED;
		}
	}
	
	public void gorselDondur() {
		gorsel = Functions.rotatedImage(gorsel, 180);
	}
	
	public boolean pozisyondaMi() {
		return xH == 0 && yH == 0;
	}
	
	public void goster(Graphics2D g) {
		g.setColor(color);
		//g.fillRect((int) pos.x - 5, (int) pos.y - 5, (int) boyut.x + 10, (int) boyut.y + 10);
		g.drawImage(gorsel, (int) pos.x, (int) pos.y, null);
	}
	public void yenile() {
		pos.x += xH;
		pos.y += yH;
		
		if(pos.x == hedefX) xH = 0;
		if(pos.y == hedefY) yH = 0;
	}
	
	public void pozisyonaGit(float x, float y) {
		pos.x = x;
		pos.y = y;
		ilkPos.x = x;
		ilkPos.y = y;
	}
	
	public void pozisyonaGit(float x, float y, float s) {
		xH = (x - pos.x) / s;
		yH = (y - pos.y) / s;
		hedefX = x;
		hedefY = y;
	}
	public void ilkPozisyonaDon(float s) {
		pozisyonaGit(ilkPos.x, ilkPos.y, s);
	}
	
}
