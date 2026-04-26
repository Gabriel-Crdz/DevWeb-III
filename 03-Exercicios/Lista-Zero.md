**1\. Quais protocolos (os principais) são usados em uma comunicação realizada**  
**na web? Descreva muito brevemente o papel de cada um deles.**  
R:  
HTTP (HyperText Transfer Protocol): Responsável pela comunicação entre cliente (navegador) e servidor. Define como as requisições e respostas são feitas.  
HTTPS (HTTP Secure): Versão segura do HTTP, usa criptografía (TLS/SSL) para proteger os dados.  
TCP (Transmission Control Protocol): Garante que os dados sejam entregues corretamente e na ordem certa.  
IP (Internet Protocol): Responsável por identificar os dispositivos na rede e encaminhar os pacotes.  
DNS (Domain Name System): Traduz nomes de domínio (ex: google.com) para o endereços IP.

**2\. Alguns autores usam o termo “arquitetura da web” para se referir a como as**  
**camadas tecnológicas da web estão organizadas e aos princípios que**  
**definem a troca de informações entre essas camadas. Por questões**  
**didáticas e de simplificação, convencionou-se chamar essa arquitetura de**  
**“arquitetura cliente x servidor” ou arquitetura “requisição x resposta”.**  
**Explique de forma simplificada como funciona essa arquitetura.**  
R:  
O cliente(navegador) envia uma requisição(solicita um determinado recurso) para o servidor, o servidor processa a requisição e envia uma resposta(devolvendo o  
recurso solicitado) de volta para o cliente.

**3\. Em uma arquitetura web, qual o papel desempenhado pelo protocolo HTTP?**  
R:  
Define uma série de interfaces para requisição e resposta.  A requisição é padronizada por meio de verbos. A resposta é padronizada por meio de código de resposta ou códigos de estados.

**4\. O HTTP possui padrões uniformes para requisição e para resposta.**  
**a) Dê ao menos três exemplos métodos de requisição e suas**  
**características;**  
R:  
GET: utilizado para solicitar dados do servidor.  
POST: envia dados do cliente ao servidor.  
PUT: atualiza dados existentes no servidor.

**b) Dê ao menos três exemplos status de respostas e quando ocorrem;**  
R:  
200 OK: Requisição bem-sucedida.  
404 Not Found: Recurso não encontrado.  
500 Internal Server Error: Erro no servidor.

**5\. Em uma arquitetura web é sempre o cliente quem inicia o processo de**  
**comunicação: o cliente requisita, o servidor responde. Contudo, aplicações**  
**web como Gmail ou Instagram, “empurram” informações novas ao cliente,**  
**tais como um novo e-mail ou uma “curtida” em uma determinada**  
**publicação. Hipoteticamente, quais estratégias poderiam ser empregadas**  
**para se chegar a esse resultado?**  
R:  
WebSocket: O cliente abre uma conexão persistente com o servidor, depois disso, ambos podem enviar dados a qualquer momento.  
Polling: O cliente fica “perguntando” ao servidor em intervalos regulares, se existe algo novo.

---

## SOBRE O FRAMEWORK SPRING
**O Spring Framework é um framework Java que fornece uma infraestrutura**  
**completa para o desenvolvimento de aplicações, especialmente aplicações web**  
**e corporativas, com foco em: i) Baixo acoplamento; ii) Alta coesão; ii)**  
**Organização da arquitetura**

**6\. Em uma aplicação web baseada no Spring MVC, o desenvolvedor cria**  
**classes anotadas com @Controller (ou @RestController no futuro) e define**  
**métodos para responder a URLs específicas. Entretanto, quando um usuário**  
**acessa uma URL no navegador (por exemplo, digitando /produtos), o**  
**navegador não “chama diretamente” o método do controller. Faça uma**  
**pesquisa e considerando a arquitetura do Spring MVC explique como o**  
**Spring consegue interceptar a requisição HTTP, decidir qual controller e**  
**qual método devem ser executados e, por fim, gerar a resposta ao cliente.**  
R:  
Quando o usuário acessa **`/produtos`**, o navegador envia uma requisição HTTP para o servidor, ela chega em um componente do Spring chamado: **DispatcherServlet,** ele recebe a requisição HTTP e decide quem deve processar. DispatcherServlet pergunta: “Qual controller e método lidam com **`/produtos`**?”, Para isso, ele usa: **HandlerMapping**, ele analisa as anotações como **`@RequestMapping`**, **`@GetMapping`**, etc., Mapeando a URL para um método específico. Agora que o Spring sabe qual método chamar ele usa, o componente **HandlerAdapter** que invoca o método correto, resolve parâmetros e faz conversões. Depois do Controller processar as informações, depois de tudo isso: DispatcherServlet recebe o resultado final e envia a resposta HTTP de volta ao cliente

**7\. Em Java, é comum encontrarmos códigos que utilizam anotações como**  
**@Override, @Deprecated ou @SuppressWarnings. Diferentemente de**  
**comentários, essas marcações não servem apenas para documentação, mas**  
**podem influenciar o comportamento do compilador, das ferramentas e até**  
**da execução do programa. Considerando esse contexto, explique o que são**  
**anotações em Java e qual é o seu papel no desenvolvimento de aplicações**  
**modernas.**  
R:  
Anotações são metadados adicionados ao código que fornecem informações extras sobre classes, métodos, atributos ou parâmetros, sem alterar diretamente a lógica do programa. Diferente de comentários, que são ignorados pelo compilador, as anotações podem ser interpretadas pelo próprio compilador, por ferramentas de desenvolvimento ou até em tempo de execução, dependendo de como foram definidas.

**8\. Em aplicações baseadas no Spring Framework, é comum criar classes**  
**simples, sem código explícito de configuração, apenas utilizando anotações**  
**como @Component, @Controller, @Service ou @Repository. Mesmo assim,**  
**o Spring consegue instanciar objetos, injetar dependências e organizar a**  
**aplicação automaticamente. Considerando esse cenário, explique como as**  
**anotações do Spring permitem que o framework identifique, gerencie e**  
**conecte os componentes da aplicação sem que o desenvolvedor precise**  
**criar objetos manualmente.**  
R:  
Anotações funcionam como marcadores que indicam ao container do Spring quais classes devem ser tratadas como componentes gerenciados. Durante a inicialização da aplicação, o Spring realiza um processo chamado *component scanning*, no qual ele percorre os pacotes definidos e identifica automaticamente essas classes anotadas. A partir disso, ele cria e armazena instâncias desses objetos em um contexto central chamado *ApplicationContext*. Além disso, por meio do princípio de *Inversão de Controle (IoC)* e *Injeção de Dependência (DI)*, o framework é responsável por instanciar e conectar os objetos entre si, geralmente usando anotações como **`@Autowired`** para resolver dependências automaticamente. 