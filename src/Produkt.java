import java.util.Calendar;
import java.util.Date;


//Oberklasse Produkt
public class Produkt {
        //Eigenschaften eines Produktes
        private String bezeichnung;
        private int qualität;
        private Calendar verfallsdatum;
        private Calendar aktuellerTag;
        private double preis;
        private boolean wegwerfen;
        private double tagespreis;

        public Produkt(){
        }

        //Standart Konstruktor
        public Produkt(String bezeichnung, int qualität, Calendar verfallsdatum, double preis){
            this.bezeichnung = bezeichnung;
            this.qualität = qualität;
            this.verfallsdatum = verfallsdatum;
            this.preis = preis;
            this.tagespreis = preis + 0.1*qualität;
            this.aktuellerTag = Calendar.getInstance();
        }

        //Käse Konstruktor (nicht ganz Perfekt)
        public Produkt( int qualität, String bezeichnung, Calendar verfallsdatum, double preis){
            this.bezeichnung = bezeichnung;
            if(checkQualityKase(qualität)){
                this.qualität = qualität;
            }
            else{
                throw new IllegalArgumentException("Qualität zu niedrig");
            }
            if(checkVerfallsdatumKase(verfallsdatum)){
                this.verfallsdatum = verfallsdatum;
            }
            else{
                throw new IllegalArgumentException("Verfallsdatum nicht richtig");
            }
            this.preis = preis;
            this.tagespreis = preis + 0.1*qualität;
            this.aktuellerTag = Calendar.getInstance();
        }

        //Wein Konstruktor ohne Verfallsdatum
        public Produkt(String bezeichnung, int qualität, double preis){
            this.bezeichnung = bezeichnung;
            if(checkQualitätWein(qualität)){
                this.qualität = qualität;
            }
            else{
                throw new IllegalArgumentException("Qualität falsch angegeben");
            }
            this.verfallsdatum = null;
            this.preis = preis;
            this.tagespreis = preis + 0.1*qualität;
            this.aktuellerTag = Calendar.getInstance();
        }

        //Setter und Getter für alle benötigten Eigenschaften
        public String getBezeichnung(){return bezeichnung;}

        public void setQualität(int qual){
            qualität = qual;
        }

        public int getQualität(){return qualität;}

        public Date getVerfallsdatum() {
            if(verfallsdatum == null){
                return null;
            }
            else {
            return verfallsdatum.getTime();
            }
        }

        public double getPreis() {return preis;}

        public void setWegwerfen(boolean wegwerfen) {
            this.wegwerfen = wegwerfen;
        }

        public double getTagespreis(){return tagespreis;}

        public boolean isWegwerfen() {
            return wegwerfen;
        }

        //Updaten des Tagespreis
        public void tagespreis(){
            this.tagespreis = preis + 0.1*qualität;
        }

        //Aktuellen Tag -> Morgen
        public void addDay(){
            aktuellerTag.add(Calendar.DATE,1);
        }

        //Neuer Tag updatet Tagespreis, überprüft Verfallsdatum, setzt aktueller Tag auf Morgen
        public void newday(){
            tagespreis();
            verfallen();
            addDay();
        }

        //Überprüft ob Produkt abgelaufen ist
        public void verfallen(){

            if(verfallsdatum.before(aktuellerTag)){
                setWegwerfen(true);
            }
        }

        //Überprüft die Qualität des Käses im Konstruktor: Qualität>=30
        public boolean checkQualityKase(int quality){
            if (quality < 30){
                return false;
            }
            return true;
        }

        //Überprüft das Verfallsdatum des Käses im Konstruktor: 50=<Verfallsdatum=<100
        public boolean checkVerfallsdatumKase(Calendar verfallsdatum){
            Calendar hundred = Calendar.getInstance();
            hundred.add(Calendar.DATE,100);
            Calendar fifty = Calendar.getInstance();
            fifty.add(Calendar.DATE,49);

            if(verfallsdatum.after(hundred) || verfallsdatum.before(fifty)){
                return false;

            }
            return true;
        }

        //Überprüft die Qualität des Weins im Konstruktor: 0=<Qualität<=50
        public boolean checkQualitätWein(int qualität){
            if(qualität<0 || qualität > 50){
                return false;
            }
            return true;
        }

}

