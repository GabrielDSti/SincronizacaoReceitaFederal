package br.com.sicredi.avaliacao.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;

import br.com.sicredi.avaliacao.dto.Conta;
import br.com.sicredi.avaliacao.util.Utils;

public class CSV {
	 
	private  String CSV_PATH_SAIDA; 
	private static Logger log = LoggerFactory.getLogger(CSV.class);


	public List<Conta> lerCsv(String caminhoEntrada,String caminhoSaida) {
		CSV_PATH_SAIDA = caminhoSaida;
		long msInicial =ZonedDateTime.now().toInstant().toEpochMilli();
		List<Conta> atributosCsv = new ArrayList<Conta>();
		log.info("Validando extensão de arquivo...");
		if(FilenameUtils.getExtension(caminhoEntrada).equalsIgnoreCase("csv")) {
		try {
			log.info("Lendo CSV de Entrada...");
			FileInputStream caminhoArquivo = new FileInputStream(caminhoEntrada);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(caminhoArquivo));
			List<String> lines = buffer.lines().collect(Collectors.toList());
			lines.remove(0);
			for (String line : lines) {
				line = Utils.formatarLineCsv(line);
				String[] itens = line.split(";");
				Conta conta = new Conta();
				conta.setAgencia(itens[0]);
				conta.setNumeroConta(Utils.formatarNumeroConta(itens[1]));
				conta.setSaldo(Utils.formatarSaldo(itens[2]));
				conta.setStatus(itens[3]);
				
				atributosCsv.add(conta);
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long msFinal =ZonedDateTime.now().toInstant().toEpochMilli();
		log.info((msFinal-msInicial)+"ms:");
		}else {
			log.error("Extensão de arquivo invalido.");
		}
		return atributosCsv;
	}
	
	public  void gerarCsv(List<Conta> dadosCsv)  {
		List<String[]> linhas = new ArrayList<>();
		log.info("Escrevendo CSV de Saida...");
		for(Conta linha: dadosCsv ) {
			linhas.add(new String[] {linha.getAgencia(),Utils.reverterFormatarNumeroConta(linha.getNumeroConta()),linha.getSaldo(),linha.getStatus(),Utils.formatarResposta(linha.getStatusEnvioReceita())});
			log.info(linha.toString());

		}
		log.info("Formatando dados para o padrao do CSV...");
		String[] cabecalho = {"agencia", "conta", "saldo", "status", "resposta_receita"};
		try {
			FileWriter escrever = new FileWriter(new File(CSV_PATH_SAIDA));
			log.info("Gerando CSV no destino:"+ CSV_PATH_SAIDA);
			
		
			CSVWriter csv = new CSVWriter(escrever,';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
			csv.writeNext(cabecalho);
			csv.writeAll(linhas);
			csv.flush();
			escrever.close();
			log.info("Processamento finalizado.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
