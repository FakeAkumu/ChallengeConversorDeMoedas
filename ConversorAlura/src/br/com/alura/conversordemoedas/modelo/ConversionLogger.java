package br.com.alura.conversordemoedas.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ConversionLogger {
    private final List<String> conversions = new ArrayList<>();
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void logConversion(String conversion) {
        conversions.add(conversion);
    }

    public void saveHistory() throws IOException {
        try (FileWriter writer = new FileWriter("ConversionHistory.json")) {
            writer.write(gson.toJson(conversions));
        }
    }

    public void saveRates(Map<String, Double> rates) throws IOException {
        Map<String, Double> usedRates = new LinkedHashMap<>();

        usedRates.put("Base BRL", rates.get("BRL"));

        List<String> remainingKeys = new ArrayList<>(List.of("USD", "COP", "ARS"));
        Collections.sort(remainingKeys);

        for (String key : remainingKeys) {
            if (rates.containsKey(key)) {
                usedRates.put(key, rates.get(key));
            }
        }

        try (FileWriter writer = new FileWriter("exchange_rate.json")) {
            writer.write(gson.toJson(usedRates));
        }
    }
}
