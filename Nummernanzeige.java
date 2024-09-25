
/**
 * Die Klasse Nummernanzeige repr?sentiert Darstellungen von
 * digitalen Werten, die von Null bis zu einem vorgegebenen Limit
 * reichen k?nnen. Das Limit wird definiert, wenn eine Nummernanzeige
 * erzeugt wird. Die darstellbaren Werte reichen von Initialwert bis Limit-1.
 * Wenn beispielsweise eine Nummernanzeige f?r die Sekunden einer
 * digitalen Uhr verwendet werden soll, w?rde man ihr Initialwert auf 0 und das Limit auf 60
 * setzen, damit die dargestellten Werte von 0 bis 59 reichen.
 * Wenn der Wert einer Nummernanzeige erh?ht wird, wird bei Erreichen
 * des Limits der Wert automatisch auf Null zur?ckgesetzt.
 * 
 * Es gibt auch Nummernanzeige ohne Limit, wie z.B. das Jahr. Somit
 * wird bei der Erzeugung einer Nummernanzeige das Limit auf 0 gesetzt.
 *  
 * 
 * @author Antonia Albani
 * @version 2012.10.23
 * 
 */
public class Nummernanzeige
{
    private int limit;
    private int wert;
    private int initialwert;

    /**
     * Konstruktor f?r Exemplare der Klasse Nummernanzeige.
     * Setzt das Limit, bei dem die Anzeige zur?ckgesetzt wird und
     * den Initialwert, der angibt wo mit Z?hlen angefangen wird.
     */
    public Nummernanzeige(int anzeigeLimit, int initialwert)
    {
        limit = anzeigeLimit;
        wert = initialwert;
        this.initialwert = initialwert;
    }
    
    /**
     * Konstruktor f?r Exemplare der Klasse Nummernanzeige.
     * F?r den Fall, dass kein Limit gebraucht wird, wird Limit auf 0 
     * gesetzt. Der initialwert gibt an wo mit Z?hlen angefangen wird.
     */
    
    public Nummernanzeige(int initialwert)
    {
        limit = 0;
        wert = initialwert;
        this.initialwert = initialwert;
    }

    /**
     * Liefere den aktuellen Wert als int.
     */
    public int gibWert()
    {
        return wert;
    }

    /**
     * Dieser Methode erlaubt es das Limit neu zu setzen
     */
    public void setzeLimit(int limit){
        this.limit=limit;
    }
    /**
     * Liefere den Anzeigewert, also den Wert dieser Anzeige als
     * einen String der angegebenen L?nge. Wenn der Wert der Anzeige
     * kleiner als 'l?nge' ist, wird die Anzeige mit f?hrenden
     * Nullen einger?ckt.
     */
    public String gibAnzeigewert(int laenge)
    {
        if(laenge==2){
            if(wert < 10) {
                return "0" + wert;
            }
            else {
                return "" + wert;
            }
        }else if (laenge==3){
            if(wert < 10) {
                return "00" + wert;
            }else if (wert < 100){
                return "0" + wert;
            }else{
                return "" + wert;
            } 
        }else if (laenge==4){
            if(wert < 10) {
                return "000" + wert;
            }else if (wert < 100){
                return "00" + wert;
            }else if(wert<1000){
                return "0" + wert;
            }else{
                return "" + wert;
            }
        }else{
            return "" + wert;
        }
    }
    
 
    /**
     * Setze den Wert der Anzeige auf den angegebenen 'ersatzwert'.
     * Wenn der angegebene Wert unter Null oder ?ber dem Limit liegt,
     * tue nichts.
     */
    public void setzeWert(int ersatzwert)
    {
        if((ersatzwert >= 0) && ((ersatzwert < limit) || (limit == 0))){
            wert = ersatzwert;
        }
    }

    /**
     * Erh?he den Wert um Eins. Wenn das Limit erreicht ist, setze
     * den Wert wieder auf den Initialwert. Wenn kein Initialwert gesetzt ist, 
     * erh?he den Wert um 1.
     */
    public void erhoehen()
    {
        int zwischenwert;

        if(limit==0){
            wert = wert + 1;
        }else{
            zwischenwert = (wert + 1) % limit;
            if(zwischenwert==0){
                wert = initialwert;
            }else {
                wert = zwischenwert;
            }
        }
    }
}
