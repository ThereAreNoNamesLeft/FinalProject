
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
    public static double level = 0;
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
        boxes.add(new Box(-1, 0, 802, 2));
        boxes.get(0).onSurface = true; 
        boxes.get(0).yVel = 0;
    }
    
    public void run()
    {
        level += 0.1;
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
            if(milliseconds % 50 < ComsciFinalProject.rate)
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
        for(int b1 = 0; b1 < boxes.size(); b1++)
        {
            Box box = boxes.get(b1);
            DoublePoint collision = Collisions.CollidesLineRect(new Line(p.getX(), p.getY(), p.getX() + p.getXVel(), p.getY() + p.getYVel()), box.x, box.y, box.x + box.width, box.y + box.height);
            if(collision != null)
            {
                p.setOnBox(box, collision);
            }
            //check for collisions between boxes and stop the higher box
            box.run();
            broken
            if(!box.onSurface)
            {
                for(int b2 = 0; b2 < boxes.size(); b2++)
                {
                    Box box2 = boxes.get(b2);
                    if(box2.onSurface && b2 != b1 && Collisions.Collides2d(box.x, box.y, box.x + box.width, box.y + box.height, box2.x, box2.y, box2.x + box2.width, box2.y + box2.height));
                    {
                        box.setOnSurface();
                    }
                }
            }
        }
        p.run();
        //fix view to players height if they are moving faster
        if(p.getY() < -200 - level)
        {
            level = -200 - p.getY();
        }
//        if(player below screen)
//        {
//            gameOver();
//        }
//        if(player collide bottom box && box is on ground)
//        {
//            gameOver();
//        }
    }
    
    public void gameOver()
    {
        milliseconds = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        level = 0;
        boxes.clear();
        p = new Player();
        startup();
    }
    
    public void addRandomBox()
    {
        int width = RNG.nextInt(75) + 25;
        Box box = new Box(RNG.nextInt(800 - width), -(int)level - 600, width, RNG.nextInt(75) + 25);
        boxes.add(0, box);
    }
    
    @Override 
    public void paintComponent(Graphics g)
    {
        Colours.hueShift();
        g.setColor(Colours.Black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Colours.getHueShift());
        //draw boxes
        for(Box box : boxes)
        {
            g.drawRect((int)box.x, (int)box.y + (int)level + 400, (int)box.width, (int)box.height);
        }
        //draw player
        g.drawRect((int)p.getX() - 10, (int)p.getY() - 10 + (int)level + 400, 20, 20);
        
        g.drawString("Time: " + hours + ':' + minutes + ':' + seconds + ':' + milliseconds, 10, 10);
        g.drawString("X: " + p.getX(), 10, 30);
        g.drawString("Y: " + p.getY(), 10, 50);
        g.drawString("Level: " + level, 10, 70);
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
            case 82:
            {
                gameOver();
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
