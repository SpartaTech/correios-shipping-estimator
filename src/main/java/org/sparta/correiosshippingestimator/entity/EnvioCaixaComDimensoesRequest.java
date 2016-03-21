/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import org.sparta.correiosshippingestimator.constant.FormatoEncomenda;

/**
 * Objeto de request usando Caixa/Pacote informando as dimensões do pacote.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class EnvioCaixaComDimensoesRequest extends CorreiosEstimatorRequest {

	private Double pesoTotal;
	private Double alturaPacote;
	private Double larguraPacote;
	private Double comprimentoPacote;
	
	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getFormatoEncomenda()
	 */
	@Override
	public FormatoEncomenda getFormatoEncomenda() {
		return FormatoEncomenda.CAIXA;
	}

	/**
	 * @param pesoTotal the pesoTotal to set
	 */
	public void setPesoTotal(Double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
	
	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getPesoTotal()
	 */
	@Override
	public Double getPesoTotal() {
		return pesoTotal;
	}
	
	/**
	 * @param comprimentoPacote the comprimentoPacote to set
	 */
	public void setComprimentoPacote(Double comprimentoPacote) {
		this.comprimentoPacote = comprimentoPacote;
	}

	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getComprimentoPacote()
	 */
	@Override
	public Double getComprimentoPacote() {
		return comprimentoPacote;
	}

	/**
	 * @param larguraPacote the larguraPacote to set
	 */
	public void setLarguraPacote(Double larguraPacote) {
		this.larguraPacote = larguraPacote;
	}
	
	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getLarguraPacote()
	 */
	@Override
	public Double getLarguraPacote() {
		return larguraPacote;
	}

	/**
	 * @param alturaPacote the alturaPacote to set
	 */
	public void setAlturaPacote(Double alturaPacote) {
		this.alturaPacote = alturaPacote;
	}
	
	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getAlturaPacote()
	 */
	@Override
	public Double getAlturaPacote() {
		return alturaPacote;
	}
	
	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getDiametroPacote()
	 */
	@Override
	public Double getDiametroPacote() {
		return 0d;
	}
}
