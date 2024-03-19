package nesneler;

import java.awt.Color;
import java.awt.Graphics2D;

import math.Functions;
import math.Vector2D;

public abstract class Nesne {
	protected Kart kart;
	protected float deneyimPuani;
	protected float dayaniklilik;
	protected boolean terfiEtmisMi;
	
	public boolean hayatta;
	public float hasarKatsayisi = 0.2f;
	
	public Nesne(float dayaniklilik, float deneyimPuani) {
		kart = new Kart(-100, -100, 100, 150);
		hayatta = true;
		terfiEtmisMi = false;
		this.deneyimPuani = deneyimPuani;
		this.dayaniklilik = dayaniklilik;
	}
	
	public Nesne() {
		this(20, 0);
	}
	
	public float deneyimPuaniAl() {
		return deneyimPuani;
	}
	
	public Kart kartAl() {
		return kart;
	}

	public float dayaniklilikAl() {
		return dayaniklilik;
	}
	
	
	public void goster(Graphics2D g) {
		kart.goster(g);
	}
	
	public void kartPozisyonAyarla(float x, float y) {
		kart.pozisyonaGit(x, y);
	}
	public void kartPozisyonAyarla(Vector2D yeniPozisyon) {
		kartPozisyonAyarla(yeniPozisyon.x, yeniPozisyon.y);
	}
	
	public void nesnePuaniGoster(Graphics2D g) {
		String dayaniklilikYazi = "Dayaniklilik: " + Float.toString(dayaniklilik);
		String deneyimPuaniYazi = "DeneyimPuani: " + Float.toString(deneyimPuani);
		
		g.setColor(Color.WHITE);
		g.drawString(dayaniklilikYazi, kart.pos.x, kart.pos.y - 25);
		g.drawString(deneyimPuaniYazi, kart.pos.x, kart.pos.y - 10);
	}
	
	public abstract float etkiHesapla(Nesne rakip);
	
	public void durumGuncelle(Nesne rakip, float darbe) {
		rakip.dayaniklilik -= darbe;
		
		if(rakip.dayaniklilik <= 0.01) {
			rakip.dayaniklilik = 0;
			deneyimPuani += 20;		
			rakip.hayatta = false;
		}
		
	}
	
	public static void darbeHesapla(Nesne n1, Nesne n2) {
		float ilkD = n1.etkiHesapla(n2);
		float ikiD = n2.etkiHesapla(n1);
		n1.durumGuncelle(n2, ilkD);
		n2.durumGuncelle(n1, ikiD);
		
		/*
		System.out.println("Ä°lk darbe: " + Double.toString(ilkD));
		System.out.println("N1 dayaniklilik: " + Float.toString(n1.dayaniklilik));
		System.out.println("Ä°kinci darbe: " + Double.toString(ikiD));
		System.out.println("N2 dayabiklilik: " + Float.toString(n2.dayaniklilik));
		*/
	}
	
	public abstract Nesne terfiEt();

}
