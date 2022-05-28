# 5001 - "As Project Manager,  I want that the team start developing the output communication module of the AGV digital twin to update its status on the "AGVManager"."



# 1. Requisitos


**UC5002:** Como Project Manager pretendo que a equipa comece a desenvolver o modúlo de comunicação por output do AGV digital twin, para atualizar o seu status no "AGVManager".

A interpretação feita deste requisito foi no sentido de desenvolver o AGV digital twin, permitindo enviar informação sobre o seu status atual (from the system specification document: "it can send information about its current [...] status").


# 2. Análise

## 2.1. Respostas do Cliente

>Q1: Regarding the USs 1901,4001, 5001, and 5002, what would you consider its complete state, that is, what would be the criterion to define whether or not this US is functional?
>
>A1: For all of those US, the communication between the two involved components must be implemented in accordance with the SPOMS2022. The requests processing can be somehow mocked. For instance, if processing a request implies saving some data to the database, the component can instead write such data to a log (mocking). Latter, on next sprint, the teams implement the interaction to the database. However, it is not advisable mocking everything, namely the components (internal) state. Notice that by mocking you are letting extra effort to the next sprint. Finally, all US must be demonstrable.

>Q2: What type of communication do you want, i.e., what are the communications that you want to have between these two. Like AGV Manager says "Take a product" and AGV Digital Twin says "Taken"? Is it something like that? Or am i confused?
> 
>A2: The communication must follow the SPOMS2022 protocol. It is up to you and your team to devise a set of messages fulfilling the business requirements properly.

## 2.2. Regras de Negócio

* Deve ser utilizado o protocolo de aplicação fornecido (SPOMS2022).
* Sugere-se a adoção de mecanismos concorrentes (por exemplo, threads) e compartilhamento de estados entre esses mecanismos.
* Neste sprint, para fins de demonstração, é aceitável simular o processamento de algumas das solicitações recebidas para promover alguma comunicação de saída.


# 3. Design



## 3.1. Realização da Funcionalidade


### 3.1.1. Modelo de Domínio:

![US_5002_DM](US_5002_DM.svg)


### 3.1.2. Classes de Domínio:

* 


### 3.1.3. Diagrama de Sequência do Sistema:

![US_5002_SSD](US_5002_SSD.svg)


### 3.1.4. Diagrama de Sequência:

![US_5002_SD](US_5002_SD.svg)



## 3.2. Diagrama de Classes


![US_5002_CD](US_5002_CD.svg)


## 3.3. Padrões Aplicados




# 4. Implementação

## 4.1. Classe 


    [...]

    
    
    [...]


    


# 5. Integração/Demonstração

Esta User Story depende da User Story 4001, uma vez que é necessária a existência do AGVManager para que esta US funcione do modo pretendido.

# 6. Observações

Uma vez que para esta US não é criada qualquer tipo de entidade, não foi criada nenhuma classe de testes.