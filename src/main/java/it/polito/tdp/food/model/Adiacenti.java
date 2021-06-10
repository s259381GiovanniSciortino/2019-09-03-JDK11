package it.polito.tdp.food.model;

public class Adiacenti {
	String portion1;
	String portion2;
	int peso;
	public Adiacenti(String portion1, String portion2, int peso) {
		super();
		this.portion1 = portion1;
		this.portion2 = portion2;
		this.peso = peso;
	}
	public String getPortion1() {
		return portion1;
	}
	public void setPortion1(String portion1) {
		this.portion1 = portion1;
	}
	public String getPortion2() {
		return portion2;
	}
	public void setPortion2(String portion2) {
		this.portion2 = portion2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
}
