package com.example.mvc;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.*;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class VerticalMenu extends LinearLayout implements Observer {

    private Model model;
    private View selectButton, eraseButton, rectangleButton, circleButton, lineButton, paintButton;
    private View redButton, orangeButton, yellowButton, greenButton, blueButton, purpleButton, stroke1Button, stroke2Button, stroke3Button, stroke4Button;
    private boolean holdClearCanvas;
    public VerticalMenu(Context context, Model m) {
        super(context);

        Log.d("MVC", "View1 constructor");

        // get the xml description of the view and "inflate" it
        // into the display (kind of like rendering it)
        View.inflate(context, R.layout.verticalmenu, this);

        // save the model reference
        model = m;
        // add this view to model's list of observers
        model.addObserver(this);

        // get a reference to widgets to manipulate on update
        selectButton = findViewById(R.id.select_btn);
        // create a controller for the button
        selectButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                model.setTool('s');
                // do this each time the button is clicked
                model.incrementCounter();
            }
        });

        eraseButton = findViewById(R.id.erase_btn);
        eraseButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //remove all shapes
                Log.d("clear", "allshapes");
                List<Shape> shapes = null;
                shapes = new ArrayList<Shape>();
                View2.setShapes(shapes);
                return true;
            }
        });
        eraseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setTool('e');
                model.incrementCounter();
            }
        });

        rectangleButton = findViewById(R.id.rectangle_btn);
        rectangleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setTool('r');
                model.incrementCounter();
            }
        });

        circleButton = findViewById(R.id.circle_btn);
        circleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setTool('c');
                model.incrementCounter();
            }
        });

        lineButton = findViewById(R.id.line_btn);
        lineButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setTool('l');
                model.incrementCounter();
            }
        });

        paintButton = findViewById(R.id.paint_btn);
        paintButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setTool('p');
                model.incrementCounter();
            }
        });

        redButton = findViewById(R.id.red_btn);
        redButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setColor("ed1c24");
                model.incrementCounter();
            }
        });

        orangeButton = findViewById(R.id.orange_btn);
        orangeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setColor("f16623");
                model.incrementCounter();
            }
        });

        yellowButton = findViewById(R.id.yellow_btn);
        yellowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setColor("ffff41");
                model.incrementCounter();
            }
        });


        greenButton = findViewById(R.id.green_btn);
        greenButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setColor("00a14b");
                model.incrementCounter();
            }
        });

        blueButton = findViewById(R.id.blue_btn);
        blueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // implement color picker here!
                model.setColor("27aae1");
                model.incrementCounter();
            }
        });

        purpleButton = findViewById(R.id.purple_btn);
        purpleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // implement color picker here!
                model.setColor("9263a3");
                model.incrementCounter();
            }
        });

        stroke1Button = findViewById(R.id.stroke1_btn);
        stroke1Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setStroke(1);
                model.incrementCounter();
            }
        });

        stroke2Button = findViewById(R.id.stroke2_btn);
        stroke2Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setStroke(2);
                model.incrementCounter();
            }
        });

        stroke3Button = findViewById(R.id.stroke3_btn);
        stroke3Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setStroke(3);
                model.incrementCounter();
            }
        });

        stroke4Button = findViewById(R.id.stroke4_btn);
        stroke4Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setStroke(4);
                model.incrementCounter();
            }
        });



    }

    // the model call this to update the view
    public void update(Observable observable, Object data) {
        Log.d("MVC", "update View1");
        char tool = model.getTool();
        String color = model.getColor();
        int stroke = model.getStroke();
        if (tool != 'r' && tool != 'c' && tool!= 'l') { // put stroke buttons to inactive
            stroke1Button.setBackgroundColor(Color.WHITE);
            stroke2Button.setBackgroundColor(Color.WHITE);
            stroke3Button.setBackgroundColor(Color.WHITE);
            stroke4Button.setBackgroundColor(Color.WHITE);
            // put color buttons to inactive
            redButton.setBackgroundColor(Color.WHITE);
            orangeButton.setBackgroundColor(Color.WHITE);
            greenButton.setBackgroundColor(Color.WHITE);
            blueButton.setBackgroundColor(Color.WHITE);
            purpleButton.setBackgroundColor(Color.WHITE);
        }
        if (tool == 'r' || tool == 'c' || tool=='l') {
            switch(stroke) {
                case 1:
                    stroke1Button.setBackgroundColor(Color.parseColor("#e0e0e0")) ;
                    stroke2Button.setBackgroundColor(Color.WHITE);
                    stroke3Button.setBackgroundColor(Color.WHITE);
                    stroke4Button.setBackgroundColor(Color.WHITE);
                    break;

                case 2:
                    stroke2Button.setBackgroundColor(Color.parseColor("#e0e0e0"));
                    stroke1Button.setBackgroundColor(Color.WHITE);
                    stroke3Button.setBackgroundColor(Color.WHITE);
                    stroke4Button.setBackgroundColor(Color.WHITE);
                    break;
                case 3:
                    stroke3Button.setBackgroundColor(Color.parseColor("#e0e0e0"));
                    stroke2Button.setBackgroundColor(Color.WHITE);
                    stroke1Button.setBackgroundColor(Color.WHITE);
                    stroke4Button.setBackgroundColor(Color.WHITE);
                    break;
                case 4:
                    stroke4Button.setBackgroundColor(Color.parseColor("#e0e0e0"));
                    stroke2Button.setBackgroundColor(Color.WHITE);
                    stroke3Button.setBackgroundColor(Color.WHITE);
                    stroke1Button.setBackgroundColor(Color.WHITE);
                    break;
            }
            if (color == "ed1c24") {
                redButton.setBackgroundColor(Color.parseColor("#ed1c24"));
                orangeButton.setBackgroundColor(Color.WHITE);
                yellowButton.setBackgroundColor(Color.WHITE);
                greenButton.setBackgroundColor(Color.WHITE);
                blueButton.setBackgroundColor(Color.WHITE);
                purpleButton.setBackgroundColor(Color.WHITE);
            }
            else if (color == "ffff41") {
                yellowButton.setBackgroundColor(Color.parseColor("#ffff41"));
                redButton.setBackgroundColor(Color.WHITE);
                orangeButton.setBackgroundColor(Color.WHITE);
                greenButton.setBackgroundColor(Color.WHITE);
                blueButton.setBackgroundColor(Color.WHITE);
                purpleButton.setBackgroundColor(Color.WHITE);
            }
            else if (color == "f16623") {
                orangeButton.setBackgroundColor(Color.parseColor("#f16623"));
                yellowButton.setBackgroundColor(Color.WHITE);
                redButton.setBackgroundColor(Color.WHITE);
                greenButton.setBackgroundColor(Color.WHITE);
                blueButton.setBackgroundColor(Color.WHITE);
                purpleButton.setBackgroundColor(Color.WHITE);
            }
            else if (color == "00a14b") {
                greenButton.setBackgroundColor(Color.parseColor("#00a14b"));
                yellowButton.setBackgroundColor(Color.WHITE);
                orangeButton.setBackgroundColor(Color.WHITE);
                redButton.setBackgroundColor(Color.WHITE);
                blueButton.setBackgroundColor(Color.WHITE);
                purpleButton.setBackgroundColor(Color.WHITE);
            }
            else if (color == "27aae1") {
                blueButton.setBackgroundColor(Color.parseColor("#27aae1"));
                yellowButton.setBackgroundColor(Color.WHITE);
                orangeButton.setBackgroundColor(Color.WHITE);
                greenButton.setBackgroundColor(Color.WHITE);
                redButton.setBackgroundColor(Color.WHITE);
                purpleButton.setBackgroundColor(Color.WHITE);
            }
            else if (color == "9263a3") {
                purpleButton.setBackgroundColor(Color.parseColor("#9263a3"));
                yellowButton.setBackgroundColor(Color.WHITE);
                orangeButton.setBackgroundColor(Color.WHITE);
                greenButton.setBackgroundColor(Color.WHITE);
                blueButton.setBackgroundColor(Color.WHITE);
                redButton.setBackgroundColor(Color.WHITE);
            }
        }
        if (tool == 's') {
            selectButton.setBackgroundColor(Color.parseColor("#e0e0e0"));
            eraseButton.setBackgroundColor(Color.WHITE);
            rectangleButton.setBackgroundColor(Color.WHITE);
            circleButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            paintButton.setBackgroundColor(Color.WHITE);
        }
        if (tool == 'e') {
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.parseColor("#e0e0e0"));
            rectangleButton.setBackgroundColor(Color.WHITE);
            circleButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            paintButton.setBackgroundColor(Color.WHITE);
        }
        if (tool == 'r') {
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            rectangleButton.setBackgroundColor(Color.parseColor("#e0e0e0"));
            circleButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            paintButton.setBackgroundColor(Color.WHITE);
        }
        if (tool == 'c') {
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            rectangleButton.setBackgroundColor(Color.WHITE);
            circleButton.setBackgroundColor(Color.parseColor("#e0e0e0"));
            lineButton.setBackgroundColor(Color.WHITE);
            paintButton.setBackgroundColor(Color.WHITE);
        }
        if (tool == 'l') {
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            rectangleButton.setBackgroundColor(Color.WHITE);
            circleButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.parseColor("#e0e0e0"));
            paintButton.setBackgroundColor(Color.WHITE);
        }
        if (tool == 'p') {
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            rectangleButton.setBackgroundColor(Color.WHITE);
            circleButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            String hexColor = "#" + color;
            paintButton.setBackgroundColor(Color.parseColor(hexColor));
        }
    }
}
