package ru.academits.yudina.temperature;

public interface Scale {
    public double convertToCelcius(double temperature);

    public double convertFromCelcius(double temperature);
}
