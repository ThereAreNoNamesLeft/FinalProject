
package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import main.Math.Collisions;
import main.Math.DoublePoint;
import main.Math.Line;
import main.Objects.Box;
import main.Objects.Player;

public class CustomPanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener
{
    Random RNG = new Random();
    boolean tempJumpLimiter = true;
    Keys keys = new Keys();
    Player p = new Player();
    ArrayList<Box> boxes = new ArrayList();
    short milliseconds = 0, seconds = 0, minutes = 0, hours = 0;
    
    //notes
    //boxes array goes from bottom boxes to top boxes
    public void startup()
    {
    }
    
    public void run()
    {
        milliseconds += ComsciFinalProject.rate;
        if(milliseconds >= 1000)
        {
            milliseconds = 0;
            seconds++;
            if(seconds >= 60)
            {
                seconds = 0;
                minutes++;
                if(minutes >= 60)
                {
                    minutes = 0;
                    hours++;
                    if(hours >= 24)
                    {
                        hours = 0;
                    }
                }
            }
            if(seconds % 2 == 0)
            {
                addRandomBox();
            }
        }
        if(Keys.B_up)
        {
            if(tempJumpLimiter)
            {
                p.up();
                tempJumpLimiter = false;
            }
        }
        else
        {
            tempJumpLimiter = true;
        }
        if(Keys.B_down)
        {
            p.down();
        }
        if(Keys.B_left && !Keys.B_right)
        {
            p.left();
        }
        else if(Keys.B_right && !Keys.B_left)
        {
            p.right();
        }
        else if(p.onGround)
        {
            p.frictionHorizontal();
        }
        for(Box box : boxes)
        {
            DoublePoint collision = Collisions.CollidesLineRect(new Line(p.getX(), p.getY(), p.getX() + p.getXVel(), p.getY() + p.getYVel()), box.x, box.y, box.x + box.width, box.y + box.height);
            if(collision != null)
            {
                p.setX(collision.x);
                p.setY(collision.y);
            }
            //check for collisions between boxes and stop the higher box
            box.run();
        }
        p.run();
    }
    
    public void addRandomBox()
    {
        int width = RNG.nextInt(100);
        Box box = new Box(RNG.nextInt(800 - width), 0, width, RNG.nextInt(100));
        boxes.add(0, box);
    }
    
    @Override 
    public void paintComponent(Graphics g)
    {
        Colours.hueShift();
        g.setColor(Colours.Black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Colours.getHueShift());
        for(Box box : boxes)
        {
            g.drawRect((int)box.x, (int)box.y, (int)box.width, (int)box.height);
        }
        g.drawRect((int)p.getX() - 10, (int)p.getY() - 10, 20, 20);
        g.drawLine(0, 400, 800, 400);
        g.drawString("Time: " + hours + ':' + minutes + ':' + seconds + ':' + milliseconds, 10, 10);
    }
    

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        switch(e.getKeyCode())
        {
            case 32:
            {
                if(!Keys.B_up)
                {
                    Keys.B_up = true;
                }
            }
            break;
            case 65:
            {
                if(!Keys.B_left)
                {
                    Keys.B_left = true;
                }
            }
            break;
            case 83:
            {
                if(!Keys.B_down)
                {
                    Keys.B_down = true;
                }
            }
            break;
            case 68:
            {
                if(!Keys.B_right)
                {
                    Keys.B_right = true;
                }
            }
            break;
            case 78:
            {
                addRandomBox();
            }
            break;
            default:
            {
                System.out.println("key " + e.getKeyCode() + " pressed");
            }
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        switch(e.getKeyCode())
        {
            case 32:
            {
                Keys.B_up = false;
            }
            break;
            case 65:
            {
                Keys.B_left = false;
            }
            break;
            case 83:
            {
                Keys.B_down = false;
            }
            break;
            case 68:
            {
                Keys.B_right = false;
            }
            break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
    {
        
    }
}
