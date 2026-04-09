# Testes de Software

## Introdução

* Por que testar?
    - Reduz bugs;
    - Melhora a manutenção;
    - Aumenta a confiabilidade;
    - Otimiza o tempo

---

## Testes Unitarios
São testes automatizados que verificam o comportamento de uma unidade individual
de código — **geralmente um único método ou função** — de forma isolada, sem dependências externas.
O objetivo é garantir que cada parte pequena do sistema funcione corretamente por si só.
- Facilita a detecção de bugs rapidamente, pois cada teste cobre uma única responsabilidade.
- Serve como documentação viva do comportamento esperado das unidades.

Testes unitários devem:
* Ser rápidos (milissegundos);
* Independentes;
* Repetitíveis;
* Autoverificáveis;
* Tempo oportuno

### Padrão Triplo A (AAA) 
é uma estrutura muito usada para organizar de forma clara e compreensível:
* **Arrange (Preparar o cenário):** Nesta etapa, você configura o cenário do teste, criando os
objetos necessários, mocks, valores esperados, etc.
* **Act (Agir ou ação):** Você executa o comportamento que está sendo testado, geralmente
chamando o método que deseja validar.
* **Assert (Verificação):** Você verifica se o resultado está correto, comparando o retorno com
o valor esperado ou verificando se uma exceção foi lançada;

- Em pt-br normalmente esse padrão é representado como cenário, ação e validação

#### Verifique se a dependência de testes está incuida no arquivo POM.xml:

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

* Principais ferramentas usadas em testes:
    - **jUnit:** principal framework de testes unitários.
    - **AssertJ / Hamcrest:** para asserções mais legíveis e expressivas.
    - **Mockito:** para criar mocks e spies e isolar a unidade de teste.
    - **Spring Boot Test:** suporte a contexto e MVC
    - **MockMvc:** simula requisições HTTP

###  MOCKS
São objetos simulados usados em testes de software para imitar o comportamento de
dependências reais de uma classe ou método;
São usados principalmente em testes unitários, para isolar o código que está sendo testado e
controlar o ambiente do teste
uando usamos Mock, está sendo criando um objeto falso da classe;
Ou seja, nenhum dos métodos reais da classe será executado, a não ser que você diga
explicitamente o que fazer (com when(...), doAnswer(...), etc.);
* De forma geral, um Mock **representa um objeto vazio da classe**, onde todos os métodos:
    - não têm lógica real;
    - retornam null, 0, ou valores padrão;
    - não executam o código que está na classe original.
Uma alternativa ao uso de **Mocks são os Spies.**

```java
@SpringBootTest
class ValidacaoFormulariosApplicationTests {
    @Mock
    EstudanteService service;

    List<Estudante> listaFake = new ArrayList<>();

    @BeforeTestMethod
    void setUp() {
        when(service.findAll()).thenReturn(listaFake);
    }
}
``
