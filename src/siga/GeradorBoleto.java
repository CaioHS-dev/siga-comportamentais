package siga;

/**
 * DESLIZE 1 (parte B) — a MESMA cadeia de condicionais, escrita de novo.
 *
 * E já divergente: quando a política do egresso foi criada, ninguém
 * atualizou este trecho. O mesmo aluno recebe um valor na matrícula e
 * outro no boleto.
 */
public class GeradorBoleto {

    public void emitir(Matricula matricula, double valorBase) {
        // Agora o boleto extrai o valor exato da matrícula, eliminando o Deslize 1
        double valor = matricula.calcularValor(valorBase);
        System.out.printf("   [boleto] %s: R$ %.2f%n", matricula.getAluno().getNome(), valor);
    }
}