/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto contendo os dados de resposta para prazo.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class PrazoResponse {
	private int prazoEntrega;
	private boolean entregaDomiciliar;
	private boolean entregaSabado;
	/**
	 * @return the prazoEntrega
	 */
	public int getPrazoEntrega() {
		return prazoEntrega;
	}
	/**
	 * @param prazoEntrega the prazoEntrega to set
	 */
	public void setPrazoEntrega(int prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	/**
	 * @return the entregaDomiciliar
	 */
	public boolean isEntregaDomiciliar() {
		return entregaDomiciliar;
	}
	/**
	 * @param entregaDomiciliar the entregaDomiciliar to set
	 */
	public void setEntregaDomiciliar(boolean entregaDomiciliar) {
		this.entregaDomiciliar = entregaDomiciliar;
	}
	/**
	 * @return the entregaSabado
	 */
	public boolean isEntregaSabado() {
		return entregaSabado;
	}
	/**
	 * @param entregaSabado the entregaSabado to set
	 */
	public void setEntregaSabado(boolean entregaSabado) {
		this.entregaSabado = entregaSabado;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
