/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

import java.util.ArrayList;
import java.util.List;

import org.sparta.correiosshippingestimator.constant.FormatoEncomenda;

/**
 * Request para envio de Caixa/Pacote, com calculo automatico das dimensões.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class RequestCaixaListaProdutosRequest extends CorreiosEstimatorRequest {

	private List<CorreiosProduto> produtos = new ArrayList<>();
	
	private Double dimensao = null;
	
	/**
	 * Adicionar produto a lista.
	 * 
	 * @param produto
	 */
	public void addProduto(CorreiosProduto produto) {
		this.produtos.add(produto);
		dimensao = null;
	}
	
	/**
	 * @return the produtos
	 */
	public List<CorreiosProduto> getProdutos() {
		dimensao = null;
		return produtos;
	}

	
	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getFormatoEncomenda()
	 */
	@Override
	public FormatoEncomenda getFormatoEncomenda() {
		return FormatoEncomenda.CAIXA;
	}

	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getPesoTotal()
	 */
	@Override
	public Double getPesoTotal() {
		return produtos.stream().mapToDouble(produto -> produto.getPeso()).sum();
	}

	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getComprimentoPacote()
	 */
	@Override
	public Double getComprimentoPacote() {
		return getDimensao();
	}

	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getLarguraPacote()
	 */
	@Override
	public Double getLarguraPacote() {
		return getDimensao();
	}

	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getAlturaPacote()
	 */
	@Override
	public Double getAlturaPacote() {
		return getDimensao();
	}

	/* (non-Javadoc)
	 * @see org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest#getDiametroPacote()
	 */
	@Override
	public Double getDiametroPacote() {
		return 0d;
	}
	
	/**
	 * Calcula e retorna volume total do pacote
	 * 
	 * @return volume total
	 */
	private Double getDimensao() {
		if (dimensao == null) {
			this.dimensao = Math.cbrt(
						produtos.stream()
							.mapToDouble(p -> p.getAltura() * p.getComprimento() * p.getLargura())
							.sum()
					);
		}
		return dimensao;
	}
}
