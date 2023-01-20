//Wein vererbt von Produkt, da ähnlichkeit zu Produkt besteht
public class Wein extends Produkt{

    private int tage;

    //Konstruktor des Weins ohne Verfallsdatum, da Wein nicht verfällt
    public Wein(String bezeichnung, int qualität, double preis) {
        super(bezeichnung, qualität, preis);
    }

    //Neuer Tag wird angepasst, da Wein alle 10 Tage an Qualität zunimmt und weil Wein nicht verfällt
    @Override
    public void newday() {
        tage++;
        if(tage == 10 && this.getQualität()<50){
            this.setQualität(this.getQualität()+1);
            tage = 0;
        }

    }

}
