package ru.academits.yudina.temperature;

public class CelciusScale implements Scale {
    private String celsiusString = "Цельсий";

    @Override
    public double convertToCelcius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelcius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return celsiusString;
    }
}
