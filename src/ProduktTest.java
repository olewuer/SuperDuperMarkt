import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

//Hoffe die Test sind so in Ordnung
class ProduktTest {


    @Test
    void checkQualityPreisKase() {
        Calendar fünfundfünfzig = Calendar.getInstance();
        fünfundfünfzig.add(Calendar.DATE, 55);

        Kase kase = new Kase(31, "Gouda", fünfundfünfzig, 3.0);
        assertEquals(6.1, kase.getTagespreis());

        kase.newday();
        assertEquals(30, kase.getQualität());
        assertEquals(6.0, kase.getTagespreis());
        assertEquals(false,kase.isWegwerfen());

        kase.newday();
        assertEquals(0, kase.getQualität());
        assertEquals(3.0, kase.getTagespreis());
        assertEquals(true,kase.isWegwerfen());

    }

    @Test
    void kaseKonstruktorQualität() {
        Calendar fünfundfünfzig = Calendar.getInstance();
        fünfundfünfzig.add(Calendar.DATE, 55);
        Throwable exeption = assertThrows(IllegalArgumentException.class, () -> new Kase(29, "Edamer", fünfundfünfzig, 4.0));
        assertEquals("Qualität zu niedrig", exeption.getMessage());
    }

    @Test
    void kaseKonstruktorVerfallsdatumUnten() {
        Calendar neunundvierzig = Calendar.getInstance();
        neunundvierzig.add(Calendar.DATE, 49);
        Throwable exeption2 = assertThrows(IllegalArgumentException.class, () -> new Kase(45, "Frischkase", neunundvierzig, 3.0));
        assertEquals("Verfallsdatum nicht richtig", exeption2.getMessage());
    }

    @Test
    void kaseKonstruktorVerfallsdatumOben() {
        Calendar hunderteins = Calendar.getInstance();
        hunderteins.add(Calendar.DATE,101);
        Throwable exeption3 = assertThrows(IllegalArgumentException.class, ()-> new Kase ( 45, "Camembert", hunderteins, 3.0));
        assertEquals("Verfallsdatum nicht richtig", exeption3.getMessage());
    }

    @Test
    void weinKonstruktor(){
        Throwable exeption = assertThrows(IllegalArgumentException.class, ()-> new Wein("Weißwein", -1, 20.0));
        assertEquals("Qualität falsch angegeben", exeption.getMessage());
    }

    @Test
    void checkQualitätundPreisWeinNach10Tagen() {
        Wein wein = new Wein("Wein", 49, 20.0);
        for (int i = 0; i < 10; i++) {
            wein.newday();
        }
        assertEquals(50, wein.getQualität());
        assertEquals(24.9, wein.getTagespreis());
    }

    @Test
    void checkWeinQualitätNichtMehrAlsFünfzig(){
        Wein wein = new Wein("Wein", 50, 20.0);
        for(int i=0; i<10;i++){
            wein.newday();
        }
        assertEquals(50, wein.getQualität());

    }
}