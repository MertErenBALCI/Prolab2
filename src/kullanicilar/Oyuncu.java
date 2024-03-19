package kullanicilar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import nesneler.*;

public abstract class Oyuncu {
	boolean ustOyuncu;
	int y;
	boolean kapali;
	public int sayi = 5;
	
	protected BufferedImage TAS = null;
	protected BufferedImage AGIRTAS = null;
	protected BufferedImage MAKAS = null;
	protected BufferedImage USTAMAKAS = null;
	protected BufferedImage KAGIT = null;
	protected BufferedImage OZELKAGIT = null;
	protected BufferedImage KAPALI = null;
	protected BufferedImage KULLANILMIS = null;
	
	
	protected int oyuncuID;
	protected String oyuncuAdi;
	protected float skor;
	
	protected ArrayList<Nesne> nesneListesi;
	protected ArrayList<Nesne> kullanilmisListesi;
	int xPos = 1;
	
	public Oyuncu(int oyuncuID, String oyuncuAdi, float skor, boolean ustOyuncu) {
		this.oyuncuID = oyuncuID;
		this.oyuncuAdi = oyuncuAdi;
		this.skor = skor;
		this.kapali = false;
		
		if(ustOyuncu) y = 100;
		else y = 600;

		resimYukle();
		
		nesneListesi = new ArrayList<Nesne>();
		
		kullanilmisListesi = new ArrayList<Nesne>();
		
	}
	
	public Oyuncu(int id) {
		this(id, "Player" + Integer.toString(id), 0, false);
	}
	
	public Oyuncu() {
		this(0, "Player0", 0, false);
	}
	
	private void resimYukle() {
		String p = Paths.get("resimler").toAbsolutePath().toString();
		
		String a = p + "/Tas.png";
		
		try {
			TAS = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
		
		a = p + "/Makas.png";
		try {
			MAKAS = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
		
		a = p + "/Kagit.png";
		try {
			KAGIT = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
		
		a = p + "/AgirTas.png";
		try {
			AGIRTAS = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
		
		a = p + "/UstaMakas.png";
		try {
			USTAMAKAS = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
		
		a = p + "/OzelKagit.png";
		try {
			OZELKAGIT = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
		
		a = p + "/Kapali.jpg";
		try {
			KAPALI = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
		
		a = p + "/Kullanilmis.jpg";
		try {
			KULLANILMIS = ImageIO.read(new File(a));
		} catch (IOException e) {
			System.out.println("Dosya okunamadi");
			System.exit(-1);
		}
	}
	

	public void kartAc() {
		kapali = false;
	}
	public void kartKapa() {
		kapali = true;
	}
	
	public void nesneEkle(int s) {
		Nesne n;
		switch(s) {
		case 1:
			n = new Tas(xPos*175, y);
			xPos++;
			nesneGorselSinif(n);
			nesneListesi.add(n);
			break;
			
		case 2:
			n = new Makas(xPos*175, y);
			xPos++;
			nesneGorselSinif(n);
			nesneListesi.add(n);
			break;
		
		case 3:
			n = new Kagit(xPos*175, y);
			xPos++;
			nesneGorselSinif(n);
			nesneListesi.add(n);
			break;
			
		}
	}
	
	public void rastgeleKart() {
		for(int i = 0; i < 5; i++) {
			int rastgele = new Random().nextInt(0, 3);
			Nesne a;
			switch(rastgele) {
			case 0:
				a = new Tas((i+1)*175, y);
				if(kapali) nesneGorselKapa(a);
				else nesneGorselSinif(a);
				nesneListesi.add(a);
				break;
			case 1:
				a = new Makas((i+1)*175, y);
				if(kapali) nesneGorselKapa(a);
				else nesneGorselSinif(a);
				nesneListesi.add(a);
				break;
			case 2:
				a = new Kagit((i+1)*175, y);
				if(kapali) nesneGorselKapa(a);
				else nesneGorselSinif(a);
				nesneListesi.add(a);
				break;
			}
		}
	}
	
	public abstract Nesne nesneSec(float x, float y);
	
	
	public String adAl() {
		return oyuncuAdi;
	}
	public int idAl() {
		return oyuncuID;
	}
	public float skorAl() {
		return skor;
	}
	
	public void nesneLisesindenSil(Nesne n) {
		if(nesneListesi.contains(n)) nesneListesi.remove(n);
	}
	
	public void yenile() {
	
		int s = kullanilmisListesi.size();
			
		for(int i = 0; i < s; i++) {
			if(kullanilmisListesi.get(i).deneyimPuaniAl() > 15) {
				Nesne a = kullanilmisListesi.get(i);
				a = a.terfiEt();
				if(kapali) nesneGorselKapa(a);
				else nesneGorselSinif(a);
				kullanilmisListesi.remove(i);
				kullanilmisListesi.add(i, a);
			}
		}
			
			
		for(int i = 0; i < s; i++) {
			if(kullanilmisListesi.get(0).dayaniklilikAl() > 0) {
				Nesne a = kullanilmisListesi.get(0);
				if(kapali) nesneGorselKapa(a);
				else nesneGorselSinif(a);
				nesneListesi.add(a);
			}
			else {
				sayi--;
			}
			kullanilmisListesi.remove(0);
		}
	}
	public int nesneListesiBoyutu() {
		return nesneListesi.size();
	}
	
	public void nesneGorselKapa(Nesne n) {
		n.kartAl().gorselDegistir(KAPALI);
	}
	public void nesneGorselKullanilmis(Nesne n) {
		n.kartAl().gorselDegistir(KULLANILMIS);
	}
	
	public boolean kartKalmadi() {
		return sayi == 0;
	}
	
	public void sifirla() {
		sayi = 5;
		nesneListesi.clear();
		kullanilmisListesi.clear();
		rastgeleKart();
	}
	
	public void nesneGorselSinif(Nesne n) {
		
		if(n instanceof Tas) {
			if(n instanceof AgirTas) {
				n.kartAl().gorselDegistir(AGIRTAS);
				return;
			}
			n.kartAl().gorselDegistir(TAS);
			return;
		}
		else if(n instanceof Makas) {
			if(n instanceof UstaMakas) {
				n.kartAl().gorselDegistir(USTAMAKAS);
				return;
			}
			n.kartAl().gorselDegistir(MAKAS);
			return;
		}
		else if(n instanceof Kagit) {
			if(n instanceof OzelKagit) {
				n.kartAl().gorselDegistir(OZELKAGIT);
				return;
			}
			n.kartAl().gorselDegistir(KAGIT);
			return;
		}
	}
 	
	public void goster(Graphics2D g) {
            try{
		for(Nesne n : kullanilmisListesi) {
			n.goster(g);
			if(!kapali) {
				n.nesnePuaniGoster(g);
			}
		}
		for(Nesne n : nesneListesi) {
			n.goster(g);
			if(!kapali) {
				n.nesnePuaniGoster(g);
			}
		}
            }
            catch( java.util.ConcurrentModificationException e){
            }
            }
		
	
	
	public void skorHesapla() {
		for(Nesne n : nesneListesi) {
			skor += n.dayaniklilikAl();
		}
	}
	
}
