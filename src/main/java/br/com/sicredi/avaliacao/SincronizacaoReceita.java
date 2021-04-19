package br.com.sicredi.avaliacao;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sicredi.avaliacao.domain.CSV;
import br.com.sicredi.avaliacao.dto.Conta;
import br.com.sicredi.avaliacao.service.ReceitaService;


/*
Cenário de Negócio:
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de 
contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi 
já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, 
antes as 10:00 da manhã na abertura das agências.

Requisito:
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

Funcionalidade:
0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma 
nova coluna.


Formato CSV:
agencia;conta;saldo;status
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

*/

@SpringBootApplication
public class SincronizacaoReceita {
	
	
	private static Logger log = LoggerFactory.getLogger(SincronizacaoReceita.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SincronizacaoReceita.class, args);
		String[] path;
		if(args.length > 0) {
		 path = args[0].toString().split(",");
		log.info("path entrada:"+ path[0]);
		log.info("path saida:"+ path[1]);
		
		CSV csv = new CSV();
		List<Conta> dadosEnviados = new ArrayList<>();
     try {
    	
    	 long msInicial =ZonedDateTime.now().toInstant().toEpochMilli();
    	 for(Conta conta : csv.lerCsv(path[0],path[1])) {
    		
    	 // Exemplo como chamar o "serviço" do Banco Central.
    	ReceitaService receitaService = new ReceitaService();
		Boolean retornoService = receitaService.atualizarConta(conta.getAgencia(), conta.getNumeroConta(), Double.valueOf(conta.getSaldo()), conta.getStatus());
    	conta.setStatusEnvioReceita(retornoService);
		dadosEnviados.add(conta);
    	 }
    	 long msFinal =ZonedDateTime.now().toInstant().toEpochMilli();
    	 log.info((msFinal-msInicial)+"ms: total");
    	 csv.gerarCsv(dadosEnviados);
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		}else {
			log.info(">>>>>>path do .csv não informado.<<<<<<");
			log.info("Exemplo de  inicializacao no Windows->");
			log.info("java -jar SincronizacaoReceitaFederal-0.0.1-SNAPSHOT.jar  C:\\\\Users\\\\gabriels\\\\Downloads\\\\dados.csv,C:\\\\Users\\\\gabriels\\\\Downloads\\\\dadosAtualizado.csv\n" + 
					"");
			System.err.println("passo a passo para executar o projeto na pasta INSTRUÇÕES dentro do projeto");
		}

}
	
	
}
