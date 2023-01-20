import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        //Liste aus CSV oder vorgefertigt
        System.out.println("Wollen sie eine CSV-Datei einlesen dann drücken sie die 1. Sonst drücken Sie eine beliebige Zahl ");
        Scanner sc = new Scanner(System.in);
        List<Produkt>list = new ArrayList<>();
        int j = sc.nextInt();

        if(j==1){
            String pfad;
            System.out.println("Geben sie den Dateipfad an:");
            pfad = sc.next(); //C:\\Users\\olew1\\OneDrive\\Dokumente\\Produkte.csv
            list = readCSV(pfad);
        }
        else{
            list = createList();
        }

        //Liste am ersten Tag ausgeben und anschließend fragen ob ein neues Produkt hinzugefügt werden soll
        //ein Neuer Tag beginnt oder das Programm beendet werden soll
        listeErsterTag(list);
        Scanner choose = new Scanner(System.in);
        int choice= 1;
        while (!(0 == choice)) {
            System.out.println("\nWenn Sie ein neues Produkt hinzufügen wollen schreiben Sie -1. \nWenn Sie Tage vergehen lassen wollen, schreiben sie die Anzahl.\nZum Beenden schreib  \"0\".");
            choice = choose.nextInt();
            if (choice >0) {
                for(int i=0; i<choice;i++){
                    neuerTag(list);
                }
                listeAusgeben(list);
            }
            if(choice<0){
                list.add(createProdukt());
            }


        }
        choose.close();

    }

    //Funktion gibt alle Informationen der Produktliste aus
    static void listeErsterTag(List<Produkt> liste){
        for(Produkt product:liste){
            System.out.println("\nBezeichnung: " + product.getBezeichnung() );
            System.out.println("Verfallsdatum: " + product.getVerfallsdatum());
            System.out.println("Preis: " + product.getPreis()+"€");
            System.out.println("Tagespreis: " + product.getTagespreis()+"€");
            System.out.println("Qualität: " + product.getQualität());
            System.out.println("Wegwerfen: " + product.isWegwerfen() + " \n");

        }
    }

    //Gibt die wichtigsten Informationen der Produktliste aus
    static void listeAusgeben(List<Produkt> liste){
        for(Produkt product:liste){
            System.out.println("\nBezeichnung: " + product.getBezeichnung());
            System.out.println("Tagespreis: " + product.getTagespreis()+"€");
            System.out.println("Qualität: " + product.getQualität());
            System.out.println("Wegwerfen: " + product.isWegwerfen() + " \n");

        }

    }

    //Ruft die Funktion newday für alle Elemente der Liste auf
    static void neuerTag(List<Produkt> liste){
        for(Produkt produkt:liste){
        //Man kann noch überprüfen ob das Objekt weggeschmissen werden soll
        //und gegebenenfalls löschen wenn wegwerfen true
            produkt.newday();
        }
        System.out.println("\nNeuer Tag-------------------------------\n");
    }

    //Erstellt eine Liste mit vorgefertigten Objekten
    static List<Produkt> createList(){
        Calendar heute = Calendar.getInstance();
        Calendar morgen = Calendar.getInstance();
        morgen.add(Calendar.DATE,1);

        Calendar fünfundfünfzig = Calendar.getInstance();
        fünfundfünfzig.add(Calendar.DATE, 55);

        Produkt produktTest1 = new Produkt("Chips", 40, heute, 3.0);
        Produkt produktTest2 = new Produkt("Haribo", 35, fünfundfünfzig, 5.0);

        Kase kaseTest1 = new Kase( 45, "Gouda", fünfundfünfzig, 3.0);

        Wein weinTest1 = new Wein("Rotwein", 40, 20.0);
        Wein weinTest2 = new Wein("Rotwein", 50, 20.0);


        List<Produkt>list = new ArrayList<>();
        list.add(produktTest1);
        list.add(produktTest2);
        list.add(kaseTest1);

        list.add(weinTest1);
        list.add(weinTest2);

        return list;
    }

    //Liest Informationen aus einer CSV Datei aus und Speichert Objekte in einer Liste
    static List<Produkt> readCSV(String dateiname) throws IOException, ParseException {
        List<Produkt>list = new ArrayList<>();

        String line = "";
        String splitBy = ";";
        BufferedReader br = new BufferedReader(new FileReader(dateiname));
        while ((line = br.readLine()) != null)
        {
            String[] produkt = line.split(splitBy);

            if(produkt[0].equals("Produkt")){

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                cal.setTime(sdf.parse(produkt[3]));

                Produkt csvProdukt = new Produkt(produkt[1],Integer.parseInt(produkt[2]), cal, Double.parseDouble(produkt[4]));
                list.add(csvProdukt);
            }
            if(produkt[0].equals("Kase")){
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                cal.setTime(sdf.parse(produkt[3]));

                Kase csvKase = new Kase(Integer.parseInt(produkt[2]), produkt[1], cal, Double.parseDouble(produkt[4]));
                list.add(csvKase);

            }
            if(produkt[0].equals("Wein")){
                Wein csvWein = new Wein(produkt[1],Integer.parseInt(produkt[2]), Double.parseDouble(produkt[4]));
                list.add(csvWein);
            }



        }

        return list;
    }

    //bietet die möglichkeit über die Konsole ein neues Objekt zu erstellen
    static Produkt createProdukt() throws ParseException {
        Scanner psc = new Scanner(System.in);
        System.out.println("Neues Produkt: 1  Neuer Käse: 2  Neuer Wein:  3");
        int j = psc.nextInt();

        if(j == 1){
            System.out.println("Bitte geben Sie Beschreibung, Qualität, Datum und Preis an");
            String beschreibung = psc.next();
            int qualität = psc.nextInt();
            String datum = "";
            datum= psc.nextLine();
            datum+= psc.nextLine(); //Thu Mar 16 10:16:37 CET 2023
            System.out.println(datum);
            double preis = psc.nextDouble();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            cal.setTime(sdf.parse(datum));

            Produkt produkt = new Produkt(beschreibung, qualität, cal, preis);
            return produkt;
        }
        if(j == 2){
            System.out.println("Bitte geben Sie Beschreibung, Qualität, Datum und Preis an");
            String beschreibung = psc.next();
            int qualität = psc.nextInt();
            String datum = "";
            datum= psc.nextLine();
            datum+= psc.nextLine();
            double preis = psc.nextDouble();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            cal.setTime(sdf.parse(datum));

            Kase kase = new Kase( qualität, beschreibung, cal, preis);
            return kase;
        }
        if(j == 3){
            System.out.println("Bitte geben Sie Beschreibung, Qualität und Preis an");
            String beschreibung = psc.next();
            int qualität = psc.nextInt();
            double preis = psc.nextDouble();

            Wein wein = new Wein(beschreibung, qualität, preis);
            return wein;

        }
        return null;
    }

}
