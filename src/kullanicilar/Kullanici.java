package kullanicilar;


import nesneler.*;

public class Kullanici extends Oyuncu {
	
	public Kullanici(int oyuncuID, String oyuncuAdi, float skor, boolean ustOyuncu) {
		super(oyuncuID, oyuncuAdi, skor, ustOyuncu);
	}
	
	public Kullanici(int oyuncuID, String oyuncuAdi) {
		this(oyuncuID, oyuncuAdi, 0, false);
	}
	
	public Kullanici (int id) {
		this(id, "Player" + Integer.toString(id), 0, false);
	}
	public Kullanici(int id, boolean ustOyuncu) {
		this(id, "Player" + Integer.toString(id), 0, ustOyuncu);
	}
	
	public Kullanici () {
		this(0, "Player0", 0, false);
	}

	@Override
	public Nesne nesneSec(float x, float y) {
		for(Nesne n: nesneListesi) {
			if(n.kartAl().fareUstundeMi(x, y)) {
				Nesne secilen = n;
				kullanilmisListesi.add(secilen);
				nesneListesi.remove(n);
				return secilen;
			}
		}
		return null;
	}

}
