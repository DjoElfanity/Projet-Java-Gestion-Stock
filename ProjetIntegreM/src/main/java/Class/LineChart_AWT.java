/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author Dell
 */
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.DefaultCategoryDataset;


public class LineChart_AWT extends ApplicationFrame {
    
  

   public LineChart_AWT( String applicationTitle , String chartTitle  , String date1,String dat2 ,int taill_max) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Years","Number of Schools",
         createDataset(date1,dat2,taill_max),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 600 ) );
      setContentPane( chartPanel );
   }


   
   public static void main( String[ ] args ) {
      LineChart_AWT chart = new LineChart_AWT(
         "School Vs Years" ,
         "Numer of Schools vs years" , "1900","1980",200);

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible(true );
   }
   
   
  public  void remplir_graphe(String applicationTitle , String chartTitle  , String date1,String dat2 ,int taill_max)
    {
         JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Years","Number of Schools",
         createDataset(date1,dat2,taill_max),
         PlotOrientation.VERTICAL,
         true,true,false);
          ChartPanel chartPanel = new ChartPanel( lineChart );
         chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 600 ) );
         setContentPane( chartPanel );
        
        
        
        
    }
  
     public static DefaultCategoryDataset createDataset(String date1,String dat2 ,int taill_max) {
      
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 0 , "nigg" , " "+date1 );
 
      dataset.addValue( taill_max , "nigg" , ""+dat2 );
      return dataset;
   }
}
