
/**
 * Die Klassen Uhrenanzeige implementiert die Anzeige einer Digitaluhr.
 * Die Anzeige zeigt Tag, Monat, Jahr, Stunden und Minuten an. Der Anzeigebereich der Stunden und Minuten reicht von
 * 00:00 (Mitternacht) bis 23:59 (eine Minute vor Mitternacht).
 *
 * Eine Uhrenanzeige sollte min?tlich "Taktsignale" (?ber die Operation
 * "taktsignalGeben") erhalten, damit sie die Anzeige aktualisieren 
 * kann. Dies geschieht, wie man es bei einer Uhr erwartet: Die 
 * Stunden erh?hen sich, wenn das Minutenlimit einer Stunde erreicht
 * ist. Die Tage erh?hen sich, wenn das Stundenlimit erreicht ist, etc.
 * 
 * @author Antonia Albani
 * @version 2021.10.05
 */
public class Uhrenanzeige 
{
    private Nummernanzeige stunden;
    private Nummernanzeige minuten;
    private Nummernanzeige monate;
    private Nummernanzeige tage;
    private Nummernanzeige jahre;
    private String zeitanzeigeD;// simuliert die Deutsche Anzeige
    private String zeitanzeigeB;// simuliert die Britische Anzeige
    
    /**
     * Konstruktor f?r ein Exemplar von Uhrenanzeige.
     * Mit diesem Konstruktor wird die Anzeige auf 01.01.0000 00:00 initialisiert.
     */
    public Uhrenanzeige()
    {
        jahre = new Nummernanzeige(0);
        monate = new Nummernanzeige(13, 1);              
        tage = new Nummernanzeige(32, 1);
        stunden = new Nummernanzeige(24, 0);
        minuten = new Nummernanzeige(60, 0);
        anzeigeAktualisieren();
    }

    /**
     * Konstruktor f?r ein Exemplar von Uhrenanzeige.
     * Mit diesem Konstruktor wird die Anzeige auf den Wert
     * initialisiert, der durch 'tag', 'monat', 'jahr', 'stunde' und 'minute' 
     * definiert ist.
     */
    public Uhrenanzeige(int tag, int monat, int jahr, int stunde, int minute)
    {
        stunden = new Nummernanzeige(24,0);
        minuten = new Nummernanzeige(60,0);
        jahre = new Nummernanzeige(1);
        monate = new Nummernanzeige(13,1);  
        if(monat==1 || monat==3 || monat==5 || monat==7 || monat==8 || monat==10|| monat==12){
            tage = new Nummernanzeige(32,1);
        }else if (monat==2){
            tage = new Nummernanzeige(29,1);
        }else{
            tage = new Nummernanzeige(31,1);
        }
        setzeUhrzeit(tag, monat, jahr, stunde, minute);
    }
    
    

    /**
     * Diese Operation sollte einmal pro Minute aufgerufen werden -
     * sie sorgt daf?r, dass diese Uhrenanzeige um eine Minute
     * weiter gestellt wird.
     */
    public boolean taktsignalGeben()
    {
        boolean minutelimitreached=false;
        
        minuten.erhoehen();
        if(minuten.gibWert() == 0) {  // Minuten Limit wurde erreicht!
            minutelimitreached=true;
            stunden.erhoehen();
            if(stunden.gibWert() == 0) {  // Stunden Limit wurde erreicht!
                tage.erhoehen();
                if(tage.gibWert() == 1) {  // Tages Limit wurde erreicht!
                    monate.erhoehen();
                    if(monate.gibWert()==1 || monate.gibWert()==3 || monate.gibWert()==5 || monate.gibWert()==7 || 
                       monate.gibWert()==8 || monate.gibWert()==10|| monate.gibWert()==12){
                           tage.setzeLimit(32);
                    }else if (monate.gibWert()==2){
                      tage.setzeLimit(29);
                    }else{
                        tage.setzeLimit(31);
                    }                   
                    if(monate.gibWert() == 1) {  // Monat Limit wurde erreicht!
                        jahre.erhoehen();
                    }
                }
        
            }
        }
        anzeigeAktualisieren();
        return minutelimitreached;
    }

    /**
     * Setze die Uhrzeit dieser Anzeige auf die gegebene 'tag', 'monat', 'jahr', 'stunde' und
     * 'minute'.
     */
    public void setzeUhrzeit(int tag, int monat, int jahr, int stunde, int minute)
    {
        stunden.setzeWert(stunde);
        minuten.setzeWert(minute);
        monate.setzeWert(monat);
        
        if(monate.gibWert()==1 || monate.gibWert()==3 || monate.gibWert()==5 || monate.gibWert()==7 || 
           monate.gibWert()==8 || monate.gibWert()==10|| monate.gibWert()==12){
              tage.setzeLimit(32);
        }else if (monate.gibWert()==2){
              tage.setzeLimit(29);
        }else{
              tage.setzeLimit(31);
        }                   
        tage.setzeWert(tag);
        jahre.setzeWert(jahr);
        anzeigeAktualisieren();
    }

    /**
     * Liefere die aktuelle Uhrzeit dieser Uhrenanzeige im Format TT.MM.JJJJ SS:MM.
     */
    public String gibDeutscheUhrzeit()
    {
        return zeitanzeigeD;
    }
    
    /**
     * Liefere die aktuelle Uhrzeit dieser Uhrenanzeige im Britischen Format.
     */
    public String gibBritischeUhrzeit()
    {
        return zeitanzeigeB;
    }
    
    /**
     * Uhrensimulation f?r ein Jahr.
     */
    public void go()
    {
        int i;
        
        for(i=1;i<525600;i++){  
            taktsignalGeben();
        }
    }
    
     /**
     * Reset der Uhr
     */
    public void reset()
    {
        setzeUhrzeit(1,1,0,0,0);
    }
    
    /**
     * Aktualisiere die interne Zeichenkette, die die Zeitanzeige h?lt.
     */
    private void anzeigeAktualisieren()
    {
       erstelleDeutschesFormat();
       erstelleBritischesFormat();
       System.out.println("Datum: "+ gibDeutscheUhrzeit()+"   "+gibBritischeUhrzeit());

    }
    
    /**
     * Aktualisiere die interne Zeichenkette, die die Zeitanzeige h?lt.
     */
    private void erstelleDeutschesFormat()
    {
        zeitanzeigeD =   tage.gibAnzeigewert(2) + "."
                        + monate.gibAnzeigewert(2) + "."
                        + jahre.gibAnzeigewert(4) + " "
                        + stunden.gibAnzeigewert(2) + ":"
                        + minuten.gibAnzeigewert(2);
    }
    
    
    /**
    * Setzt Britisches Format
    */
   private void erstelleBritischesFormat()
   {
     int stunde = stunden.gibWert();
     String suffix = "am";
     if(stunde >= 12) {
           stunde = stunde - 12;
           suffix = "pm";
     }
     if(stunde == 0) {
         stunde = 12;
     }
     zeitanzeigeB = tage.gibAnzeigewert(2) + "."
                        + monate.gibAnzeigewert(2) + "."
                        + jahre.gibAnzeigewert(4) + " " + stunde + ":" + minuten.gibAnzeigewert(2) + suffix;
   }

    
    public static void main (String[] args) {
      
      Uhrenanzeige x=new Uhrenanzeige();
      x.taktsignalGeben();
      x.go();
  }

   
   
}
