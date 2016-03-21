/**
 * Sparta Software Co.
 * 2016
 */
package org.sparta.correiosshippingestimator.constant;

/**
 * @author Daniel Conde Diehl
 *
 */
public enum FormatoEncomenda {
	CAIXA(1, true),
	ROLO(2, false),
	ENVELOPE(3, false);
	
	private int codigo;
	private boolean permiteCalculoVolume;
	private FormatoEncomenda(int codigo, boolean permiteCalculoVolume) {
		this.codigo = codigo;
		this.permiteCalculoVolume = permiteCalculoVolume;
	}
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @return the permiteCalculoVolume
	 */
	public boolean isPermiteCalculoVolume() {
		return permiteCalculoVolume;
	}
	
}
