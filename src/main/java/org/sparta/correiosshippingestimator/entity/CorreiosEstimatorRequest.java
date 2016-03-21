/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.sparta.correiosshippingestimator.constant.FormatoEncomenda;

/**
 * Objeto basico para consulta dos correios.
 * 
 * @author Daniel Conde Diehl
 *
 */
public abstract class CorreiosEstimatorRequest {

	private Set<String> codigosServico = new LinkedHashSet<>();
	private Long cepOrigem;
	private Long cepDestino;
	private boolean EntregaMaoPropria;
	private boolean avisoRecebimento;
	private BigDecimal valorDeclarado;
	/**
	 * @return the codigoServico
	 */
	public Set<String> getCodigosServico() {
		return codigosServico;
	}
	/**
	 * Adiciona servico para ser consultado.
	 * 
	 * @see {@code ServicosCorreioConstants} para valores validos
	 * 
	 * @param codigoServico the codigoServico to set
	 */
	public void addCodigoServico(String codigoServico) {
		this.codigosServico.add(codigoServico);
	}
	/**
	 * @return the cepOrigem
	 */
	public Long getCepOrigem() {
		return cepOrigem;
	}
	/**
	 * @param cepOrigem the cepOrigem to set
	 */
	public void setCepOrigem(Long cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	/**
	 * @return the cepDestino
	 */
	public Long getCepDestino() {
		return cepDestino;
	}
	/**
	 * @param cepDestino the cepDestino to set
	 */
	public void setCepDestino(Long cepDestino) {
		this.cepDestino = cepDestino;
	}
	/**
	 * @return the entregaMaoPropria
	 */
	public boolean isEntregaMaoPropria() {
		return EntregaMaoPropria;
	}
	/**
	 * @param entregaMaoPropria the entregaMaoPropria to set
	 */
	public void setEntregaMaoPropria(boolean entregaMaoPropria) {
		EntregaMaoPropria = entregaMaoPropria;
	}
	/**
	 * @return the avisoRecebimento
	 */
	public boolean isAvisoRecebimento() {
		return avisoRecebimento;
	}
	/**
	 * @param avisoRecebimento the avisoRecebimento to set
	 */
	public void setAvisoRecebimento(boolean avisoRecebimento) {
		this.avisoRecebimento = avisoRecebimento;
	}
	/**
	 * @return the valorDeclarado
	 */
	public BigDecimal getValorDeclarado() {
		return valorDeclarado;
	}
	/**
	 * @param valorDeclarado the valorDeclarado to set
	 */
	public void setValorDeclarado(BigDecimal valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * Retorna o tipo de formato da encomenda.
	 * 
	 * @return formato da encomenda
	 */
	public abstract FormatoEncomenda getFormatoEncomenda();

	/**
	 * Peso total do pacote. Em kilos.
	 * 
	 * @return peso do pacote
	 */
	public abstract Double getPesoTotal();
	
	/**
	 * Comprimento do pacote, incluindo embalagem em centimetros.
	 * 
	 * @return comprimento pacote
	 */
	public abstract Double getComprimentoPacote();
	
	/**
	 * Largura do pacote, incluindo embalagem em centimetros.
	 * 
	 * @return largura pacote
	 */
	public abstract Double getLarguraPacote();
	
	/**
	 * Altura do pacote, incluindo embalagem em centimetros.
	 * Apenas para caixa/pacote.
	 * 
	 * @return altura pacote
	 */
	public abstract Double getAlturaPacote();
	
	/**
	 * Comprimento do pacote, incluindo embalagem em centimetros.
	 * Apenas para Rolo/Prisma.
	 * 
	 * @return
	 */
	public abstract Double getDiametroPacote();
}
