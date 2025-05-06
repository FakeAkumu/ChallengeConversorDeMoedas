package br.com.alura.conversordemoedas.modelo;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        ExchangeRateService exchangeRateService = new ExchangeRateService();
        ConversionLogger logger = new ConversionLogger();

        int input;
        double value;
        try {
            ExchangeRates rates = exchangeRateService.getRates();
            Map<String, Double> rateMap = rates.conversionRate;

            while (true) {
                System.out.println("""
                        *************************************************************
                        Seja bem-vindo(a) ao Conversor de Moeda =]
                        Insira um número para converter a moeda de sua escolha.
                        1 - Real brasileiro -> Peso argentino
                        2 - Peso argentino -> Real brasileiro
                        3 - Real brasileiro -> Peso colombiano
                        4 - Peso colombiano -> Real brasileiro
                        5 - Real brasileiro -> Dólar
                        6 - Dólar -> Real brasileiro
                        7 - Sair
                        *************************************************************
                        """);

                Scanner reader = new Scanner(System.in);
                input = reader.nextInt();

                if (input >= 1 && input <= 6) {
                    System.out.println("Por favor, insira o valor a ser convertido:");
                    value = reader.nextDouble();
                    String conversionResult = converter.convert(input, value, rateMap);
                    System.out.println(conversionResult);
                    logger.logConversion(conversionResult);
                } else if (input == 7) {
                    break;
                } else {
                    System.out.println("Resposta inválida. Por favor, insira um dos números apresentados.");
                }
            }

            logger.saveHistory();
            logger.saveRates(rateMap);
        } catch (InputMismatchException e) {
            System.out.println("Ocorreu um erro. O valor entregue não está dentro dos parâmetros esperados.");
        } catch (NullPointerException e) {
            System.out.println("Ocorreu um erro ao tentar obter os valores de conversão. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado. " + e.getMessage());
        }
        System.out.println("Programa finalizado.");
    }
}