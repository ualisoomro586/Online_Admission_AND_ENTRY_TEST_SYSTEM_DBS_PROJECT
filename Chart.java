import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Chart {

    public static  void main(String[] args){
        String s= "2022-02-31";
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        date.setLenient(false);
        try {
            date.parse(s);
            System.out.println("Done");
        }
        catch (Exception e) {
            System.out.println("Not Done");
        }
        System.out.println(date);
    }
}
