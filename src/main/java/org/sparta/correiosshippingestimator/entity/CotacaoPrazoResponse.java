/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto resposta para Cotacao Prazo.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class CotacaoPrazoResponse extends CotacaoResponse {

	private PrazoResponse prazo;
	
	/**
	 * Package constructor recebendo o prazo
	 * 
	 * @param prazo objeto com informacoes de prazo
	 */
	CotacaoPrazoResponse(PrazoResponse prazo) {
		this.prazo = prazo;
	}
	
	/**
	 * Package constructor quando houver erro na execucao.
	 * 
	 * @param erro codigo de erro
	 * @param mensagemErro mensagem de erro
	 */
	CotacaoPrazoResponse(String erro, String mensagemErro) {
		super(erro, mensagemErro);
	}
	
	/**
	 * @return the prazoEntrega
	 */
	public int getPrazoEntrega() {
		return prazo.getPrazoEntrega();
	}

	/**
	 * @return the entregaDomiciliar
	 */
	public boolean isEntregaDomiciliar() {
		return prazo.isEntregaDomiciliar();
	}
	/**
	 * @return the entregaSabado
	 */
	public boolean isEntregaSabado() {
		return prazo.isEntregaSabado();
	}	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
