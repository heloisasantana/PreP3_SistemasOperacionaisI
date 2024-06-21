package model;

public class Drink {

	private String nomeReceita;
	private String instrucoesEmItaliano;

	public Drink(String nomeReceita, String instrucoesEmItaliano) {
		this.nomeReceita = nomeReceita;
	    this.instrucoesEmItaliano = instrucoesEmItaliano;
	}

	public String getNomeReceita() {
		return nomeReceita;
	}

	public String getInstrucoesEmItaliano() {
		return instrucoesEmItaliano;
	}

}