# Documentação de Refatoração: Padrões Strategy e Observer (SIGA)

## 1. Correção das Regras de Desconto (Padrão Strategy)

* O método `calcularValor` em `Matricula` e o método `calcularValorDoBoleto` em `GeradorBoleto` aplicavam os descontos baseados no perfil do aluno usando estruturas condicionais (`if/else`). Além da repetição de código, havia uma divergência: a classe de matrícula aplicava 10% de desconto para egressos, enquanto o gerador de boletos parava no desconto de servidor. Isso causou um erro financeiro, fazendo com que a aluna Ana Pereira pagasse R$ 720,00 na matrícula e R$ 800,00 no boleto.

*Para eliminar os `ifs` e centralizar os cálculos, implementei o padrão **Strategy**:
1. Criei a interface `PoliticaDesconto` com o contrato `double aplicar(double valorBase)` e `String getDescricao()`.
2. Criei quatro classes dedicadas (`SemDesconto`, `DescontoBolsista`, `DescontoServidor` e `DescontoEgresso`), cada uma encapsulando a fatia matemática correspondente (ex: multiplicando por 0.50, 0.80 ou 0.90).
3. Na classe `Matricula`, apaguei todas as condicionais, passei a injetar a política via construtor e apenas deleguei o cálculo: `politica.aplicar(valorBase)`.
4. Na classe `GeradorBoleto`, removi o cálculo manual e passei a receber a instância de `Matricula`, chamando o método de cálculo nela.

* Os percentuais de desconto agora existem em apenas um lugar do projeto, blindando-o contra divergências. Qualquer alteração ou nova política de desconto será feita sem usar a palavra `if` e afetará automaticamente tanto a matrícula quanto o boleto.

---

## 2. Desacoplamento das Reações à Matrícula (Padrão Observer)

* A classe `FachadaMatricula` estava acoplada intimamente a quatro serviços diferentes (e-mail, vagas, auditoria e aviso à coordenação). Após registrar o aluno, ela os chamava de forma sequencial. O problema crítico aqui era a falta de proteção: como a falha do aviso à coordenação disparava uma exceção, o processo inteiro travava antes da etapa final, impedindo a auditoria de ser registrada. 

* Para desvincular a fachada de seus efeitos colaterais e blindar o fluxo, implementei o padrão **Observer**:
1. Criei a interface `OuvinteMatricula` contendo o método `aoMatricular(Matricula)`.
2. Adaptei os serviços (`NotificadorEmail`, `PainelVagas`, `RegistroAuditoria` e `AvisoCoordenacao`) para implementarem essa interface.
3. Criei a classe `PublicadorMatricula` com uma lista interna para registrar os ouvintes. No método de disparo (`publicar`), iterei sobre uma **cópia** da lista e envolvi cada execução num bloco `try-catch` que apenas registra o erro e não interrompe o laço.
4. Refatorei a `FachadaMatricula` para receber apenas o `PublicadorMatricula` no construtor. Após a matrícula, ela invoca apenas `publicador.publicar(nova)`.

* Criei uma quinta reação para estatísticas de ocupação. Inseri a classe no projeto registrando-a no publicador (no `Main`), e comprovei que é possível adicionar um serviço novo sem tocar numa única linha de código da fachada.

* A fachada voltou a ter a sua responsabilidade única (orquestrar os passos antes e durante a matrícula). As reações após a matrícula agora são tratadas como eventos independentes, isolados e resilientes a falhas de terceiros.