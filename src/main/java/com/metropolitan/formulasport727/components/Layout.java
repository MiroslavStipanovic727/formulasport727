package com.metropolitan.formulasport727.components;

import com.metropolitan.formulasport727.data.Jezici;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.pages.Index;
import java.util.Locale;
import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.services.PersistentLocale;

/**
 * Layout componenta aplikacije koja se koristi na svim stranama.
 * @author Miroslav Stipanovic 727
 */
@Import(stylesheet="context:css/layout.css")
public class Layout
{
  @Inject
  private ComponentResources resources;

  @Property
  @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
  private String title;

  @Property
  private String pageName;

  @Property
  @Inject
  @Symbol(SymbolConstants.APPLICATION_VERSION)
  private String appVersion;
  
  @Property
  @SessionState
  private Korisnik ulogovaniKorisnik;
  
  @Property
  @Persist
  private Jezici selektJezik;
  
  @Inject
  private PersistentLocale persistentLocale;

  /**
   * metoda vraća String active ako je klijent na tml stranici klase navedene 
   * u pageName atributu, ili null ako nije
   * @return 
   */
  public String getClassForPageName()
  {
    return resources.getPageName().equalsIgnoreCase(pageName)
        ? "active"
        : null;
  }

    /**
     * Metoda vraća imena glavnih stranica za navigaciju
     * @return 
     */
    public String[] getPageNames()
    {
        return new String[]{"Index", "StatistikaSampionata",
            "WebProdavnica", "DiskusioneGrupe"};
    }

    /**
     * metoda vraća lokalizovana imena stranica za prikaz u navigacionom meniju
     * @param pageName
     * @return 
     */
    public String prepare(String pageName) 
    {
        if(!persistentLocale.isSet()||persistentLocale.get().getLanguage().equals("sr")){
            if(pageName.equals("Index")){
                return pageName.replace("Index", "Vesti"); 
            }
            else if(pageName.equals("StatistikaSampionata")){
                return pageName.replace("Sampionata", "");
            }
            else if(pageName.equals("WebProdavnica")){
                return pageName.replace("Web", "");
            }
            else if(pageName.equals("DiskusioneGrupe")){
                return pageName.replace("oneGrupe", "je");
            } else
                return pageName;
        } else {
            if(pageName.equals("Index")){
                return pageName.replace("Index", "News"); 
            }
            else if(pageName.equals("StatistikaSampionata")){
                return pageName.replace("StatistikaSampionata", "Statistics");
            }
            else if(pageName.equals("WebProdavnica")){
                return pageName.replace("Prodavnica", "Shop");
            }
            else if(pageName.equals("DiskusioneGrupe")){
                return pageName.replace("kusioneGrupe", "cussions");
            } else
                return pageName;
        }

    }
    
    /**
     * metoda vraća true ako je korisnik ulogovan na sajt, false ako nije
     * @return 
     */
    public boolean getUlogovan(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getEmail()!=null;
    }

    /**
     * Metoda za odjavu korisnika sa sajta, postavlja vrednost ulogovanog korisnika
     * na null i vraća klijenta na početnu stranu sajta
     * @return 
     */
    public Object onLogout(){
        ulogovaniKorisnik=null;
        return Index.class;
    }

    /**
     * metoda koja na osnovu selektovanog jezika menja jezik aplikacije na srpski
     * ili engleski
     * @return 
     */
    public Object onSuccessFromPromenaJezika(){
        if(selektJezik==null){
            persistentLocale.set(new Locale("sr"));
        }  else if(selektJezik.equals(Jezici.srb)){
            persistentLocale.set(new Locale("sr"));
        } else if(selektJezik.equals(Jezici.eng)){
            persistentLocale.set(new Locale("en"));
        }
        return this;
    }

}
