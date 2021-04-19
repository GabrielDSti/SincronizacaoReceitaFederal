package br.com.sicredi.avaliacao.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	public static  String formatarNumeroConta(String numeroConta) {
		String numeroContaFormatado = numeroConta.replace("-", "");
		return numeroContaFormatado;
	}
	
	public static  String reverterFormatarNumeroConta(String numeroConta) {
		String numeroContaFormatado = numeroConta.substring(0, 5)+"-"+numeroConta.substring(5,numeroConta.length());
		return numeroContaFormatado;
	}
	
	public static  String formatarSaldo(String saldo) {
		String saldoFormatado = saldo.replace(",", ".");
		return saldoFormatado;
	}
	
	public static  String reverterFormatarSaldo(String saldo) {
		Double val = Double.parseDouble(saldo);
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String saldoFormatado =  decimalFormat.format(val);
		return saldoFormatado;
	}
	
	public static String formatarLineCsv(String linha) {
		return linha.replace("\"", "");
		
	}
	
	public static String formatarResposta(Boolean isBoolean) {
		return (isBoolean.toString().equalsIgnoreCase("TRUE") ? "SUCESSO" : "FALHOU");
	}

}
