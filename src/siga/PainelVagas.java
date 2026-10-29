package siga;

/** Reação à matrícula. Pronta. */
public class PainelVagas implements OuvinteMatricula {
    @Override
    public void aoMatricular(Matricula matricula) {
        System.out.println("   [painel] vagas de " + matricula.getCodigoTurma() + " atualizadas");
    }
}
