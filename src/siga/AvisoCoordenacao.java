package siga;

/** Reação à matrícula. Pronta. Falha de propósito, para a etapa 4. */
public class AvisoCoordenacao implements OuvinteMatricula {
    @Override
    public void aoMatricular(Matricula matricula) {
        throw new IllegalStateException("servico de aviso indisponivel para " + matricula.getCodigoTurma());
    }
}
