package pl.kalisz.ak.pup.apkaarkadiusz28879;

import java.util.ArrayList;
import java.util.List;

public class StrukturaUczelni {
    List<String> getKierunki (String wydzial) {
        List<String> kierunki = new ArrayList<>();
        switch (wydzial) {
            case "Politechniczny":
                kierunki.add("Informatyka");
                kierunki.add("Inżynieria środowiska");
                kierunki.add("Mechanika maszyn");
                break;

            case "Medyczny":
                kierunki.add("Pielęgniarstwo");
                kierunki.add("Ratownictwo medyczne");
                break;

            case "Rehabilitacji i sportu":
                kierunki.add("Fizjoterapia");
                kierunki.add("Wychowanie fizyczne");
                break;

            case "Nauk Społecznych i Humanistycznych":
                kierunki.add("Bezpieczenstwo");
                kierunki.add("Marketing");
                break;
            default:
                kierunki.add("Wybierz wydział");
        }
        return kierunki;
    }
}
