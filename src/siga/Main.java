package siga;

/**
 * Demonstra os dois deslizes acontecendo. Execute e observe a saída:
 * é dela que partem as etapas da ficha de atividade prática.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Aula 12: Strategy e Observer (Refatorado) ===");

        Aluno bolsista = new Aluno("2024001", "Maria Silva");
        Aluno egresso = new Aluno("2024003", "Ana Pereira");

        double valorBase = 800.00;
        GeradorBoleto gerador = new GeradorBoleto();

        System.out.println("\n1) Valores calculados com padrao Strategy (Matricula e Boleto convergentes)");
        Matricula matBolsista = new Matricula(bolsista, "DSM-2A", new DescontoBolsista());
        System.out.printf("   %-14s matricula: R$ %.2f%n", bolsista.getNome(), matBolsista.calcularValor(valorBase));
        gerador.emitir(matBolsista, valorBase);

        Matricula matEgresso = new Matricula(egresso, "DSM-2A", new DescontoEgresso());
        System.out.printf("   %-14s matricula: R$ %.2f%n", egresso.getNome(), matEgresso.calcularValor(valorBase));
        gerador.emitir(matEgresso, valorBase);


        System.out.println("\n2) Padrao Observer (Reacoes blindadas e quinta reacao adicionada)");
        PublicadorMatricula publicador = new PublicadorMatricula();
        publicador.registrar(new NotificadorEmail());
        publicador.registrar(new PainelVagas());
        publicador.registrar(new AvisoCoordenacao()); // Esta vai falhar
        publicador.registrar(new RegistroAuditoria());
        publicador.registrar(new EstatisticasOcupacao()); // A 5ª reação

        FachadaMatricula fachada = new FachadaMatricula(publicador);

        // Mesmo com a falha do AvisoCoordenacao, a auditoria e a estatística são executadas
        fachada.matricular(egresso, "DSM-2B", new DescontoEgresso());
    }
}
