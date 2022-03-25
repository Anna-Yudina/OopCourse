package ru.academits.yudina.temperature;

public class TemperatureConverter {
    public String convertCelsiusToKelvin(double temperature) {
        return String.format("%.2f", temperature + 273.15);
    }

    public String convertCelsiusToFahrenheit(double temperature) {
        return String.format("%.2f", (9 * temperature / 5 + 32));
    }

    public String convertFahrenheitToCelsius(double temperature) {
        return String.format("%.2f", (5 * (temperature - 32) / 9));
    }

    public String convertFahrenheitToKelvin(double temperature) {
        return String.format("%.2f", (5 * (temperature - 32) / 9 + 273.15));
    }

    public String convertKelvinToFahrenheit(double temperature) {
        return String.format("%.2f", (9 * (temperature - 273.15) / 5 + 32));
    }

    public String convertKelvinToCelsius(double temperature) {
        return String.format("%.2f", temperature - 273.15);
    }
}
