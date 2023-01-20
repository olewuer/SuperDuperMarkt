import java.util.Calendar;

//Käse vererbt vom Produkt, da nur einige Verarbeitungsregeln angepasst werden müssen
public class Kase extends Produkt {

    //Konstruktor wird leicht angepasst um die Eigenschaften unabhängig vom Produkt zu Testen
    public Kase(int qualität, String bezeichnung,  Calendar verfallsdatum, double preis) {
        super(qualität, bezeichnung, verfallsdatum, preis);

    }

    //Setter Qualität wird angepasst um Qualität zu prüfen
    @Override
    public void setQualität(int qual) {
        if(qual >= 30) {
            super.setQualität(qual);
        }
        else{
            this.setWegwerfen(true);
            super.setQualität(0);
        }
    }

    //Neuer Tag wird angepasst sodass die Qualität pro Tag um 1 abnimmt
    @Override
    public void newday() {
        this.setQualität(this.getQualität()-1);
        tagespreis();
        verfallen();
        addDay();
    }

}
