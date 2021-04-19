# SincronizacaoReceitaFederal
desafio avenuecode-sicredi


Para executar a aplicação conforme escrito na especificação:
Exemplo testado em minha maquina Sistema Operacional Windows;
Comando-> java -jar SincronizacaoReceitaFederal-0.0.1-SNAPSHOT.jar  C:\\Users\\gabriels\\Downloads\\dados.csv,C:\\Users\\gabriels\\Downloads\\dadosAtualizado.csv

note que é passado  dois caminhos no input.
	
 O primeiro caminho é para buscar o .csv de origem -> C:\\Users\\gabriels\\Downloads\\dados.csv
 O segundo caminho é para destinar o novo .csv contendo o novo campo com a resposta do Serviço da Receita -> C:\\Users\\gabriels\\Downloads\\dadosAtualizado.csv
 Atenção-> devem ser separados por virgula, formando a seguinte String: C:\\Users\\gabriels\\Downloads\\dados.csv,C:\\Users\\gabriels\\Downloads\\dadosAtualizado.csv

 O Serviço retorna TRUE ou FALSE, fiz uma logica para que em caso de TRUE preencha o .CSV  com SUCESSO e FALSE com FALHA.


Em caso de duvidas estou a disposição.
