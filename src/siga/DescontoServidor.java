package siga;

public class DescontoServidor implements PoliticaDesconto {
    @Override
    public double aplicar(double valorBase) {
        return valorBase * 0.80;
    }

    @Override
    public String getDescricao() {
        return "Desconto de Servidor (20%)";
    }
}