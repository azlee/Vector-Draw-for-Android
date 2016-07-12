package com.example.mvc;

import android.util.Log;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
	private int counter;
    private int stroke = 1;
    private char tool; // hold current tool value
    // s = select, e = erase, r = rectangle, c = circle, l = line, p = paint
    private String color = "ed1c24"; // hold hex color of the current color

	Model() {
		counter = 0;
	}

	// Data methods
	public int getCounterValue() {
		return counter;
	}

	public void setCounterValue(int i) {
		counter = i;
		//Log.d("MVC", "Model: set counter to " + counter);
		setChanged();
		notifyObservers();
	}

	public void incrementCounter() {
		//Log.d("MVC", "Model: increment counter to " + counter);
		setChanged();
		notifyObservers();
	}

    public void setStroke(int s) {
        stroke = s;
    }

    public int getStroke() {
        return stroke;
    }

    public void setColor(String c) {
        color = c;
    }

    public String getColor() {
        return color;
    }

    public void setTool(char t) {
        tool = t;
    }

    public char getTool() {
        return tool;
    }
	// Observer methods
	@Override
	public void addObserver(Observer observer) {
		Log.d("MVC", "Model: Observer added");
		super.addObserver(observer);
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public void notifyObservers() {
		//Log.d("MVC", "Model: Observers notified");
		super.notifyObservers();
	}

	@Override
	protected void setChanged() {
		super.setChanged();
	}

	@Override
	protected void clearChanged() {
		super.clearChanged();
	}
}