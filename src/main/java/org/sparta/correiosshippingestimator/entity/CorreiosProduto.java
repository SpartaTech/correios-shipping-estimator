/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Produto, usado para request de caixa, usando calculo automatico de multi-volume.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class CorreiosProduto {
	
	private Double peso;
	private Double comprimento;
	private Double largura;
	private Double altura;
	
	/**
	 * 
	 */
	public CorreiosProduto() {
		// TODO Auto-generated constructor stub
	}
	
	public CorreiosProduto(Double peso, Double comprimento, Double largura, Double altura) {
		super();
		this.peso = peso;
		this.comprimento = comprimento;
		this.largura = largura;
		this.altura = altura;
	}
	/**
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	/**
	 * @return the comprimento
	 */
	public Double getComprimento() {
		return comprimento;
	}
	/**
	 * @param comprimento the comprimento to set
	 */
	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}
	/**
	 * @return the largura
	 */
	public Double getLargura() {
		return largura;
	}
	/**
	 * @param largura the largura to set
	 */
	public void setLargura(Double largura) {
		this.largura = largura;
	}
	/**
	 * @return the altura
	 */
	public Double getAltura() {
		return altura;
	}
	/**
	 * @param altura the altura to set
	 */
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
