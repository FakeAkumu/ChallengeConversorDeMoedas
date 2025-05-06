package br.com.alura.conversordemoedas.modelo;

import java.util.Map;

public class CurrencyConverter {
    public String convert(int input, double value, Map<String, Double> rates) {
        double result = 0;
        String from = "", to = "";

        switch (input) {
            case 1:
                result = value * rates.get("ARS");
                from = "BRL";
                to = "ARS";
                break;
            case 2:
                result = value / rates.get("ARS");
                from = "ARS";
                to = "BRL";
                break;
            case 3:
                result = value * rates.get("COP");
                from = "BRL";
                to = "COP";
                break;
            case 4:
                result = value / rates.get("COP");
                from = "COP";
                to = "BRL";
                break;
            case 5:
                result = value * rates.get("USD");
                from = "BRL";
                to = "USD";
                break;
            case 6:
                result = value / rates.get("USD");
                from = "USD";
                to = "BRL";
                break;
        }

        return String.format("Valor %.2f [%s] corresponde ao valor final de %.2f [%s]", value, from, result, to);
    }
}
