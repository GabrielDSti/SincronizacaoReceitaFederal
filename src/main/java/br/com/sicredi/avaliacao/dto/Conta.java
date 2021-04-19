package br.com.sicredi.avaliacao.dto;

import java.math.BigDecimal;
import java.util.List;

public class Conta {
	private String agencia;
	private String numeroConta;
	private String saldo;
	private String status;
	private Boolean statusEnvioReceita;
	
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String conta) {
		this.numeroConta = conta;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getStatusEnvioReceita() {
		return statusEnvioReceita;
	}
	public void setStatusEnvioReceita(Boolean statusEnvioReceita) {
		this.statusEnvioReceita = statusEnvioReceita;
	}
	@Override
	public String toString() {
		return "Conta [agencia=" + agencia + ", numeroConta=" + numeroConta + ", saldo=" + saldo + ", status=" + status
				+ ", statusEnvioReceita=" + statusEnvioReceita + "]";
	}
	
	
	
	
	
	
	

}
