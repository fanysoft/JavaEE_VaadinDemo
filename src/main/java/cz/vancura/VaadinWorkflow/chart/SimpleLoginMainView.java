package cz.vancura.VaadinWorkflow.chart;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.MarkerSymbolEnum;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.SubTitle;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class SimpleLoginMainView extends CustomComponent implements View {
	
	public static final String NAME = "";
	Chart chart = new Chart(ChartType.AREA);
    Configuration conf = chart.getConfiguration();
    Label text = new Label();


    public  SimpleLoginMainView() {
    
    	setCompositionRoot(new CssLayout(chart));
    	setWidth("100.0%");
        setHeight("100.0%");
        
        conf.setTitle(new Title("Spotřeba energie a teplota"));
        conf.setSubTitle(new SubTitle( "Klatovská třída 75"));

        PlotOptionsArea plotOptions = new PlotOptionsArea();

        Marker marker = new Marker();
        marker.setEnabled(false);
        marker.setSymbol(MarkerSymbolEnum.CIRCLE);
        marker.setRadius(2);
        
        plotOptions.setMarker(marker);
        conf.setPlotOptions(plotOptions);
    	
        Labels labels = new Labels();
        
        XAxis xAxis = new XAxis();
        xAxis.setTitle("Date");;
        xAxis.setType(AxisType.DATETIME);
        conf.addxAxis(xAxis);
 
        YAxis yAxis = new YAxis();
        yAxis.setTitle("Wh / stC");
        labels = new Labels();
        yAxis.setLabels(labels);
        conf.addyAxis(yAxis);
         
        
        ChartsData data = new ChartsData();
    
        // data - serie 1
    	DataSeries spotrebaEle = new DataSeries("Elekřina");
    	for(Spotreba spotreba : data.getSpotrebaPole()) {
    		spotrebaEle.add(new DataSeriesItem(
    	                   spotreba.timestamp,
    	                   spotreba.ele));
    	}
    	conf.addSeries(spotrebaEle);
    	
    	
    	// data - serie 2
    	DataSeries spotrebaPlyn = new DataSeries("Plyn");
    	for(Spotreba spotreba : data.getSpotrebaPole()) {
    		spotrebaPlyn.add(new DataSeriesItem(
    	                   spotreba.timestamp,
    	                   spotreba.plyn));
    	}
    	conf.addSeries(spotrebaPlyn);    
    	
    	// data - serie 3
    	DataSeries spotrebaVoda = new DataSeries("Voda");
    	for(Spotreba spotreba : data.getSpotrebaPole()) {
    		spotrebaVoda.add(new DataSeriesItem(
    	                   spotreba.timestamp,
    	                   spotreba.voda));
    	}
    	conf.addSeries(spotrebaVoda);  
    	
    	
    	// data - serie 4
    	DataSeries teplota = new DataSeries("Teplota");
    	for(Spotreba spotreba : data.getSpotrebaPole()) {
    		teplota.add(new DataSeriesItem(
    	                   spotreba.timestamp,
    	                   spotreba.temp));
    	}
    	conf.addSeries(teplota);  
    	
           
    	
    }

    @Override
    public void enter(ViewChangeEvent event) {

        String username = String.valueOf(getSession().getAttribute("user"));
      //  text.setValue("Hello user=" + username);
        
    }
}
