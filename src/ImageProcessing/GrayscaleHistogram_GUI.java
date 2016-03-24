/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import org.jfree.chart.JFreeChart;
/**
 *
 * @author Ryan Fadholi
 */
public class GrayscaleHistogram_GUI extends javax.swing.JFrame {

    /**
     * Creates new form ShowGrayscaleHistogram_GUI
     */
    public GrayscaleHistogram_GUI(){
        this(null);
    }
    public GrayscaleHistogram_GUI(Component callerComponent) {
        //callerComponent is used only to determine the position of this
        //frame when called.
        
        initComponents();
        this.setLocationRelativeTo(callerComponent);
        
        //Determine chart width & height in display.
        int chartLabelHeight = sourceImageHistogram.getHeight();
        int chartLabelWidth = sourceImageHistogram.getWidth();
       
        //Get Grayscale value for every pixels from source & processed image.
        double[] sourceGrayValues = ImageProcessing.extractGrayColor(Main.getSourceImage());
        double[] processedGrayValues = ImageProcessing.extractGrayColor(Main.getProcessedImage());
       
        //Create two charts using the grayscale values.
        JFreeChart sourceChart = ImageProcessing.createHistogram(sourceGrayValues, Color.BLACK);
        JFreeChart processedChart = ImageProcessing.createHistogram(processedGrayValues,Color.BLACK);
        
        //Convert the charts to a BufferedImage for displaying.
        BufferedImage sourceChartImage = sourceChart.createBufferedImage(chartLabelWidth, chartLabelHeight);
        BufferedImage processedChartImage = processedChart.createBufferedImage(chartLabelWidth, chartLabelHeight);
        
        //Display the chart images.
        sourceImageHistogram.setIcon(new ImageIcon(sourceChartImage));
        processedImageHistogram.setIcon(new ImageIcon(processedChartImage));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sourceImageHistogram = new javax.swing.JLabel();
        processedImageHistogram = new javax.swing.JLabel();
        sourceLabel = new javax.swing.JLabel();
        processedLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grayscale Histogram");

        sourceLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sourceLabel.setText("Source Image Histogram");

        processedLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        processedLabel.setText("Processed Image Histogram");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(processedLabel)
                    .addComponent(sourceLabel)
                    .addComponent(sourceImageHistogram, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(processedImageHistogram, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sourceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourceImageHistogram, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(processedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(processedImageHistogram, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel processedImageHistogram;
    private javax.swing.JLabel processedLabel;
    private javax.swing.JLabel sourceImageHistogram;
    private javax.swing.JLabel sourceLabel;
    // End of variables declaration//GEN-END:variables
}
