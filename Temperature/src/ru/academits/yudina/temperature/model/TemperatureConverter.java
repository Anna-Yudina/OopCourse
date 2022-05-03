package ru.academits.yudina.temperature.model;

import java.util.List;

public class TemperatureConverter {
    public static List<Scale> SCALES;

    public TemperatureConverter(List<Scale> listScale) {
        SCALES = listScale;
    }

    public double convertTemperature(Scale inputSelectedScale, Scale outputSelectedScale, double temperature) {
        return outputSelectedScale.convertFromCelsius(inputSelectedScale.convertToCelsius(temperature));
    }
}

