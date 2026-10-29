package siga;

import java.util.ArrayList;
import java.util.List;

public class PublicadorMatricula {

    private final List<OuvinteMatricula> ouvintes = new ArrayList<>();

    public void registrar(OuvinteMatricula ouvinte) {
        ouvintes.add(ouvinte);
    }

    public void remover(OuvinteMatricula ouvinte) {
        ouvintes.remove(ouvinte);
    }

    public void publicar(Matricula matricula) {
        // Cópia da lista para evitar ConcurrentModificationException
        List<OuvinteMatricula> copia = new ArrayList<>(ouvintes);
        
        for (OuvinteMatricula ouvinte : copia) {
            try {
                ouvinte.aoMatricular(matricula);
            } catch (Exception e) {
                // Falhas individuais são registradas, mas não interrompem o laço (Deslize 2 corrigido)
                System.out.println("   [erro no ouvinte] " + e.getMessage());
            }
        }
    }
}