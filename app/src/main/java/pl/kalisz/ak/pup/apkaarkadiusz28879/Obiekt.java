package pl.kalisz.ak.pup.apkaarkadiusz28879;

public class Obiekt {
    String nazwa;
    String opis;
    int fotoId;
    public static final Obiekt[] obiekty = {
            new Obiekt("Rektorat", "Nowy Swiat 4", R.drawable.crektorat),
            new Obiekt("Collegium Novum", "Nowy Swiat 4a", R.drawable.cnovum),
            new Obiekt("Collegium Oecologicum", "Poznanska 201-205", R.drawable.coecologicum),
            new Obiekt("Collegium Medicum", "Kaszubska 13", R.drawable.cmedicum),
            new Obiekt("Collegium Mechanicum", "Poznanska 201-205", R.drawable.cmechanicum)
    };
    private Obiekt(String nazwa, String opis, int fotoId)
    {
        this.nazwa = nazwa;
        this.opis = opis;
        this.fotoId = fotoId;
    }
    public String getNazwa(){return nazwa;}
    public String getOpis(){return opis;}
    public int getFotoId(){return fotoId;}
}
