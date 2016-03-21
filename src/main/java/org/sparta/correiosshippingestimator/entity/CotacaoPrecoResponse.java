/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto resposta para Cotacao Preco.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class CotacaoPrecoResponse extends CotacaoResponse {
	private PrecoResponse preco = new PrecoResponse();
	
	/**
	 * Package constructor recebendo o preco.
	 * @param preco objeto com informacoes de preco
	 */
	CotacaoPrecoResponse(PrecoResponse preco) {
		this.preco = preco;
	}
	
	/**
	 * Package constructor quando houver erro na execucao.
	 * 
	 * @param erro codigo de erro
	 * @param mensagemErro mensagem de erro
	 */
	CotacaoPrecoResponse(String erro, String mensagemErro) {
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


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
