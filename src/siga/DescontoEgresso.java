package siga;

public class DescontoEgresso implements PoliticaDesconto {
    @Override
    public double aplicar(double valorBase) {
        return valorBase * 0.90;
    }

    @Override
    public String getDescricao() {
        return "Desconto de Egresso (10%)";
    }
}