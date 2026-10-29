package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * DESLIZE 2 — a fachada conhece TODAS as reações a uma matrícula,
 * e a lista cresce a cada pedido da secretaria.
 *
 * A orquestração dos PASSOS (validar, gravar) é papel legítimo da fachada,
 * estabelecido na Aula 10 — isso não deve ser desfeito. O problema é a
 * lista de REAÇÕES ao fato já consumado: a quinta reação será a quinta
 * alteração nesta classe, que nada tem a ver com auditoria nem com painel.
 *
 * Repare também que a falha do AvisoCoordenacao derruba tudo o que viria
 * depois dele.
 */
public class FachadaMatricula {

    private final List<Matricula> armazem = new ArrayList<>();
    private final PublicadorMatricula publicador;

    public FachadaMatricula(PublicadorMatricula publicador) {
        this.publicador = publicador;
    }

    public Matricula matricular(Aluno aluno, String codigoTurma, PoliticaDesconto politica) {
        Matricula nova = new Matricula(aluno, codigoTurma, politica);
        armazem.add(nova);

        publicador.publicar(nova);

        return nova;
    }

    public List<Matricula> listar() {
        return new ArrayList<>(armazem);
    }
}
