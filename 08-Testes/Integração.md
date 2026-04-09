# Testes de Software

## Introdução

* Por que testar?
    - Reduz bugs;
    - Melhora a manutenção;
    - Aumenta a confiabilidade;
    - Otimiza o tempo

---

## Testes de Integração
Testes de integração verificam se diferentes partes de um sistema funcionam corretamente quando
combinadas;

Em vez de testar apenas uma classe ou método isolado (como nos testes unitários), eles testam a
interação entre componentes, como serviços, repositórios, controladores e banco de dados;

O objetivo desse tipo de teste é garantir que os módulos funcionam bem juntos e que a aplicação está
coerente como um todo, mesmo com dependências externas envolvidas;
* Testes de Integração:
    - Integram múltiplos componentes reais (às vezes, simulados).
    - Podem acessar banco de dados, APIs, arquivos, etc.
    - Costumam ser mais lentos do que testes unitários;
    - Úteis para verificar problemas de configuração e integração

Ao abrir uma determinada rota e verificar seus resultados, estamos realizando um tipo de teste de
integração (desde que envolva a interação entre dois ou mais componentes da aplicação);

Contudo esse tipo de teste é lente, cansativo e suscetível a erros.
Podemos automatizar esse processo;

Com a biblioteca MockMvc do Spring Framework, podemos simular o a abertura de links e o envio de
formulários.

Por rodar um servidor, é necessário que algumas configurações sejam realizadas na classe de Testes
antes da realização dos teste.

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockRequest;
    //...
}
```

```java
@Test
void deveRetornarInformacaoFilmes() throws Exception {  
    mockRequest.perform(get("/filmes"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Filme 1")));
}
```

```java
@Test
void deveRetornarInformacaoFilmes() throws Exception {
    mockRequest.perform(get("/filmes"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Filme 1")));
}
```

```java
@Test
void deveRetornarViewComMensagem() throws Exception {
    mockRequest.perform(get("/rota_a_ser_testada"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("mensagem"))
        .andExpect(model().attribute("mensagem", "Bem-vindo!"))
        .andExpect(view().name("nome-da-view-esperada"));
}
```

```java
void deveCadastrarComSucessoERedirecionar() throws Exception {
mockRequest.perform(post("/rota_a_ser_testada")
.param("nome", "João")
.param("matricula", "12181100")
.param("CPF", "068.955.389-76")
.param("dataNascimento", "1989-04-26"))
.andExpect(status().is3xxRedirection())
.andExpect(redirectedUrl("/sucesso"));
}
``

```java
@Test
void deveProcessarFormulario() throws Exception {
    MockMultipartFile mockImagem = new MockMultipartFile(
        "avatar",
        "imagem_teste.png",
    MediaType.IMAGE_JPEG_VALUE,
    "conteúdo de uma imagem".getBytes()
    );

    mockRequest.perform(multipart("/estudante")
        .file(mockImagem)
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/sucesso"));
    )}
```
