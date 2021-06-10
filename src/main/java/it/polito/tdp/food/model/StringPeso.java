package it.polito.tdp.food.model;

public class StringPeso {
	String portion;
	int peso;
	public StringPeso(String portion, int peso) {
		super();
		this.portion = portion;
		this.peso = peso;
	}
	public String getPortion() {
		return portion;
	}
	public void setPortion(String portion) {
		this.portion = portion;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
}
