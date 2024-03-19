package nesneler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import math.Vector2D;

public class Kagit extends Nesne {
	protected float nufuz;
	
	public Kagit(float x, float y, float dayaniklilik, float deneyimPuani, float nufuz) {
		super(dayaniklilik, deneyimPuani);
		this.nufuz = nufuz;

		kart.pozisyonaGit(x, y);
	}
	
	public Kagit(float x, float y, float nufuz) {
		this(x, y, 20, 0, nufuz);
	}
	
	public Kagit(float x, float y) {
		this(x, y, 20, 0, 2);
	}
	public Kagit(Vector2D pos) {
		this(pos.x, pos.y, 20, 0, 2);
	}
	
	public Kagit() {
		this(-100, -100, 20, 0, 2);
	}
	
	@Override
	public void nesnePuaniGoster(Graphics2D g) {
		String dayaniklilikYazi = "Kagit Dayaniklilik: " + Float.toString(dayaniklilik);
		String deneyimPuaniYazi = "Kagit DeneyimPuani: " + Float.toString(deneyimPuani);
		
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
			keskinlikR = ((Makas) rakip).keskinlik;
			direncR = 1;
			if(rakip instanceof UstaMakas) {
				direncR = ((UstaMakas) rakip).direnc;
			}
		}
		
		else if(rakip instanceof Kagit) {
			return 0;
		}
		
		float kagit_etkisi = (float) (nufuz / ((hasarKatsayisi * katilikR * sicaklikR) + ((1 - hasarKatsayisi) * keskinlikR * direncR)));

		return kagit_etkisi;
	}
	
	public Nesne terfiEt() {
		OzelKagit ok = new OzelKagit(dayaniklilik, deneyimPuani, nufuz, 2);
		ok.kartPozisyonAyarla(kart.pos);
		return ok;
	}
	
	
}
