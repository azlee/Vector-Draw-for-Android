package com.example.mvc;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.*;
import android.view.View;
import android.widget.LinearLayout;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import android.graphics.*;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

public class View2 extends LinearLayout implements Observer {
	
	private Model model;
    private static List<Shape> shapes = new ArrayList<Shape>();
	private View textview;
    private Shape currentShape;
    private Canvas myCanvas;
    private float offsetX, offsetY, offsetX2, offsetY2;
    private float width, height;
    private float screenWidth, screenHeight;
    private int ptrCount = 0;
    private Boolean holdClearCanvas = false;
    private Boolean scale = false;
    private double prevPinchDistance = 0;

    public List<Shape> getShapes() {
        return shapes;
    }
    static public void setShapes(List<Shape> s) {
        shapes = s;
    }

//    private boolean isPinchGesture(MotionEvent event) {
//        if (event.getPointerCount() == 2) {
//            final float distanceCurrent = distance(event, 0, 1);
//            final float diffPrimX = mPrimStartTouchEventX - event.getX(0);
//            final float diffPrimY = mPrimStartTouchEventY - event.getY(0);
//            final float diffSecX = mSecStartTouchEventX - event.getX(1);
//            final float diffSecY = mSecStartTouchEventY - event.getY(1);
//
//            if (// if the distance between the two fingers has increased past
//                // our threshold
//                    Math.abs(distanceCurrent - mPrimSecStartTouchDistance) > mViewScaledTouchSlop
//                            // and the fingers are moving in opposing directions
//                            && (diffPrimY * diffSecY) <= 0
//                            && (diffPrimX * diffSecX) <= 0) {
//                return true;
//            }
//        }
//
//        return false;
//    }

	public View2(Context context, Model m) {
		super(context);
        myCanvas = new Canvas();

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.view2, this);


		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

        setWillNotDraw(false);


		// get a reference to widgets to manipulate on update
		textview = findViewById(R.id.view2_textview);
		
		// this is a view only view, no controller 
		// (unlike the mvc java swing example)
        ScaleGestureDetector SGD;
        //SGD = new ScaleGestureDetector(textview, new ScaleListener());
        textview.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                char tool = model.getTool();
                // do this each time the view is clicked.
                if (event.getPointerCount() > 1) {
                    for (int i = shapes.size() - 1; i >= 0; i--) {
                        if (shapes.get(i).contains(event.getX(), event.getY())) {
                            // keep track of object tracked
                            currentShape = shapes.get(i);
                            break;
                        }
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ptrCount++;
                    if (tool == 'r') { //rectangle
                        currentShape = new Rectangle(event.getX(), event.getX(), event.getY(), event.getY(), model.getColor(), model.getStroke());
                    }  if (tool == 'c') { // circle
                        currentShape = new Circle(event.getX(), event.getX(), event.getY(), event.getY(), model.getColor(), model.getStroke());

                    }
                     if (tool == 'l') { // line
                        currentShape = new Line(event.getX(), event.getX(), event.getY(), event.getY(), model.getColor(), model.getStroke());
                    }
                    if (tool == 's') { // selector
                        for (int i = shapes.size() - 1; i >= 0; i--) {
                            if (shapes.get(i).contains(event.getX(), event.getY())) {
                                // keep track of object tracked
                                currentShape = shapes.get(i);
                                offsetX = event.getX() - currentShape.getLeft();
                                offsetY = event.getY() - currentShape.getTop();
                                offsetX2 = event.getX() - currentShape.getRight();
                                offsetY2 = event.getY() - currentShape.getBottom();
                                width = currentShape.getWidth();
                                height = currentShape.getHeight();
                                break;
                            }
                        }
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
                    ptrCount++;
                }
                if (event.getAction() == MotionEvent.ACTION_POINTER_UP) {
                    ptrCount--;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (currentShape != null && (tool == 'r' || tool == 'c' || tool =='l')) {
                        currentShape.setX2(event.getX());
                        currentShape.setY2(event.getY());
                    }
                    else if (currentShape != null) { //selector tool
                        if (event.getPointerCount() == 1 && tool == 's') {
                            currentShape.setX1(event.getX() - offsetX);
                            currentShape.setY1(event.getY() - offsetY);
                            currentShape.setX2(currentShape.getX1() + width);
                            currentShape.setY2(currentShape.getY1() + height);
                        }
                        else if (event.getPointerCount() > 1 && tool == 'z') {
                            //Get the current distance
                            float distx = event.getX(0) - event.getX(1);
                            float disty = event.getY(0) - event.getY(1);
                            double distCurrent = Math.sqrt(distx * distx + disty * disty);

                            if (distCurrent >= prevPinchDistance) {
                                currentShape.setX2(currentShape.getX2() + 4);
                                currentShape.setY2(currentShape.getY2() + 4);
                                prevPinchDistance = distCurrent;
                            }
                            else {
                                currentShape.setX2(currentShape.getX2() - 4);
                                currentShape.setY2(currentShape.getY2() - 4);
                                prevPinchDistance = distCurrent;
                            }
                        }
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (currentShape != null && (tool == 'r' || tool == 'c' || tool =='l')) {
                        currentShape.setX2(event.getX());
                        currentShape.setY2(event.getY());
                        shapes.add(currentShape);
                        currentShape = null;
                    }
                    else if (currentShape != null && tool == 's') { //selector tool
                        currentShape.setX1(event.getX() - offsetX);
                        currentShape.setY1(event.getY() - offsetY);
                        currentShape.setX2(currentShape.getX1() + width);
                        currentShape.setY2(currentShape.getY1() + height);
                        currentShape = null;
                    }
                    else if (currentShape != null && tool == 'z') {
                        currentShape.setX2(currentShape.getX2());
                        currentShape.setY2(currentShape.getY2());
                        prevPinchDistance = 0;
                        currentShape = null;
                        scale = false;
                    }
                    else if (tool == 'p') {
                        for (int i = shapes.size() - 1; i >=0; i--) {
                            if (shapes.get(i).contains(event.getX(), event.getY())) {
                                shapes.get(i).filled = true;
                                String fillColor = model.getColor();
                                shapes.get(i).setFillColor(fillColor);
                                break;
                            }
                        }
                    }
                    else if (tool == 'e' && !holdClearCanvas) { // eraser
                        for (ListIterator<Shape> it = shapes.listIterator(shapes.size()); it.hasPrevious(); ) {
                            if (it.previous().contains(event.getX(), event.getY())) {
                                it.remove();
                                break;
                            }
                        }
                    }
                    ptrCount--;
                }
                draw(myCanvas);
                return true;

            }
        });

	}

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors

        for (int i = 0; i < shapes.size(); i++) {
            Shape shapeIt = shapes.get(i);
            paint.setColor(Color.WHITE);
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#" + shapeIt.getColor()));
            // set stroke
            paint.setStrokeWidth(shapeIt.getLineThickness() * 3);
            char type = shapeIt.getType();

            // if filled
            if (shapeIt.getFilled()) {
                paint.setStyle(Paint.Style.FILL);
                if (type == 'r') { //rectangle
                    paint.setColor(Color.parseColor("#" + shapeIt.getFillColor()));
                    canvas.drawRect(shapeIt.getLeft(), shapeIt.getTop(), shapeIt.getLeft() + shapeIt.getWidth(), shapeIt.getTop() + shapeIt.getHeight(), paint);
                }
                if (type == 'c') {
                    paint.setColor(Color.parseColor("#" + shapeIt.getFillColor()));
                    canvas.drawCircle(shapeIt.getLeft(), shapeIt.getTop(), shapeIt.getWidth()/2, paint );
                }
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#" + shapeIt.getColor()));
            if (type == 'r') { //rectangle
                canvas.drawRect(shapeIt.getLeft(), shapeIt.getTop(), shapeIt.getLeft() + shapeIt.getWidth(), shapeIt.getTop() + shapeIt.getHeight(), paint);
            }
            if (type == 'c') {
                canvas.drawCircle(shapeIt.getLeft(), shapeIt.getTop(), shapeIt.getWidth()/2, paint );
            }
            if (type == 'l') {
                canvas.drawLine(shapeIt.getX1(), shapeIt.getY1(), shapeIt.getX2(), shapeIt.getY2(), paint);
            }
        }

        // draw current shape
        if (currentShape != null) {
//            Paint paint1 = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#" + currentShape.getColor()));
            invalidate();
            char t = currentShape.getType();
            paint.setStrokeWidth(currentShape.getLineThickness() * 3);


            // if not filled
            paint.setColor(Color.parseColor("#111111"));
            paint.setStyle(Paint.Style.STROKE);
            paint.setPathEffect(new DashPathEffect(new float[] {10,4}, 0));
            //paint.setColor(Color.parseColor("#" + currentShape.getColor()));
            if (t == 'r') {
                canvas.drawRect(currentShape.getLeft(), currentShape.getTop(), currentShape.getLeft() + currentShape.getWidth(), currentShape.getTop() + currentShape.getHeight(), paint);
            }
            if (t == 'c') {
                canvas.drawCircle(currentShape.getLeft(), currentShape.getTop(), currentShape.getWidth()/2, paint );
            }
            if (t == 'l') {
                canvas.drawLine(currentShape.getX1(), currentShape.getY1(), currentShape.getX2(), currentShape.getY2(), paint);
            }
        }
        invalidate();
    }

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    
	    int n = model.getCounterValue();
	    
	    StringBuilder s = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {
	    	s.append("x");
	    }

		// update button text with click count
	    // (convert to string, or else Android uses int as resource id!)
	    //textview.setText(s);
	}


}
