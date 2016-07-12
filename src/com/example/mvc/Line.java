package com.example.mvc;

import android.util.Log;

/**
 * Created by amberlee on 16-07-04.
 */
public class Line extends Shape {
    public Line() {
        super();
    }

    public Line(float x1, float x2, float y1, float y2, String color, int lineThickness) {
        super(x1,x2,y1,y2,color,lineThickness, 'l');
    }

    // return distance of px,py to the line that intersects points (x1, y1) and (x2, y2)
    // copied from java.awt.geom.Line2D.java from native libraries in Java
    public static double ptSegDist(float x1, float y1, float x2, float y2, float px,
                                   float py) {
        /*
         * A = (x2 - x1, y2 - y1) P = (px - x1, py - y1)
         */
        x2 -= x1; // A = (x2, y2)
        y2 -= y1;
        px -= x1; // P = (px, py)
        py -= y1;
        float dist;
        if (px * x2 + py * y2 <= 0.0) { // P*A
            dist = px * px + py * py;
        } else {
            px = x2 - px; // P = A - P = (x2 - px, y2 - py)
            py = y2 - py;
            if (px * x2 + py * y2 <= 0.0) { // P*A
                dist = px * px + py * py;
            } else {
                dist = px * y2 - py * x2;
                dist = dist * dist / (x2 * x2 + y2 * y2); // pxA/|A|
            }
        }
        if (dist < 0) {
            dist = 0;
        }
        return Math.sqrt(dist);
    }

    @Override
    public Boolean contains(float x, float y) {
        double dist = ptSegDist(getX1(), getY1(), getX2(), getY2(), x, y);
        if (dist <= (getLineThickness() * 3)) {
            return true;
        }
        return false;
    }

//    @Override
//    public void draw(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(getColor());
//        g2.setStroke(new BasicStroke(getLineThickness()));
//        g2.drawLine(getX1(), getY1(), getX2(), getY2());
//    }
}
