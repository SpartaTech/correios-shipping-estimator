/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto Base de Cotação
 * 
 * @author Daniel Conde Diehl
 *
 */
public abstract class CotacaoResponse {
	private String codigoServico;
	private boolean successo;
	private String erro;
	private String mensagemErro;
	
	/**
	 * Constructor in case of success 
	 */
	CotacaoResponse() {
		successo = true;
	}
	
	/**
	 * Costrutor usado quando a resposta foi erro.
	 * 
	 * @param erro Codigo do erro
	 * @param mensagemErro Mensagem de erro
	 */
	CotacaoResponse(String erro, String mensagemErro) {
		this.successo = false;
		this.erro = erro;
		this.mensagemErro = mensagemErro;
	}
	
	/**
	 * @return the codigoServico
	 */
	public String getCodigoServico() {
		return codigoServico;
	}
	/**
	 * @param codigoServico the codigoServico to set
	 */
	void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	
	/**
	 * @return the successo
	 */
	public boolean isSuccesso() {
		return successo;
	}
	/**
	 * @param successo the successo to set
	 */
	public void setSuccesso(boolean successo) {
		this.successo = successo;
	}
	/**
	 * @return the erro
	 */
	public String getErro() {
		return erro;
	}
	/**
	 * @param erro the erro to set
	 */
	public void setErro(String erro) {
		this.erro = erro;
	}
	/**
	 * @return the mensagemErro
	 */
	public String getMensagemErro() {
		return mensagemErro;
	}
	/**
	 * @param mensagemErro the mensagemErro to set
	 */
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
