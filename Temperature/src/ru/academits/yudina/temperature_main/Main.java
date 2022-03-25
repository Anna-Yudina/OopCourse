package ru.academits.yudina.temperature_main;

import ru.academits.yudina.temperature.TemperatureConverter;
import ru.academits.yudina.temperature.TemperatureWindow;
import ru.academits.yudina.temperature.View;

public class Main {
    public static void main(String[] args) {
        TemperatureConverter temperatureConverter = new TemperatureConverter();
        View view = new TemperatureWindow(temperatureConverter);
        view.start();
    }
}
