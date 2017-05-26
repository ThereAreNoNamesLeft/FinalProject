
package main;

import java.io.IOException;
import javax.swing.JFrame;

public class ComsciFinalProject 
{
    public static int rate = 16;
    public static void main(String[] args)  throws IOException, InterruptedException
    {
        boolean killswitch = false;
        int windowWidth, windowHeight;
        JFrame gameWindow = new JFrame();
        gameWindow.setTitle("block jumperino");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        CustomPanel panel = new CustomPanel();
        Thread.sleep(2);
        
        gameWindow.setSize(806, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
        Thread.sleep(2);
        
        panel.setSize(800, 600);
        panel.repaint();
        gameWindow.setContentPane(panel);
        panel.requestFocus();
        Thread.sleep(2);
        panel.addKeyListener(panel);
        Thread.sleep(1);
        panel.addMouseMotionListener(panel);
        Thread.sleep(1);
        panel.addMouseListener(panel);
        Thread.sleep(1);
        panel.addMouseWheelListener(panel);
        Thread.sleep(2);
        
        
        windowWidth = gameWindow.getWidth();
        windowHeight = gameWindow.getHeight();
        panel.startup();
        Thread.sleep(2);
        
        while(!killswitch)
        {
            windowWidth = gameWindow.getWidth();
            windowHeight = gameWindow.getHeight();
            panel.setSize(windowWidth, windowHeight);
            panel.run();
            Thread.sleep(rate);
            panel.repaint();
        }
    }
    
}
