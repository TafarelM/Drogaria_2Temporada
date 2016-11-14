package br.com.drogaria.filter;

import java.util.Date;

public class VendaFilter {
	private Date dataInicial;
	private Date datafinal;
	
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDatafinal() {
		return datafinal;
	}
	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}	
}
