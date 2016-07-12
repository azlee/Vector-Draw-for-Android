package com.example.mvc;

/**
 * Created by amberlee on 16-07-04.
 */
public class Circle extends Shape
{
    public Circle() {
        super();
    }

    public Circle(float x1, float x2, float y1, float y2, String color, int lineThickness) {
        super(x1, x2, y1, y2, color, lineThickness, 'c');
    }

    @Override
    public Boolean contains(float x, float y) {
        float centerX = getLeft();// + getWidth()/2;
        float centerY = getTop();// + getWidth()/2;
        float radius = getWidth()/2;
        if (((x - centerX)*(x - centerX) + (y - centerY)*(y - centerY)) < (radius * radius)) {
            return true;
        }
        return false;
    }

//    @Override
//    public void draw(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        if (this.filled) {
//            g2.setColor(this.fillColor);
//            g2.fillOval(getLeft(), getTop(), getWidth(), getWidth());
//        }
//        g2.setColor(getColor());
//        g2.setStroke(new BasicStroke(getLineThickness()));
//
//        g.drawOval(getLeft(), getTop(), getWidth(), getWidth());
//    }
}
