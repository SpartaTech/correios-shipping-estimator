/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto resposta para Cotacao de Preco e Prazo.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class CotacaoPrecoPrazoResponse extends CotacaoResponse {
	private PrecoResponse preco;
	private PrazoResponse prazo;
	
	/**
	 * package constructor com informacoes de preco e prazo
	 * 
	 * @param preco objeto com informacoes de preco
	 * @param prazo objeto com informacoes de prazo
	 * 
	 */
	CotacaoPrecoPrazoResponse(PrecoResponse preco, PrazoResponse prazo) {
		this.preco = preco;
		this.prazo = prazo;
	}
	
	/**
	 * Package constructor quando houver erro na execucao.
	 * 
	 * @param erro codigo de erro
	 * @param mensagemErro mensagem de erro
	 */
	CotacaoPrecoPrazoResponse(String erro, String mensagemErro) {
		super(erro, mensagemErro);
	}
	
	/**
	 * @return the valorTotal
	 */
	public Double getValorTotal() {
		return preco.getValorTotal();
	}

	/**
	 * @return the valorMaoPropria
	 */
	public Double getValorMaoPropria() {
		return preco.getValorMaoPropria();
	}

	/**
	 * @return the valorAvisoRecebimento
	 */
	public Double getValorAvisoRecebimento() {
		return preco.getValorAvisoRecebimento();
	}

	/**
	 * @return the valorEnvioComValorDeclarado
	 */
	public Double getValorEnvioComValorDeclarado() {
		return preco.getValorEnvioComValorDeclarado();
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
