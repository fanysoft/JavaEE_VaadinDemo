package cz.vancura.VaadinWorkflow.chart;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import cz.vancura.VaadinWorkflow.Login.SimpleLoginView;


@Theme("mytheme")
@Widgetset("cz.vancura.VaadinWorkflow.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
     

    	new Navigator(this, this);
    	
    	getNavigator().addView(SimpleLoginView.NAME, SimpleLoginView.class);
    	getNavigator().addView(ChartMainView.NAME, ChartMainView.class);

    	getNavigator().addViewChangeListener(new ViewChangeListener() {
    		
    		@Override
            public boolean beforeViewChange(ViewChangeEvent event) {
    		
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof SimpleLoginView;

                if (!isLoggedIn && !isLoginView) {
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    return false;
                }

                return true;
    			
    		}
    		
    		 @Override
             public void afterViewChange(ViewChangeEvent event) {
    		
    		
    		    }
        });
    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}