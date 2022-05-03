package ru.academits.yudina.temperature.model;

public class CelsiusScale implements Scale {
    public static String celsiusString = "Цельсий";

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Цельсий";

    }
}
