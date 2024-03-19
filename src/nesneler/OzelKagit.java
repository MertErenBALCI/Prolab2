package nesneler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OzelKagit extends Kagit {
	protected float kalinlik;
	
	public OzelKagit(float dayaniklilik, float deneyimPuani, float nufuz, float kalinlik) {
		super(-100, -100, dayaniklilik, deneyimPuani, nufuz);
		this.kalinlik = kalinlik;
	}
	
	public OzelKagit(float nufuz, float kalinlik) {
		this(20, 0, nufuz, kalinlik);
	}
	
	public OzelKagit() {
		this(20, 0, 2, 2);
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
		
		
		float kagit_etkisi = (float) (nufuz * kalinlik / ((hasarKatsayisi * katilikR * sicaklikR) + ((1 - hasarKatsayisi) * keskinlikR * direncR)));

		return kagit_etkisi;
	}
	
	
}
