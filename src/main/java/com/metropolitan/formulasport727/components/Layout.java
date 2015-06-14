package com.metropolitan.formulasport727.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

/**
 * Layout component for pages of application test-project.
 * @author Miroslav Stipanovic 727
 */
//@Import(module="bootstrap/collapse")
@Import(stylesheet="context:css/layout.css")
public class Layout
{
  @Inject
  private ComponentResources resources;

  /**
   * The page title, for the <title> element and the <h1> element.
   */
  @Property
  @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
  private String title;

  @Property
  private String pageName;

  @Property
  @Inject
  @Symbol(SymbolConstants.APPLICATION_VERSION)
  private String appVersion;

  public String getClassForPageName()
  {
    return resources.getPageName().equalsIgnoreCase(pageName)
        ? "active"
        : null;
  }

  public String[] getPageNames()
	{
		return new String[]{"Index", "StatistikaSampionata",
                    "WebProdavnica", "DiskusioneGrupe", "AdminPanel"};
	}

        public String prepare(String pageName) 
        {
            if(pageName.equals("Index")){
                return pageName.replace("Index", "Vesti"); 
            }
            else if(pageName.equals("StatistikaSampionata")){
                return pageName.replace("aS", "a \u0160"); 
            }
            else if(pageName.equals("WebProdavnica")){
                return pageName.replace("bP", "b P"); 
            }
            else if(pageName.equals("DiskusioneGrupe")){
                return pageName.replace("eG", "e G"); 
            } else
                return pageName;
        }

}
