package com.example.mvc;

/**
 * Created by amberlee on 16-07-04.
 */
public class Rectangle extends Shape {
    public Rectangle() {
        super();
    }
    public Rectangle(float x1, float x2, float y1, float y2, String color, int lineThickness) {
        super(x1,x2,y1,y2,color, lineThickness, 'r');
    }

    @Override
    public Boolean contains(float x, float y) {
        float maxX= Math.max(getX1(), getX2());
        float minX= Math.min(getX1(), getX2());
        float topY= Math.max(getY1(), getY2());
        float bottomY= Math.min(getY1(), getY2());
        if (x >= minX && x <= maxX && y <= topY && y >= bottomY) {
            return true;
        }
        return false;
    }

//    @Override
//    public void draw(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        if (this.filled) {
//            g2.setColor(this.fillColor);
//            g2.fillRect(getLeft(), getTop(), getWidth(), getHeight());
//        }
//        g2.setColor(getColor());
//        g2.setStroke(new BasicStroke(getLineThickness()));
//        g2.drawRect(getLeft(), getTop(), getWidth(), getHeight());
//    }
}
