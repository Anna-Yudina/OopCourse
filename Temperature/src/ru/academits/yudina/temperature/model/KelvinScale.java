package ru.academits.yudina.temperature.model;

public class KelvinScale implements Scale {
    public static String kelvinString = "Кельвин";

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
        return "Кельвин";
    }
}
