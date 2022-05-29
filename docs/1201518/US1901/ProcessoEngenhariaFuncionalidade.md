# US1901 - As Project Manager, I want that the "OrdersServer" component supports properly, at request, the needs of the "CustomerApp" application.
=======================================


# 1. Requisitos

US1901 - As Project Manager, I want that the "OrdersServer" component supports properly, at request, the needs of the "CustomerApp" application.

# 2. Dados do Servidor da Order

| Servidor        | Port  |
|--------------|-------|
| 192.168.1.5    | 10000 |

# 3. Fluxo de Troca de Mensagens entre o Servidor e o Cliente

* Sempre que o cliente solicita um request, através de uma conexão TCP, segue-se o seguinte fluxo de comunicação:

**1.** Espera pela mensagem do Cliente com o Código de Teste (0).  
**2.** Manda ao Cliente o Código de Entendido (2).
**3.** Cliente solicita o request desejado através do seu código associado  
**4.** Socket aguarda mensagem do client e verifica qual o request a executar através do código

<-----------------------Execução do Request----------------------->

* Para terminar o request, o servidor:

**5.** Espera pela mensagem do Cliente com o Código de Fim (1).  
**6.** Manda ao Cliente o Código de Entendido (2).  
**7.** Fecha o Socket.  

# 4. Requests

No âmbito desta US, são efetuados os seguintes requests:

| Código | Request  |
|--------|-------|
| 4      | Mostrar Catálogo de Produtos |
| 3      | Verificar se o Produto desejado existe |
| 5      | Adicionar o Produto ao Carrinho de Compras|







