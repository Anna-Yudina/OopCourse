package ru.academits.yudina.temperature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemperatureConverter {
    public static final List<Scale> SCALES = Arrays.asList(
            new CelciusScale(), new KelvinScale(), new FahrenheitScale()
    );

    public double convertTemperature(Scale selectedScale1, Scale selectedScale2, double temperature) {
        return selectedScale2.convertFromCelcius(selectedScale1.convertToCelcius(temperature));
    }
}

