package nesneler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class UstaMakas extends Makas {
	protected float direnc;
	
	public UstaMakas(float dayaniklilik, float deneyimPuani, float keskinlik, float direnc) {
		super(-100, -100, dayaniklilik, deneyimPuani, keskinlik);
		this.direnc = direnc;
		
	}
	
	public UstaMakas(float keskinlik, float direnc) {
		this(20, 0, keskinlik, direnc);
	}
	
	public UstaMakas() {
		this(20, 0, 2, 2);
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
		
		
		float makas_etkisi = (float) (keskinlik * direnc / ((hasarKatsayisi * nufuzR * kalinlikR) + ((1 - hasarKatsayisi) * katilikR * sicaklikR)));

		return makas_etkisi;
	}
}

