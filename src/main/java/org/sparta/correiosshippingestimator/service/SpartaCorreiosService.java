/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparta.correiosshippingestimator.entity.CorreiosEstimatorRequest;
import org.sparta.correiosshippingestimator.entity.CotacaoPrazoResponse;
import org.sparta.correiosshippingestimator.entity.CotacaoPrecoPrazoResponse;
import org.sparta.correiosshippingestimator.entity.CotacaoPrecoResponse;
import org.sparta.correiosshippingestimator.entity.CotacaoResponse;
import org.sparta.correiosshippingestimator.entity.CotacaoResponseFactory;
import org.sparta.correiosshippingestimator.entity.PrazoResponse;
import org.sparta.correiosshippingestimator.entity.PrecoResponse;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;

import com.sparta.virtualstore.schema.correios.CServico;
import com.sparta.virtualstore.schema.correios.CalcPrazo;
import com.sparta.virtualstore.schema.correios.CalcPrazoResponse;
import com.sparta.virtualstore.schema.correios.CalcPreco;
import com.sparta.virtualstore.schema.correios.CalcPrecoPrazo;
import com.sparta.virtualstore.schema.correios.CalcPrecoPrazoResponse;
import com.sparta.virtualstore.schema.correios.CalcPrecoResponse;

/**
 * Sparta service para contato do WS dos correios
 * 
 * @author Daniel Conde Diehl
 *
 */
public class SpartaCorreiosService extends WebServiceGatewaySupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpartaCorreiosService.class);
	
	private final static DecimalFormatSymbols PT_BR = DecimalFormatSymbols.getInstance(new Locale( "pt" , "BR" ));
	private final static String FORMAT_NUMERO = "#,##0.00";
	
	/**
	 * Calcular Preco contatando os correios. 
	 * 
	 * @param apiRequest parametros de request
	 * @return {@code Map<String, CotacaoPrecoResponse>} map contendo resposta para todos as cotacoes requisitadas 
	 * @throws ParseException 
	 */
	public Map<String, CotacaoPrecoResponse> calcularPreco (CorreiosEstimatorRequest apiRequest) throws ParseException {
		DecimalFormat df = new DecimalFormat(FORMAT_NUMERO, PT_BR);
		
		CalcPreco request = new CalcPreco();
		request.setNCdServico(StringUtils.join(apiRequest.getCodigosServico(),","));
		request.setSCepOrigem(apiRequest.getCepOrigem().toString());
		request.setSCepDestino(apiRequest.getCepDestino().toString());
		request.setNVlPeso(df.format(apiRequest.getPesoTotal()));
		request.setNCdFormato(apiRequest.getFormatoEncomenda().getCodigo());
		request.setNVlComprimento(BigDecimal.valueOf(apiRequest.getComprimentoPacote()));
		request.setNVlLargura(BigDecimal.valueOf(apiRequest.getLarguraPacote()));
		request.setNVlAltura(BigDecimal.valueOf(apiRequest.getAlturaPacote()));
		request.setNVlDiametro(BigDecimal.valueOf(apiRequest.getDiametroPacote()));
		request.setSCdMaoPropria(apiRequest.isEntregaMaoPropria() ? "S" : "N");
		request.setNVlValorDeclarado(apiRequest.getValorDeclarado());
		request.setSCdAvisoRecebimento(apiRequest.isAvisoRecebimento() ? "S" : "N");
		
		CalcPrecoResponse response = (CalcPrecoResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new WebServiceMessageCallback() {
			        public void doWithMessage(WebServiceMessage message) {
			            ((SoapMessage)message).setSoapAction("http://tempuri.org/CalcPreco");
			        }
				});
		
		Map<String, CotacaoPrecoResponse> result = 
				response.getCalcPrecoResult().getServicos().getCServico()
						.stream()
						.peek(cs -> {
								if (StringUtils.isNotEmpty(cs.getErro())) {
									LOGGER.warn("Erro calculando preco para {}, errorCode={}, errorMsg={}", cs.getCodigo(), cs.getErro(), cs.getMsgErro());
								}
						})
						.collect(Collectors.toMap(
								s -> String.valueOf(s.getCodigo()), 
								s -> {
										try {
											CotacaoPrecoResponse errorResponse = validarErro(s, CotacaoPrecoResponse.class);
											if (errorResponse != null) {
												return errorResponse;
											}
											
											PrecoResponse preco = new PrecoResponse();
											preco.setValorAvisoRecebimento(df.parse(s.getValorAvisoRecebimento()).doubleValue());
											preco.setValorEnvioComValorDeclarado(df.parse(s.getValorAvisoRecebimento()).doubleValue());
											preco.setValorMaoPropria(df.parse(s.getValorMaoPropria()).doubleValue());
											preco.setValorTotal(df.parse(s.getValor()).doubleValue());
											
											return CotacaoResponseFactory.newCotacaoPrecoResponse(String.valueOf(s.getCodigo()), preco);
										} catch (ParseException e) {
											throw new RuntimeException("Invalid value");
										}
									 }
						));
		
		return result;
	}

	/**
	 * Calcular Prazo, contatando os correios. 
	 * 
	 * @param servicos @see {@code ServicosCorreioConstants} para valores validos 
	 * @param cepOrigem cep de origem
	 * @param cepDestino cep de destino
	 * @return {@code Map<String, CotacaoPrecoResponse>} map contendo resposta para todos as cotacoes requisitadas 
	 * @throws ParseException 
	 */
	public Map<String, CotacaoPrazoResponse> calcularPrazo (Set<String> servicos, long cepOrigem, long cepDestino) throws ParseException {
		CalcPrazo request = new CalcPrazo();
		request.setNCdServico(StringUtils.join(servicos, ","));
		request.setSCepOrigem(String.valueOf(cepOrigem));
		request.setSCepDestino(String.valueOf(cepDestino));
		
		CalcPrazoResponse response = (CalcPrazoResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new WebServiceMessageCallback() {
			        public void doWithMessage(WebServiceMessage message) {
			            ((SoapMessage)message).setSoapAction("http://tempuri.org/CalcPrazo");
			        }
				});
		
		Map<String, CotacaoPrazoResponse> result = 
				response.getCalcPrazoResult().getServicos().getCServico()
						.stream()
						.peek(cs -> {
								if (StringUtils.isNotEmpty(cs.getErro())) {
									LOGGER.warn("Erro calculando prazo para {}, errorCode={}, errorMsg={}", cs.getCodigo(), cs.getErro(), cs.getMsgErro());
								}
						})
						.collect(Collectors.toMap(s -> String.valueOf(s.getCodigo()), s -> {
											CotacaoPrazoResponse errorResponse = validarErro(s, CotacaoPrazoResponse.class);
											if (errorResponse != null) {
												return errorResponse;
											}
						
											PrazoResponse prazo = new PrazoResponse();
											prazo.setEntregaDomiciliar(s.getEntregaDomiciliar() == "S" ? true : false);
											prazo.setEntregaSabado(s.getEntregaSabado() == "S" ? true : false);
											prazo.setPrazoEntrega(Integer.parseInt(s.getPrazoEntrega()));
						
											return CotacaoResponseFactory.newCotacaoPrazoResponse(String.valueOf(s.getCodigo()), prazo);
										}
						));
		
		return result;
	}

	
	/**
	 * Calcular Preco  e prazo, contatando os correios. 
	 * 
	 * @param apiRequest parametros de request
	 * @return {@code Map<String, CotacaoPrecoPrazoResponse>} map contendo resposta para todos as cotacoes requisitadas 
	 * @throws ParseException 
	 */
	public Map<String, CotacaoPrecoPrazoResponse> calcularPrecoPrazo (CorreiosEstimatorRequest apiRequest) throws ParseException {
		DecimalFormat df = new DecimalFormat(FORMAT_NUMERO, PT_BR);
		
		CalcPrecoPrazo request = new CalcPrecoPrazo();
		request.setNCdServico(StringUtils.join(apiRequest.getCodigosServico(),","));
		request.setSCepOrigem(apiRequest.getCepOrigem().toString());
		request.setSCepDestino(apiRequest.getCepDestino().toString());
		request.setNVlPeso(df.format(apiRequest.getPesoTotal()));
		request.setNCdFormato(apiRequest.getFormatoEncomenda().getCodigo());
		request.setNVlComprimento(BigDecimal.valueOf(apiRequest.getComprimentoPacote()));
		request.setNVlLargura(BigDecimal.valueOf(apiRequest.getLarguraPacote()));
		request.setNVlAltura(BigDecimal.valueOf(apiRequest.getAlturaPacote()));
		request.setNVlDiametro(BigDecimal.valueOf(apiRequest.getDiametroPacote()));
		request.setSCdMaoPropria(apiRequest.isEntregaMaoPropria() ? "S" : "N");
		request.setNVlValorDeclarado(apiRequest.getValorDeclarado());
		request.setSCdAvisoRecebimento(apiRequest.isAvisoRecebimento() ? "S" : "N");
		
		CalcPrecoPrazoResponse response = (CalcPrecoPrazoResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new WebServiceMessageCallback() {
			        public void doWithMessage(WebServiceMessage message) {
			            ((SoapMessage)message).setSoapAction("http://tempuri.org/CalcPrecoPrazo");
			        }
				});
		
		Map<String, CotacaoPrecoPrazoResponse> result = 
				response.getCalcPrecoPrazoResult().getServicos().getCServico()
						.stream()
						.peek(cs -> {
								if (StringUtils.isNotEmpty(cs.getErro())) {
									LOGGER.warn("Erro calculando preco e prazo para {}, errorCode={}, errorMsg={}", cs.getCodigo(), cs.getErro(), cs.getMsgErro());
								}
						})
						.collect(Collectors.toMap(
								s -> String.valueOf(s.getCodigo()), 
								s -> {
										try {
											CotacaoPrecoPrazoResponse errorResponse = validarErro(s, CotacaoPrecoPrazoResponse.class);
											if (errorResponse != null) {
												return errorResponse;
											}
											
											PrecoResponse preco = new PrecoResponse();
											preco.setValorAvisoRecebimento(df.parse(s.getValorAvisoRecebimento()).doubleValue());
											preco.setValorEnvioComValorDeclarado(df.parse(s.getValorAvisoRecebimento()).doubleValue());
											preco.setValorMaoPropria(df.parse(s.getValorMaoPropria()).doubleValue());
											preco.setValorTotal(df.parse(s.getValor()).doubleValue());
										
											PrazoResponse prazo = new PrazoResponse();
											prazo.setEntregaDomiciliar(s.getEntregaDomiciliar() == "S" ? true : false);
											prazo.setEntregaSabado(s.getEntregaSabado() == "S" ? true : false);
											prazo.setPrazoEntrega(Integer.parseInt(s.getPrazoEntrega()));
											
											return CotacaoResponseFactory.newCotacaoPrecoPrazoResponse(String.valueOf(s.getCodigo()), preco, prazo);
										} catch (ParseException e) {
											throw new RuntimeException("Invalid value");
										}
									 }
						));
		
		return result;
	}
	
	/**
	 * Validar se resposta tem erro.
	 * 
	 * @param respostaServico resposta de WS
	 * @param classeDeResposta Classe de resposta
	 * @return T classe gerada
	 * @throws RuntimeException
	 */
	private <T extends CotacaoResponse> T validarErro(CServico respostaServico, Class<T> classeDeResposta) throws RuntimeException {
		if (StringUtils.isEmpty(respostaServico.getErro()) || !StringUtils.equalsIgnoreCase(respostaServico.getErro(), "0")) {
			return CotacaoResponseFactory.newErroCotacao(String.valueOf(respostaServico.getCodigo()), respostaServico.getErro(), respostaServico.getMsgErro(), classeDeResposta);
		} else {
			return null;
		}
	}
	
}
