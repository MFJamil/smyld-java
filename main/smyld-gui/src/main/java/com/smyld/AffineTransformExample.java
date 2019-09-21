package com.smyld;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AffineTransformExample extends JFrame {
 ShowLabel label;
  JComboBox shearX, shearY;
  
  String[] shear = { "0.00", "0.25", "0.50", "0.75", "1.00" };

  public AffineTransformExample() {
    super("Affine Transform Example");
    Container container = getContentPane();
    label = new ShowLabel();
    container.add(label);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 4, 5, 5));
    
    shearX = new JComboBox(shear);
    shearX.setSelectedItem("0.00");
    panel.add(new JLabel("Shear X:"));
    panel.add(shearX);
    shearX.addActionListener(new ComboBoxListener());
   
    shearY = new JComboBox(shear);
    shearY.setSelectedItem("0.00");
	panel.add(new JLabel("Shear Y:"));
    panel.add(shearY);
    shearY.addActionListener(new ComboBoxListener());
    container.add(BorderLayout.NORTH, panel);

    setSize(350,300);
    setVisible(true);
  }
    public static void main(String arg[]) {
    new AffineTransformExample();
  }
    class ComboBoxListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JComboBox box = (JComboBox) e.getSource();
        if (box == shearX) {
        label.shearx = Double.parseDouble((String) box.getSelectedItem());
        label.value(true);
        label.filter();
        label.repaint();
      } else if (box == shearY) {
        label.sheary = Double.parseDouble((String) box.getSelectedItem());
        label.value(true);
        label.filter();
        label.repaint();
      }
    }
  }
}
class ShowLabel extends JLabel {
  Image image;
  BufferedImage bufferedImage1, bufferedImage2;
  BufferedImage bufferedImage;
  Graphics2D g2d;
  AffineTransform affineTransform;
  double shearx = 1.0, sheary = 1.0;

  ShowLabel() {
    image = Toolkit.getDefaultToolkit().getImage("image4.jpg");
    MediaTracker mediaTracker = new MediaTracker(this);
    mediaTracker.addImage(image, 1);
    try {
      mediaTracker.waitForAll();
    } catch (Exception e) {}
    createImages();
    affineTransform = new AffineTransform();
  }
    public void createImages() {
    bufferedImage1 = new BufferedImage(image.getWidth(this), image
        .getHeight(this), BufferedImage.TYPE_INT_RGB);
    g2d = bufferedImage1.createGraphics();
    g2d.drawImage(image, 0, 0, this);
    bufferedImage = bufferedImage1;
    bufferedImage2 = new BufferedImage(image.getWidth(this), image
        .getHeight(this), BufferedImage.TYPE_INT_RGB);
  }
    public void value(boolean shear) {
    if (shear) {
      affineTransform.setToShear(shearx, sheary);
      shear = true;
    }
  }
    public void filter() {
    AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, null);
    Graphics2D G2D = bufferedImage2.createGraphics();
    G2D.clearRect(0, 0, bufferedImage2.getWidth(this), bufferedImage2.getHeight(this));
    affineTransformOp.filter(bufferedImage1, bufferedImage2);
    bufferedImage = bufferedImage2;
  }
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(bufferedImage, 0, 0, this);
  }
}

































