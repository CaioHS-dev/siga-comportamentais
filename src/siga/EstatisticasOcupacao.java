package siga;

public class EstatisticasOcupacao implements OuvinteMatricula {
    @Override
    public void aoMatricular(Matricula matricula) {
        System.out.println("   [estatistica] incrementando ocupacao do curso para " + matricula.getCodigoTurma());
    }
}