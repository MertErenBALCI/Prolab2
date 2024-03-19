package kullanicilar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import nesneler.Kart;
import nesneler.Nesne;
import nesneler.Tas;

public class OyunOynatici {
	
	private class durumlar {
		public static final int ILKSECIM = 0;
		public static final int IKINCISECIM = 1;
		public static final int SAVASAHAREKET = 2;
		public static final int SAVAS = 3;
		public static final int ROUNDBITIR = 4;
		public static final int HAREKETBASLANGIC = 5;
		public static final int GERIHAREKET = 6;
		public static final int GERIHAREKETBASLANGIC = 7;
		
		public static final int BITIS = 8;
		public static final int KULLANICISECIM = 9;
	}
	

	protected BufferedImage TAS = null;
	protected BufferedImage MAKAS = null;
	protected BufferedImage KAGIT = null;
	
	private Oyuncu oyuncu1;
	private Oyuncu oyuncu2;
	private Nesne ilkSecim = null;
	private Nesne ikinciSecim = null;
	private int roundSayisi = 0;
	
	private float oyunHizi = 1;
	
	public int durum = durumlar.ILKSECIM;
	
	
	public OyunOynatici(Oyuncu oyuncu1, Oyuncu oyuncu2) {
		this.oyuncu1 = oyuncu1;
		this.oyuncu2 = oyuncu2;
		resimYukle();
		
		if(oyuncu1 instanceof Kullanici) {
			durum = durumlar.KULLANICISECIM;
		}
		
	}
	public OyunOynatici() {
		this.oyuncu1 = new Bilgisayar(1, false);
		this.oyuncu2 = new Bilgisayar(2, true);
		resimYukle();
		
		oyuncu1.kartAc();
		oyuncu2.kartAc();
	}
	
	public void goster(Graphics2D g) {
		if(durum != durumlar.KULLANICISECIM) {
			oyuncu1.goster(g);
			oyuncu2.goster(g);
		}
		else {
			Kart tasKart = new Kart(200, 400 - 75, 100, 150);
			Kart makasKart = new Kart(600, 400 - 75, 100, 150);
			Kart kagitKart = new Kart(1000, 400 - 75, 100, 150);
			tasKart.gorselDegistir(TAS);
			makasKart.gorselDegistir(MAKAS);
			kagitKart.gorselDegistir(KAGIT);
			
			tasKart.goster(g);
			makasKart.goster(g);
			kagitKart.goster(g);
			
		}
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
	}
	
	public boolean yenile(float x, float y, boolean mouseLeft) {
		
		switch(durum) {
		case durumlar.KULLANICISECIM:
			
			if(mouseLeft) {
				if(x > 200 && x < 300 && y > 400 - 75 && y < 400 + 75) {
					oyuncu1.nesneEkle(1);
				}
				else if(x > 600 && x < 700 && y > 400 - 75 && y < 400 + 75) {
					System.out.println("a");
					oyuncu1.nesneEkle(2);
				}
				else if(x > 1000 && x < 1100 && y > 400 - 75 && y < 400 + 75) {
					oyuncu1.nesneEkle(3);
				}
				

				try {
					Thread.sleep(360);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(oyuncu1.nesneListesiBoyutu() == 5) {
				durum = durumlar.ILKSECIM;
			}
			
			break;
		
		
		case durumlar.ILKSECIM:
			if(mouseLeft|| oyuncu1 instanceof Bilgisayar) ilkSecim = oyuncu1.nesneSec(x, y);
			if(ilkSecim != null) durum = durumlar.IKINCISECIM;
			break;
		case durumlar.IKINCISECIM:
			if(mouseLeft || oyuncu2 instanceof Bilgisayar) ikinciSecim = oyuncu2.nesneSec(x, y);
			if(ikinciSecim != null) durum = durumlar.HAREKETBASLANGIC;
			break;
		
		case durumlar.HAREKETBASLANGIC:
			ilkSecim.kartAl().pozisyonaGit(600 - 200, 400 - 75, 200 / oyunHizi);
			ikinciSecim.kartAl().pozisyonaGit(600 + 200, 400 - 75, 200 / oyunHizi);
			oyuncu1.nesneGorselSinif(ilkSecim);
			oyuncu2.nesneGorselSinif(ikinciSecim);
			durum = durumlar.SAVASAHAREKET;
			break;
		
		case durumlar.SAVASAHAREKET:
			ilkSecim.kartAl().yenile();
			ikinciSecim.kartAl().yenile();
			if(ilkSecim.kartAl().pozisyondaMi() && ikinciSecim.kartAl().pozisyondaMi()) {
				durum = durumlar.SAVAS;
			}
			break;
		case durumlar.GERIHAREKET:
			ilkSecim.kartAl().yenile();
			ikinciSecim.kartAl().yenile();
			if(ilkSecim.kartAl().pozisyondaMi() && ikinciSecim.kartAl().pozisyondaMi()) {
				oyuncu1.nesneGorselKullanilmis(ilkSecim);
				oyuncu2.nesneGorselKullanilmis(ikinciSecim);
				ilkSecim = null;
				ikinciSecim = null;
				
				if(oyuncu1.nesneListesiBoyutu() == 0 || oyuncu2.nesneListesiBoyutu() == 0) {
					durum = durumlar.ROUNDBITIR;
				}
				else {
					durum = durumlar.ILKSECIM;
				}
			}
			break;
			
		case durumlar.SAVAS:
			Nesne.darbeHesapla(ilkSecim, ikinciSecim);
			durum = durumlar.GERIHAREKETBASLANGIC;
			break;
		

		case durumlar.GERIHAREKETBASLANGIC:
			ilkSecim.kartAl().ilkPozisyonaDon(200 / oyunHizi);
			ikinciSecim.kartAl().ilkPozisyonaDon(200 / oyunHizi);
			durum = durumlar.GERIHAREKET;
			break;
		

		case durumlar.ROUNDBITIR:
			
			roundSayisi++;
			oyuncu1.yenile();
			oyuncu2.yenile();
			
			if(oyuncu1.kartKalmadi() || oyuncu2.kartKalmadi() || roundSayisi > 10) durum = durumlar.BITIS;
			else durum = durumlar.ILKSECIM;
			break;
		
		case durumlar.BITIS:
			roundSayisi = 0;
			oyuncu1.skorHesapla();
			oyuncu2.skorHesapla();
			
			oyuncu1.sifirla();
			oyuncu2.sifirla();
			durum = durumlar.ILKSECIM;
			return true;
		
		}
		return false;
	}
	
	public void oyunHiziAyarla(float hiz) {
		oyunHizi = hiz;
	}
	
	public void yazdir() {
		System.out.println(oyuncu1.oyuncuAdi + " Skor: " + oyuncu1.skor);

		System.out.println(oyuncu2.oyuncuAdi + " Skor: " + oyuncu2.skor);
	}
	
}
