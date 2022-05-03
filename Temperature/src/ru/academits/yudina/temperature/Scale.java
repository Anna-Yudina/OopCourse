package ru.academits.yudina.temperature;

public interface Scale {
    public double convertToCelsius(double temperature);

    public double convertFromCelsius(double temperature);
}
