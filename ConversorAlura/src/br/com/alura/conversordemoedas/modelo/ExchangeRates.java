package br.com.alura.conversordemoedas.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ExchangeRates {
    @SerializedName("conversion_rates")
    public Map<String, Double> conversionRate;
}
