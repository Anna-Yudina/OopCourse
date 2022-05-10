package ru.academits.yudina.temperature.model;

import java.util.List;

public class TemperatureConverter {
    private List<Scale> scales;

    public List<Scale> getScales() {
        return scales;
    }

    public void setScales(List<Scale> scales) {
        this.scales = scales;
    }

    public TemperatureConverter(List<Scale> listScale) {
        scales = listScale;
    }

    public double convertTemperature(Scale inputScale, Scale outputScale, double temperature) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(temperature));
    }
}

