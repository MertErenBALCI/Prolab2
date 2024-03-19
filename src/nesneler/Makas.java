package nesneler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import math.Vector2D;

public class Makas extends Nesne {
	protected float keskinlik;
	
	public Makas(float x, float y, float dayaniklilik, float deneyimPuani, float keskinlik) {
		super(dayaniklilik, deneyimPuani);
		this.keskinlik = keskinlik;
		
		kart.pozisyonaGit(x, y);
		
	}
	
	public Makas(float x, float y, float keskinlik) {
		this(-100, -100, 20, 0, keskinlik);
	}
	
	public Makas(float x, float y) {
		this(x, y, 20, 0, 2);
	}
	public Makas(Vector2D pos) {
		this(pos.x, pos.y, 20, 0, 2);
	}
	
	public Makas() {
		this(-100, - 100, 20, 0, 2);
	}
	
	@Override
	public void nesnePuaniGoster(Graphics2D g) {
		String dayaniklilikYazi = "Makas Dayaniklilik: " + Float.toString(dayaniklilik);
		String deneyimPuaniYazi = "Makas DeneyimPuani: " + Float.toString(deneyimPuani);
		
		g.setColor(Color.WHITE);
		g.drawString(dayaniklilikYazi, kart.pos.x, kart.pos.y - 25);
		g.drawString(deneyimPuaniYazi, kart.pos.x, kart.pos.y - 10);
	}
	
	@Override
	public float etkiHesapla(Nesne rakip) {
		double katilikR = 0, keskinlikR = 0, nufuzR = 0, sicaklikR = 0, direncR = 0, kalinlikR = 0;
		
		if(rakip instanceof Tas) {
			katilikR = ((Tas) rakip).katilik;
			sicaklikR = 1;
			if(rakip instanceof AgirTas) {
				sicaklikR = ((AgirTas) rakip).sicaklik;
			}
		}
		
		else if(rakip instanceof Makas) {
			return 0;
		}
		
		else if(rakip instanceof Kagit) {
			nufuzR = ((Kagit) rakip).nufuz;
			kalinlikR = 1;
			if(rakip instanceof OzelKagit) {
				kalinlikR = ((OzelKagit) rakip).kalinlik;
			}
		}
		
		float makas_etkisi = (float) (keskinlik / ((hasarKatsayisi * nufuzR * kalinlikR) + ((1 - hasarKatsayisi) * katilikR * sicaklikR)));

		return makas_etkisi;
	}
	
	public Nesne terfiEt() {
		UstaMakas um = new UstaMakas(dayaniklilik, deneyimPuani, keskinlik, 2);
		um.kartPozisyonAyarla(kart.pos);
		return um;
	}
}
