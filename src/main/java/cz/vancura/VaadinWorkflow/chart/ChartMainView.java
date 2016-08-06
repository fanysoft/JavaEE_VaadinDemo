package cz.vancura.VaadinWorkflow.chart;


import java.util.Date;

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
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ChartMainView extends CustomComponent implements View {
	
	public static final String NAME = "";
	
	Chart chart = new Chart(ChartType.AREA);
	Chart chart2 = new Chart(ChartType.AREA);
    Configuration conf = chart.getConfiguration();
    Configuration conf2 = chart2.getConfiguration();
    Label text = new Label();


    public ChartMainView() {
    	
    	// vloz na celou stranu 
    	setCompositionRoot(new CssLayout(chart, chart2));
    	setWidth("100.0%");
        setHeight("100.0%");
        
        conf.setTitle(new Title("Spotřeba energie"));
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
        yAxis.setTitle("Wh / Litry");
        labels = new Labels();
        yAxis.setLabels(labels);
        conf.addyAxis(yAxis);
         
        
        ChartData data = new ChartData();
    
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
    	
    
    	
    	
    	// Chraf2
    	
    	 conf2.setTitle(new Title("Teploty"));
         conf2.setSubTitle(new SubTitle( "Klatovská třída 75"));

         PlotOptionsArea plotOptions2 = new PlotOptionsArea();

         Marker marker2 = new Marker();
         marker2.setEnabled(false);
         marker2.setSymbol(MarkerSymbolEnum.CIRCLE);
         marker2.setRadius(2);
         
         plotOptions2.setMarker(marker2);
         conf2.setPlotOptions(plotOptions2);
     	
         Labels labels2 = new Labels();
         
         XAxis xAxis2 = new XAxis();
         xAxis2.setTitle("Date");;
         xAxis2.setType(AxisType.DATETIME);
         conf2.addxAxis(xAxis2);
  
         YAxis yAxis2 = new YAxis();
         yAxis2.setTitle("stC");
         labels2 = new Labels();
         yAxis2.setLabels(labels2);
         conf2.addyAxis(yAxis2);
          
         
    	
    	// data - serie 4
    	DataSeries teplota = new DataSeries("Teplota OUT");
    	for(Spotreba spotreba : data.getSpotrebaPole()) {
    		teplota.add(new DataSeriesItem(
    	                   spotreba.timestamp,
    	                   spotreba.temp1));
    	}
    	conf2.addSeries(teplota);  
    	
    	// data - serie 5
    	DataSeries teplota2 = new DataSeries("Teplota IN");
    	for(Spotreba spotreba : data.getSpotrebaPole()) {
    		teplota2.add(new DataSeriesItem(
    	                   spotreba.timestamp,
    	                   spotreba.temp2));
    	}
    	conf2.addSeries(teplota2);  
    	
	
	    
    	
    }

   @Override
    public void enter(ViewChangeEvent event) {

        String username = String.valueOf(getSession().getAttribute("user"));
      //  text.setValue("Hello user=" + username);
        
    }
}
