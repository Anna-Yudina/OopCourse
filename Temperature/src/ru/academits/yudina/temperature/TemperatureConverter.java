package ru.academits.yudina.temperature;

public class TemperatureConverter {
    private static final String CELSIUS_TEMPERATURE = "Цельсий";
    private static final String KELVIN_TEMPERATURE = "Кельвин";
    private static final String FAHRENHEIT_TEMPERATURE = "Фаренгейт";

    public String[] getTemperaturesArray() {
        return new String[]{CELSIUS_TEMPERATURE, KELVIN_TEMPERATURE, FAHRENHEIT_TEMPERATURE};
    }

    public double convertTemperature(String scaleFrom, String scaleTo, double temperature) {
        if (scaleFrom.equals(CELSIUS_TEMPERATURE)) {
            return convertCelsius(scaleTo, temperature);
        }

        if (scaleFrom.equals(KELVIN_TEMPERATURE)) {
            return convertKelvin(scaleTo, temperature);
        }

        return convertFahrenheit(scaleTo, temperature);
    }

    public double convertCelsius(String scaleTo, double temperature) {
        return switch (scaleTo) {
            case (KELVIN_TEMPERATURE) -> 9 * temperature / 5 + 32;
            case (FAHRENHEIT_TEMPERATURE) -> temperature + 273.15;
            default -> temperature;
        };
    }

    public double convertFahrenheit(String scaleTo, double temperature) {
        return switch (scaleTo) {
            case (CELSIUS_TEMPERATURE) -> 5  * (temperature - 32) / 9;
            case (KELVIN_TEMPERATURE) -> (5 * (temperature - 32) / 9 + 273.15);
            default -> temperature;
        };
    }

    public double convertKelvin(String scaleTo, double temperature) {
        return switch (scaleTo) {
            case (CELSIUS_TEMPERATURE) -> temperature - 273.15;
            case (FAHRENHEIT_TEMPERATURE) -> 9 * (temperature - 273.15) / 5 + 32;
            default -> temperature;
        };
    }
}

