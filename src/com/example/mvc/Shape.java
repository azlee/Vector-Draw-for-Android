package com.example.mvc;

/**
 * Created by amberlee on 16-07-04.
 */
public abstract class Shape {
    private float x1, x2, y1, y2;
    private String borderColor;
    public String fillColor;
    public Boolean filled = false; // true if filled, false else
    private int lineThickness;
    private char type; // r for rect, c for circle, l for line

    public Shape() {
        x1 = x2 = y1 = y2 = 0;
        borderColor = "ed1c24";
        filled = false;
        lineThickness = 1;
    }

    public Shape(float x1, float x2, float y1, float y2, String color, int lineThickness, char t) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.filled = false;
        this.borderColor = color;
        this.lineThickness = lineThickness;
        this.type = t;
    }

    // setters
    public void setColor(String color) {
        this.borderColor = color;
    }
    public void setX1(float x1) {
        this.x1 = x1;
    }
    public void setX2(float x2) {
        this.x2 = x2;
    }
    public void setY1(float y1) {
        this.y1 = y1;
    }
    public void setY2(float y2) {
        this.y2 = y2;
    }
    public void setLineThickness(int x) {
        lineThickness = x;
    }
    public void setFilled(boolean f) { filled = f; }
    public void setFillColor(String c) { fillColor = c; };
    // getters
    public char getType() { return type; }
    public float getX1() {
        return x1;
    }
    public float getX2() {
        return x2;
    }
    public float getY1() {
        return y1;
    }
    public float getY2() {
        return y2;
    }
    public float getLeft() {
        return Math.min(x1, x2);
    }
    public float getRight() {
        return Math.max(x1, x2);
    }
    public float getTop() {
        return Math.min(y1, y2);
    }
    public float getBottom() {
        return Math.max(y1, y2);
    }
    public float getHeight() {
        return Math.abs(y1 - y2);
    }
    public float getWidth() {
        return Math.abs(x1 - x2);
    }
    public float getLineThickness() {
        return lineThickness;
    }
    public String getColor() {
        return borderColor;
    }
    public Boolean getFilled() { return filled; }
    public String getFillColor() { return fillColor; }
    // implement draw methods in the classes derived from Shape
    // ex. Rectangle, line, circle
    abstract public Boolean contains(float x, float y);
}