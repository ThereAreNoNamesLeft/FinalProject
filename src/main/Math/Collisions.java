

package main.Math;

import java.awt.Point;

public class Collisions 
{
    //THESE ONLY WORK WITH SOUTHEAST RECTANGLES
    //point
    public static boolean Collides2dPoint(int ax,int ay,int bx1,int by1,int bx2,int by2)
    {
        return (ax < bx2 && ax > bx1 && ay < by2 && ay > by1);
    }
    public static boolean Collides2d(int ax1,int ay1,int ax2,int ay2,int bx1,int by1,int bx2,int by2)
    {
        return ((ax2 > bx1 && ax1 < bx2)&&(ay2 > by1 && ay1 < by2));
    }
    public static boolean Collides3d(int ax1,int ay1,int az1,int ax2,int ay2,int az2,int bx1,int by1,int bz1,int bx2,int by2,int bz2)
    {
        return ((ax2 > bx1 && ax1 < bx2)&&(ay2 > by1 && ay1 < by2)&&(az2 > bz1 && az1 < bz2));
    }
    public static boolean Collides2dPoint(double ax,double ay,double bx1,double by1,double bx2,double by2)
    {
        return (ax < bx2 && ax > bx1 && ay < by2 && ay > by1);
    }
    public static boolean Collides2d(double ax1,double ay1,double ax2,double ay2,double bx1,double by1,double bx2,double by2)
    {
        return ((ax2 > bx1 && ax1 < bx2)&&(ay2 > by1 && ay1 < by2));
    }
    public static boolean Collides3d(double ax1,double ay1,double az1,double ax2,double ay2,double az2,double bx1,double by1,double bz1,double bx2,double by2,double bz2)
    {
        return ((ax2 > bx1 && ax1 < bx2)&&(ay2 > by1 && ay1 < by2)&&(az2 > bz1 && az1 < bz2));
    }
    public static DoublePoint CollidesLineLine(Line a,Line b)
    {
        //what x value gives equal y values
        //CAN RETURN NULL
        //same xVal divides by 0
        //test cases
        // colinear
        // perpendicular cardinal
        // extension of line collides vertical
        // extension of line collides horizontal
        // parallel
        //test if point is on both line a and line b
        double nnum = ((b.x2 - b.x1) * (a.y1 - b.y1) - (b.y2 - b.y1) * (a.x1 - b.x1));
        double ndenom = ((b.y2 - b.y1) * (a.x2 - a.x1) - (b.x2 - b.x1) * (a.y2 - a.y1));
        double n = nnum / ndenom;
        double m = (a.x1 - b.x1 + n * (a.x2 - a.x1)) / (b.x2 - b.x1);
        if(n <= 1 && n >= 0 && m <= 1 && m >= 0)
        {
            DoublePoint p = new DoublePoint();
            p.x = a.x1 + n * (a.x2 - a.x1);
            p.y = a.y1 + n * (a.y2 - a.y1);
            return p;
        }
        return null;
    }
    public static DoublePoint CollidesLineRect(Line a,double bx1,double by1,double bx2,double by2)
    {
        //return closest to a.x1
        //p is the least x difference (math.min)
        DoublePoint p;
        DoublePoint c1 = CollidesLineLine(a, new Line(bx1, by1, bx2, by1));
        DoublePoint c2 = CollidesLineLine(a, new Line(bx1, by2, bx2, by2));
        
        //excnahnged x and y values so that slope is 0 instead of undef
        Line aflip = new Line(a.y1, a.x1, a.y2, a.x2);
        DoublePoint holder = CollidesLineLine(aflip, new Line(by1, bx1, by2, bx1));
        DoublePoint c3 = null;
        if(holder != null)
        {
            c3 = new DoublePoint();
            c3.x = holder.y;
            c3.y = holder.x;
        }
        holder = CollidesLineLine(aflip, new Line(by1, bx2, by2, bx2));
        DoublePoint c4 = null;
        if(holder != null)
        {
            c4 = new DoublePoint();
            c4.x = holder.y;
            c4.y = holder.x;
        }
        
        
        p = c1;
        if(p != null)
        {
            p.surfaceType = 't';
        }
        if(p == null || (c2 != null && Math.abs(a.x1 - c2.x) < Math.abs(a.x1 - p.x)))
        {
            p = c2;
            p.surfaceType = 'b';
        }
        if(p == null || (c3 != null && Math.abs(a.x1 - c3.x) < Math.abs(a.x1 - p.x)))
        {
            p = c3;
            p.surfaceType = 's';
        }
        if(p == null || (c4 != null && Math.abs(a.x1 - c4.x) < Math.abs(a.x1 - p.x)))
        {
            p = c4;
            p.surfaceType = 's';
        }
        if(p != null)
        {
            return p;
        }
        //collide lines diagonals of rect, and points of line with entire
        return null;
    }
    
}
