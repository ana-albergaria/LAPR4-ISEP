# 1005 - "As Sales Clerk, I want to define a new category of products."



# 1. Requisitos


**UC1005:** Como Sales Clerk desejo definir uma nova Categoria de Produtos.

A interpretação feita deste requisito foi a de definir uma nova Categoria de Produtos com informação dada pelo Sales Clerk.

# 2. Análise

## 2.1. Respostas do Cliente

>Q1: "Regarding US1005, is there any rules for the alphanumeric code, or the Sales Clerk chooses what to input."
>
>A1: "- code: not empty alphanumeric code with at 10 chars maximum;
      - description: not empty with a minimum of 20 chars and 50 chars maximum;"

>Q2: "Regarding US1005, when creating a new product category, is it supposed to be able to add products at the moment of the creation? Or just after the category is created?"
>
>A2: "No, it is not supposed to add products while creating the category."

>Q3: "Could you please clarify if it is possible to a category have a reference to another category? (super-category)."
>
>A3: "For now, there is no need of hierarchies on categories."

 
## 2.2. Regras de Negócio

* Code: not empty alphanumeric code with at 10 chars maximum;
* Description: not empty with a minimum of 20 chars and 50 chars maximum;

# 3. Design

*Nesta secção a equipa deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, a equipa deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade.*

*Para além das secções sugeridas, podem ser incluídas outras.*


## 3.1. Realização da Funcionalidade


### 3.1.1. Classes de Domínio:

* WarehousePlant, Aisle, Row, Shelf, AVGDock
* Controlador:
  * SetWarehousePlantController
* Repository:
  * WarehouseRepository


### 3.1.2. Diagrama de Sequência do Sistema:

![US_1001_SSD](US_1001_SSD.svg)


### 3.1.3. Diagrama de Sequência:

![US_1001_SD](US_1001_SD.svg)



## 3.2. Diagrama de Classes


![US_1001_CD](US_1001_CD.svg)


## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Testes
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível configurar a planta com um ficheiro incorreto.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 2:** Verificar que não é possível configurar a planta com uma Length nula.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 3:** Verificar que não é possível configurar a planta com uma Width nula.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 4:** Verificar que não é possível configurar a planta com o Square nulo.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 5:** Verificar que não é possível configurar a planta com a Unit nula.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 6:** Verificar que não é possível configurar a planta com a Aisle Accessibility nula.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 7:** Verificar que não é possível configurar a planta com uma Length menor que zero.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 8:** Verificar que não é possível configurar a planta com uma Width menor que zero.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 9:** Verificar que não é possível configurar a planta com o Square menor que zero.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 10:** Verificar que não é possível configurar a planta com a Unit menor que zero.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

**Teste 11:** Verificar que não é possível configurar a planta com a Aisle Accessibility com mais de 2 chars.

	@Test(expected = IllegalArgumentException.class)
        //to develop
    }

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*



