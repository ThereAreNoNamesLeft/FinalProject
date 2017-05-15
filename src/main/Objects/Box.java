
package main.Objects;


public class Box 
{
    public double x = 0, y = 0, xVel = 0, yVel = 1, width = 1, height = 1;
    boolean onGround = false;
    public Box(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }
    public void run()
    {
        if(!onGround)
        {
            y += yVel;
        }
        if(y > 400 - height)
        {
            y = 400 - height;
            yVel = 0;
            onGround = true;
        }
        
    }
}