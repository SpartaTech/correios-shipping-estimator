/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto contendo os dados de resposta para preco.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class PrecoResponse {

	private Double valorTotal; 
	private Double valorMaoPropria;
	private Double valorAvisoRecebimento;
	private Double valorEnvioComValorDeclarado;
	

	/**
	 * @return the valorTotal
	 */
	public Double getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	/**
	 * @return the valorMaoPropria
	 */
	public Double getValorMaoPropria() {
		return valorMaoPropria;
	}
	/**
	 * @param valorMaoPropria the valorMaoPropria to set
	 */
	public void setValorMaoPropria(Double valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}
	/**
	 * @return the valorAvisoRecebimento
	 */
	public Double getValorAvisoRecebimento() {
		return valorAvisoRecebimento;
	}
	/**
	 * @param valorAvisoRecebimento the valorAvisoRecebimento to set
	 */
	public void setValorAvisoRecebimento(Double valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}
	/**
	 * @return the valorEnvioComValorDeclarado
	 */
	public Double getValorEnvioComValorDeclarado() {
		return valorEnvioComValorDeclarado;
	}
	/**
	 * @param valorEnvioComValorDeclarado the valorEnvioComValorDeclarado to set
	 */
	public void setValorEnvioComValorDeclarado(Double valorEnvioComValorDeclarado) {
		this.valorEnvioComValorDeclarado = valorEnvioComValorDeclarado;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
