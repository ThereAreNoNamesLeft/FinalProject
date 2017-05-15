
package main.Objects;

import main.Keys;

public class Player 
{
    double x = 0, y = 0;
    double xVel = 0, yVel = 0;
    double friction = 0.9, gravity = 0.9;
    double speed = 4, jumpSpeed = 17, fallSpeed = 30;
    public boolean onGround = false;
    int jumpsLeft = 1, jumpsMax = 6000000;
    public void run()
    {
        x += xVel;
        y += yVel;
        if(!onGround)
        {
            yVel += gravity;
            if(yVel > fallSpeed)
            {
                yVel = fallSpeed;
            }
        }
        if(y > 400)
        {
            y = 400;
            yVel = 0;
            onGround = true;
            jumpsLeft = jumpsMax;
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
    public void left()
    {
        if(onGround)
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
        if(onGround)
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
        if(onGround)
        {
            yVel = -jumpSpeed;
            onGround = false;
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
