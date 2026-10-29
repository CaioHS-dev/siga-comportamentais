package siga;

/** Reação à matrícula. Pronta. */
public class RegistroAuditoria implements OuvinteMatricula {
    @Override
    public void aoMatricular(Matricula matricula) {
        System.out.println("   [auditoria] MATRICULA - " + matricula.getAluno().getMatricula());
    }
}
