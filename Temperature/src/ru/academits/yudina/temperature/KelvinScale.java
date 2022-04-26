package ru.academits.yudina.temperature;

public class KelvinScale implements Scale {
    private String kelvinStribg = "Кельвин";

    @Override
    public double convertToCelcius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelcius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public String toString() {
        return kelvinStribg;
    }
}
