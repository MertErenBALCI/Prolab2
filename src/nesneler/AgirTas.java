package nesneler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class AgirTas extends Tas {
	protected float sicaklik;
	
	public AgirTas(float dayaniklilik, float deneyimPuani, float katilik, float sicaklik) {
		super(-100, -100, dayaniklilik, deneyimPuani, katilik);
		this.sicaklik = sicaklik;
	}
	
	public AgirTas(float katilik, float sicaklik) {
		this(20, 0, katilik, sicaklik);
	}
	
	public AgirTas() {
		this(20, 0, 2, 2);
	}
	
	@Override
	public void nesnePuaniGoster(Graphics2D g) {
		String dayaniklilikYazi = "Tas Dayaniklilik: " + Float.toString(dayaniklilik);
		String deneyimPuaniYazi = "Tas DeneyimPuani: " + Float.toString(deneyimPuani);
		
		g.setColor(Color.WHITE);
		g.drawString(dayaniklilikYazi, kart.pos.x, kart.pos.y - 25);
		g.drawString(deneyimPuaniYazi, kart.pos.x, kart.pos.y - 10);
	}
	
	@Override
	public float etkiHesapla(Nesne rakip) {
		double katilikR = 0, keskinlikR = 0, nufuzR = 0, sicaklikR = 0, direncR = 0, kalinlikR = 0;
		
		if(rakip instanceof Tas) {
			return 0;
		}
		
		else if(rakip instanceof Makas) {
			keskinlikR = ((Makas) rakip).keskinlik;
			direncR = 1;
			if(rakip instanceof UstaMakas) {
				direncR = ((UstaMakas) rakip).direnc;
			}
		}
		
		else if(rakip instanceof Kagit) {
			nufuzR = ((Kagit) rakip).nufuz;
			kalinlikR = 1;
			if(rakip instanceof OzelKagit) {
				kalinlikR = ((OzelKagit) rakip).kalinlik;
			}
		}
		
		
		float tas_etkisi = (float) (katilik * sicaklik / ((hasarKatsayisi * keskinlikR * direncR) + ((1 - hasarKatsayisi) * nufuzR * kalinlikR)));

		return tas_etkisi;
	}
	
}
