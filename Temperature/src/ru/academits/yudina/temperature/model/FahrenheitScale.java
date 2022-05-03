package ru.academits.yudina.temperature.model;

public class FahrenheitScale implements Scale {
    public static String fahrenheitString = "Фаренгейт";

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public String toString() {
        return "Фаренгейт";
    }
}