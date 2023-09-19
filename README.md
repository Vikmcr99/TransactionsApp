Descrição da atividade: 

"Você tem a tarefa de implementar um aplicativo que autoriza uma transação (cartão de crédito) para uma conta especifica após um conjunto de regras predefinidas

Operações: 
O programa lida com dois tipos de operações, decidindo qual de acordocom a linha que está sendo processada:
1. Criação de conta
2. Autorização de uma transação

1. Criação de conta:
Cria a conta com "availableLimit" e "activeCard" definidos.
Uma vez criada, a conta não deve ser atualizada ou recriada

2. Autorização de transação
Tenta autorizar uma transação para um determinado comerciante, quantidade e tempo dado, o estado da conta e o ultimo autorizado

Violações da lógica de negocios:
Voce deve implementar as seguintes regras, tendo em mente que novas regras aparecerão no futuro:
O valor da transação não deve exceder o limite disponivel: Limite insuficiente
Nenhuma transação deve ser aceita quando o cartão não está ativo
Não deve haver mais de 3 transações em um intervalo de 2 minutos.
Não deve haver mais de 2 transações semelhantes"

Nesse projeto utilizei as melhores praticas de programação: Testes unitários, testes de carga, Documentação da API com Swagger, Validação das operações com o POSTMAN, SONARCLOUD.
O projeto foi feito em JAVA. 
Apenas Back-End.

Espero que gostem!
