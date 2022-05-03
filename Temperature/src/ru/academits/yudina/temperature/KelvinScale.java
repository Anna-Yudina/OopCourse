package ru.academits.yudina.temperature;

public class KelvinScale implements Scale {
    private String kelvinString = "Кельвин";

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public String toString() {
        return kelvinString;
    }
}
