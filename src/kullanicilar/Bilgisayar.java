package kullanicilar;

import java.util.Random;

import nesneler.Nesne;

public class Bilgisayar extends Oyuncu {
	
	public Bilgisayar(int oyuncuID, String oyuncuAdi, float skor, boolean ustOyuncu) {
		super(oyuncuID, oyuncuAdi, skor, ustOyuncu);
		kapali = true;
		rastgeleKart();
	}
	
	public Bilgisayar(int id, boolean ustOyuncu) {
		this(id, "Player" + Integer.toString(id), 0, ustOyuncu);
	}
	
	public Bilgisayar (int id) {
		this(id, "Player" + Integer.toString(id), 0, true);
	}
	
	public Bilgisayar () {
		this(0, "Player0", 0, true);
	}
	
	@Override
	public Nesne nesneSec(float x, float y) {
		// TODO Auto-generated method stub
		
		int randomInt = new Random().nextInt(0, nesneListesi.size());
		
		Nesne secilen = nesneListesi.get(randomInt);
		kullanilmisListesi.add(secilen);
		nesneListesi.remove(randomInt);
		
		return secilen;
	}
}
