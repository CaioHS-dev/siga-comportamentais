package siga;

public class DescontoBolsista implements PoliticaDesconto {
    @Override
    public double aplicar(double valorBase) {
        return valorBase * 0.50;
    }

    @Override
    public String getDescricao() {
        return "Desconto de Bolsista (50%)";
    }
}