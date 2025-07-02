/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author denia
 */
public class ImagePanel extends JPanel{
    
    private BufferedImage image;
    private BufferedImage blurredImage;
    
    public ImagePanel(String imagine) throws IOException{
        image = ImageIO.read(new File(imagine));
        blurredImage = blurImage(image);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
      private BufferedImage blurImage(BufferedImage src) {
        float[] blurKernel = {
            1f / 9f, 1f / 9f, 1f / 9f,
            1f / 9f, 1f / 9f, 1f / 9f,
            1f / 9f, 1f / 9f, 1f / 9f
        };

        ConvolveOp op = new ConvolveOp(
            new Kernel(3, 3, blurKernel),
            ConvolveOp.EDGE_NO_OP,
            null
        );

        return op.filter(src, null);
    }
}
