

package main.Math;

import java.awt.Point;
import main.Math.DoublePoint;

public interface Collisions 
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
        double slA = (a.y2 - a.y1) / (a.x2 - a.x1);
        double slB = (b.y2 - b.y1) / (b.x2 - b.x1);
        double xA1, xB1, xA2, xB2, yA, yB;
        if(a.x1 <= a.x2)
        {
            xA1 = a.x1;
            xA2 = a.x2;
            yA = a.y1;
        }
        else
        {
            xA1 = a.x2;
            xA2 = a.x1;
            yA = a.y2;
        }
        if(b.x1 <= b.x2)
        {
            xB1 = b.x1;
            xB2 = b.x2;
            yB = b.y1;
        }
        else
        {
            xB1 = b.x2;
            xB2 = b.x1;
            yB = b.y2;
        }
        double baseA = yA - slA * xA1;
        double baseB = yB - slB * xB1;
        double intersect = (baseA - baseB) / (slB - slA);
        if(a.x1 == a.x2)
        {
            intersect = a.x1;
        }
        if(b.x1 == b.x2)
        {
            intersect = b.x1;
        }
        if(intersect >= Math.max(xA1, xB1) && intersect <= Math.min(xA2, xB2))
        {
            DoublePoint p = new DoublePoint();
            p.x = intersect;
            p.y = intersect * slA + baseA;
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
        DoublePoint c3 = CollidesLineLine(a, new Line(bx1, by1, bx1, by2));
        DoublePoint c4 = CollidesLineLine(a, new Line(bx2, by1, bx2, by2));
        p = c1;
        if(p != null && c2 != null && p.x - a.x1 > c2.x - a.x1)
        {
            p = c2;
        }
        if(p != null && c3 != null && p.x - a.x1 > c3.x - a.x1)
        {
            p = c3;
        }
        if(p != null && c4 != null && p.x - a.x1 > c4.x - a.x1)
        {
            p = c4;
        }
        if(p != null)
        {
            return p;
        }
        //collide lines diagonals of rect, and points of line with entire
        return null;
    }
}
