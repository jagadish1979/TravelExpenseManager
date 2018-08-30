package com.expenses.domain;

import java.io.Serializable;

public class ChartData implements Serializable {

	private double value;
	private String color;
	private String highlight;
	private String label;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "PieChartData [value=" + value + ", color=" + color + ", highlight=" + highlight + ", label=" + label
				+ "]";
	}
	
	
}
