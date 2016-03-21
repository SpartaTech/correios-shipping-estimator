/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.entity;

/**
 * Factory para objetos de resposta da cotacao.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class CotacaoResponseFactory {

	/**
	 * Constroi novo objeto de resposta do prazo. 
	 * 
	 * @param codigoServico codigo do servico
	 * @param prazo objeto contendo valores de resposta de prazo
	 * @return CotacaoPrazoResponse
	 */
	public static CotacaoPrazoResponse newCotacaoPrazoResponse (String codigoServico, PrazoResponse prazo) {
		CotacaoPrazoResponse response = new CotacaoPrazoResponse(prazo);
		response.setCodigoServico(codigoServico);
		return response;
	}
	
	/**
	 * Constroi novo objeto de resposta do preco. 
	 * 
	 * @param codigoServico codigo do servico
	 * @param preco objeto contendo valores de resposta de preco
	 * @return CotacaoPrecoResponse
	 */
	public static CotacaoPrecoResponse newCotacaoPrecoResponse (String codigoServico, PrecoResponse preco) {
		CotacaoPrecoResponse response = new CotacaoPrecoResponse(preco);
		response.setCodigoServico(codigoServico);
		return response;
	}
	
	/**
	 * Constroi novo objeto de resposta do preco e prazo 
	 * 
	 * @param codigoServico codigo do servico
	 * @param preco objeto contendo valores de resposta de preco
	 * @param prazo objeto contendo valores de resposta de prazo
	 * @return CotacaoPrecoPrazoResponse
	 */
	public static CotacaoPrecoPrazoResponse newCotacaoPrecoPrazoResponse (String codigoServico, PrecoResponse preco, PrazoResponse prazo) {
		CotacaoPrecoPrazoResponse response = new CotacaoPrecoPrazoResponse(preco, prazo);
		response.setCodigoServico(codigoServico);
		return response;
	}
	
	/**
	 * Cria um objeto com a mensagem de erro.
	 * 
	 * @param codigoServico codigo do servico
	 * @param erro Codigo de erro
	 * @param mensagemErro Mensagem de erro
	 * @param typeClass Classe especifica para gerar construtor 
	 * @return T objeto gerado
	 * @throws RuntimeException Caso a classe nao pode ser criada 
	 */
	public static <T extends CotacaoResponse> T newErroCotacao (String codigoServico, String erro, String mensagemErro, Class<T> typeClass) throws RuntimeException {
		try {
			T obj = typeClass.getDeclaredConstructor(String.class, String.class).newInstance(erro, mensagemErro);
			obj.setCodigoServico(codigoServico);
			return obj;
		} catch (Exception e) { 
			throw new RuntimeException("Erro gerando objeto. ", e);
		}
	}
}
