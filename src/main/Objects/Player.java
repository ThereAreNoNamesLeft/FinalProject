
package main.Objects;

import main.Keys;

public class Player 
{
    double x = 0, y = 0;
    double xVel = 0, yVel = 0;
    double friction = 0.9, gravity = 0.9;
    double speed = 4, jumpSpeed = 17, fallSpeed = 30;
    public boolean onSurface = false;
    int jumpsLeft = 1, jumpsMax = 6000000;
    public void run()
    {
        if(y > 400)
        {
            y = 400;
            setOnGround();
        }
        //on surface checks must be done before this point
        x += xVel;
        y += yVel;
        if(onSurface && !(Keys.B_left || Keys.B_right))
        {
            frictionHorizontal();
        }
        
        //temp
        if(!onSurface)
        {
            yVel += gravity;
            if(yVel > fallSpeed)
            {
                yVel = fallSpeed;
            }
        }
        onSurface = false;
        
    }
    public void setOnGround()
    {
            yVel = 0;
            onSurface = true;
            jumpsLeft = jumpsMax;
    }
    public void setOnBox(Box b, char collType)
    {
        switch(collType)
        {
            case 't':
            {
                onSurface = true;
                y = b.y - 1;
                yVel = Math.min(b.yVel, yVel);
                jumpsLeft = jumpsMax;
            }
            break;
            case 'b':
            {
                yVel = b.yVel;
            }
            break;
            case 's':
            {
                xVel = -xVel / 100;
            }
            break;
            default:
            {
                System.out.println(collType + " is not a valid collision type");
            }
        }
    }
    public void frictionHorizontal()
    {
        xVel *= friction;
    }
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public double getXVel()
    {
        return xVel;
    }
    public double getYVel()
    {
        return yVel;
    }
    public void stop()
    {
        xVel = 0;
        yVel = 0;
    }
    public void left()
    {
        if(onSurface)
        {
            pureLeft(0.5);
        }
        else
        {
            pureLeft(0.05);
        }
    }
    public void right()
    {
        if(onSurface)
        {
            pureRight(0.5);
        }
        else
        {
            pureRight(0.05);
        }
    }
    public void up()
    {
        if(onSurface)
        {
            yVel = -jumpSpeed;
            onSurface = false;
        }
        else if(jumpsLeft > 0)
        {
            yVel = -jumpSpeed;
            
        if(Keys.B_left && !Keys.B_right)
        {
            pureLeft(1);
        }
        else if(Keys.B_right && !Keys.B_left)
        {
            pureRight(1);
        }
            jumpsLeft--;
        }
    }
    public void down()
    {
        yVel += (fallSpeed - yVel) * 0.4;
    }
    private void pureLeft(double percent)
    {
        xVel += (-speed - xVel) * percent;
    }
    private void pureRight(double percent)
    {
        xVel += (speed - xVel) * percent;
    }
    private void pureUp(double percent)
    {
        
    }
    private void pureDown(double percent)
    {
    }
}
