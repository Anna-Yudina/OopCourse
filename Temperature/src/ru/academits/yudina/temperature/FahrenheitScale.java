package ru.academits.yudina.temperature;

public class FahrenheitScale implements Scale {
    private String fahrenheitString = "Фаренгейт";

    @Override
    public double convertToCelcius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public double convertFromCelcius(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public String toString() {
        return fahrenheitString;
    }
}
