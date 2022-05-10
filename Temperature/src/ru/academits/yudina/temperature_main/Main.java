package ru.academits.yudina.temperature_main;

import ru.academits.yudina.temperature.model.*;
import ru.academits.yudina.temperature.view.TemperatureWindow;
import ru.academits.yudina.temperature.view.View;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> scales = Arrays.asList(new CelsiusScale(), new KelvinScale(), new FahrenheitScale());

        TemperatureConverter temperatureConverter = new TemperatureConverter(scales);
        View view = new TemperatureWindow(temperatureConverter);
        view.start();
    }
}
