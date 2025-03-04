package de.lukas.LA23NT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Messwertsatz {
    private ArrayList<Messwert> werte;
    private String eigenschaft;
    private String einheit;
    private Mitarbeiter mitarbeiter;
    private Double min;
    private Double max;


    public Messwertsatz(String eigenschaft, String einheit, Mitarbeiter mitarbeiter) {
        this.eigenschaft = eigenschaft;
        this.einheit = einheit;
        this.mitarbeiter = mitarbeiter;
    }

    public void hinzufuegenWert(Double mw, Date messzeit) {
        werte.add(new Messwert(mw, messzeit));
        if (min == null || mw < min) {
            min = mw;
        }
        if (max == null || mw > max) {
            max = mw;
        }
        //


    }
    public double ermittleMedian() {
        return 0;
    }

    public void sortiereWerteNachMW() {
        werte.sort(new Comparator<Messwert>() {
            @Override
            public int compare(Messwert o1, Messwert o2) {
                return o1.getWert().compareTo(o2.getWert());
            }
        });
    }

    public ArrayList<Messwert> getWerte() {
        return werte;
    }
}
