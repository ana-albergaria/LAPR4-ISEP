# 3002 - "As Sales Manager, I want to get a statistical report regarding a previously set up questionnaire."



# 1. Requisitos


**UC3002:** Como Sales Manager, pretendo gerar um relatório estatístico relativo a um questionário previamente configurado.

A interpretação feita deste requisito foi no sentido de permitir ao sales manager visualizar relatórios estatísticos dos questionários que existem no sistema.


# 2. Análise

## 2.1. Respostas do Cliente

>Q1: 
>
>A1: 



## 2.2. Regras de Negócio

* "The report should be generated considering the given questionnaire and the set of answers obtained until the current moment."


# 3. Design

## 3.1. Realização da Funcionalidade


### 3.1.1. Modelo de Domínio:

![US_3002_DM](US_3002_DM.svg)


### 3.1.2. Classes de Domínio:

Controlador: GenerateReportsController


### 3.1.3. Diagrama de Sequência do Sistema:

![US_3002_SSD](US_3002_SSD.svg)


### 3.1.4. Diagrama de Sequência:

![US_3002_SD](US_3002_SD.svg)


## 3.2. Diagrama de Classes

![US_3002_CD](US_3002_CD.svg)



# 4. Implementação

## 4.1. Classe ...




# 3. Integração/Demonstração

Esta User Story depende da User Story 3001 e 3501, uma vez que é necessária a existência de questionários, que por sua vez podem ser respondidos por clientes, para que possam ser gerados relatórios com os resultados obtidos.

# 4. Observações

n/a