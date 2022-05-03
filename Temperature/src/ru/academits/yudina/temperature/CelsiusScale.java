package ru.academits.yudina.temperature;

public class CelsiusScale implements Scale {
    private String celsiusString = "Цельсий";

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
        return celsiusString;
    }
}
