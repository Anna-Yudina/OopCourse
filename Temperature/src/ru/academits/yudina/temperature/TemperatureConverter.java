package ru.academits.yudina.temperature;

import java.util.Arrays;
import java.util.List;

public class TemperatureConverter {
    public static final List<Scale> SCALES = Arrays.asList(
            new CelsiusScale(), new KelvinScale(), new FahrenheitScale()
    );

    public double convertTemperature(Scale selectedScale1, Scale selectedScale2, double temperature) {
        return selectedScale2.convertFromCelsius(selectedScale1.convertToCelsius(temperature));
    }
}

