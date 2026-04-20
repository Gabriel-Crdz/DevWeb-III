# Lista de exercicios 2

## 1. Crie um projeto Java Web

## 2. Controllers

### Questão A:
Implementação de um Controller que responda à rota principal (/):
Nesse caso, o método retorna uma view chamada home, que pode ser um arquivo home.html
localizado na pasta src/main/resources/templates

### Questão B:
O tratamento de erros personalizado: 
Para o erro 404 (página nãoencontrada), 
Foi implementado um arquivo chamado 404.html dentro do diretório:
src/main/resources/templates/error
Ao acessar uma URL inválida, essa página será exibida automaticamente.

### Questão C:
Ciclo de vida da aplicação
implementação do LifeCycleController utilizando as anotações especificadas:
* **@PostConstruct:** Usada para criar métodos que o Spring chamará automaticamente
* **@PreDestroy:** Usada para marcar um método que deve ser executado imediatamente antes de um bean (um objeto) ser removido do contêiner Spring

#### Respostas

* **i) Quantas vezes apareceu a mensagem “Chamou o método init”?** 
    - A mensagem aparece 1 vez, durante a inicialização da aplicação.

* **ii) Quantas vezes apareceu a mensagem “Chamou o método service”?**  
    - A mensagem aparece toda vez que a rota `/lifecycle` é chamada.

* **iii) Quantas vezes apareceu a mensagem “Chamou o método destroy”?**  
    - A mensagem aparece 1 vez, quando a aplicação é encerrada.

* **iv) Atualizando o navegador (F5), existe algum efeito?**  
    - Sim. a cada atualização o metodo `/service` é chamado novamente.

### Questão D:
Implementação de um Controller responsável por contabilizar o número de acessos
Ao acessar a rota `/contador` o contador incremental 1 na variavel mostrada.

#### Respostas

* **i) Atributos de classe podem ser compartilhados entre diferentes requisições dentro da aplicação. Em quais situações isso pode ser útil?**
    - são úteis quando você precisa armazenar dados que devem ser consistentes e acessíveis por todas as instâncias e requisições da aplicação, sem precisar recriá-los a cada vez.
    Ex: conexão com banco de dados, contagem de requisições processadas.

* **ii) Na sua opinião, essa abordagem é adequada para sistemas com múltiplos usuários acessando o sistema simultaneamente?**
    - Não, o uso de atributos de classe introduz o risco de Race Conditions (condições de corrida), onde duas requisições tentam alterar o mesmo dado ao mesmo tempo, causando comportamentos imprevisíveis, duplicação de dados ou erros.

* **ii) As informações de uma requisição não são compartilhadas. Dizemos que são “Thread Safe”. Explique a importância desse comportamento.**
    -  Garante que a aplicação funcione corretamente quando acessado por múltiplas threads simultaneamente, impedindo race conditions (concorrência desordenada) e garantindo a integridade dos recursos compartilhados.