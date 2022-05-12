# 4002 - "As Project Manager, I want that the "AGVManager" component is enhanced with a basic FIFO algorithm to automatically assign tasks to AGVs."



# 1. Requisitos


**UC4002:** Como Project Manager pretendo que a componente "AGVManager" seja aprimorada com um algoritmo FIFO (first in first out) básico, para atribuir automáticamente tarefas aos AGVs.

A interpretação feita deste requisito foi no sentido de acrescentar o algoritmo pedido ao AGVManager e fazer com que a designação de tasks aos AGVs seja automática.

# 2. Análise

## 2.1. Respostas do Cliente

>Q1: "Will the FIFO algorithm be used to control the tasks/orders that are waiting for an available AGV? If I am interpreting something wrong please clarify for me."
>
>A1: ""

>Q2: "Talking about being automatic, the System executes this functionally after some other functionality, or executes it periodically? If it is periodically, how often?"
>
>A2: ""



## 2.2. Regras de Negócio

* O algoritmo desenvolvido deve ser do tipo FIFO (first in first out)



# 3. Design



## 3.1. Realização da Funcionalidade


### 3.1.1. Modelo de Domínio:

![US_4002_DM](US_4002_DM.svg)


### 3.1.2. Classes de Domínio:

* AGV
* AutonomyStatus
* TaskStatus
* Model
* Controlador:
  * AssignTasksToAGVsController
* Repository:
  * AGVRepository


### 3.1.3. Diagrama de Sequência do Sistema:

![US_4002_SSD](US_4002_SSD.svg)


### 3.1.4. Diagrama de Sequência:

![US_4002_SD](US_4002_SD.svg)



## 3.2. Diagrama de Classes


![US_4002_CD](US_4002_CD.svg)


## 3.3. Padrões Aplicados

Foram aplicados os padrões princípios SOLID e GoF

### Builder


### Creator


### Repository


### Factory


### Information Expert


## 3.4. Testes




# 4. Implementação

## 4.1. Classe ...


    


# 5. Integração/Demonstração

Esta User Story não tem dependências com qualquer outra User Story deste Sprint.

# 6. Observações

