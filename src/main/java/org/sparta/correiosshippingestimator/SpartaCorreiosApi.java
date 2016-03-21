/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.sparta.correiosshippingestimator.config.ContextConfig;
import org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest;
import org.sparta.correiosshippingestimator.entity.CotacaoPrazoResponse;
import org.sparta.correiosshippingestimator.entity.CotacaoPrecoPrazoResponse;
import org.sparta.correiosshippingestimator.entity.CotacaoPrecoResponse;
import org.sparta.correiosshippingestimator.service.SpartaCorreiosService;
import org.sparta.springwebutils.SpringContextUtils;
import org.springframework.context.ApplicationContext;

/**
 * Api de calculo de preco e/ou prazo pelo Web Service dos correios.
 * 
 * @author Daniel Conde Diehl
 *
 */
public class SpartaCorreiosApi {
	
	private ApplicationContext ctx;
	private SpartaCorreiosService service;
	
	/**
	 * Init library.
	 * 
	 * @param endpoint URL dos correios
	 */
	public SpartaCorreiosApi(String endpoint) {
		Map<String, Object> extraBeans = new HashMap<>();
		extraBeans.put("endpoint",  endpoint);
		ctx = SpringContextUtils.contextMergedBeans(extraBeans, ContextConfig.class);
		service = ctx.getBean(SpartaCorreiosService.class);
	}
	
	/**
	 * Calculo de preco pelos Web Service dos correios.
	 * 
	 * @param request parametros de request.
	 * @return objeto com resposta para todos os servicos requisitado.
	 * @throws Exception
	 */
	public Map<String, CotacaoPrecoResponse> calcularPreco(CorreiosEstimatorRequest request) throws Exception {
		return service.calcularPreco(request);
	}
	
	/**
	 * Calculo de prazo pelos Web Service dos correios.
	 * 
	 * @param servicos
	 * @param cepOrigem
	 * @param cepDestino
	 * @return objeto com resposta para todos os servicos requisitado.
	 * @throws Exception
	 */
	public Map<String, CotacaoPrazoResponse> calcularPrazo(Set<String> servicos, long cepOrigem, long cepDestino) throws Exception {
		return service.calcularPrazo(servicos, cepOrigem, cepDestino);
	}
	
	/**
	 * Calculo de preco e prazo pelos Web Service dos correios.
	 * 
	 * @param request parametros de request.
	 * @return objeto com resposta para todos os servicos requisitado.
	 * @throws Exception
	 */
	public Map<String, CotacaoPrecoPrazoResponse> calcularPrecoPrazo(CorreiosEstimatorRequest request) throws Exception {
		return service.calcularPrecoPrazo(request);
	}
}
