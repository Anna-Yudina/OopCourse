package ru.academits.yudina.temperature;

import javax.swing.*;
import java.awt.*;

public class TemperatureWindow implements View {
    private final TemperatureConverter temperatureConverter;

    public TemperatureWindow(TemperatureConverter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Перевод температуры");
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            frame.add(panel);

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;

            JLabel enterTemperatureLabel = new JLabel("Введите температуру:");
            panel.add(enterTemperatureLabel, c);

            JTextField celsiusTemperatureField = new JTextField(10);
            c.gridx = 1;
            c.gridy = 0;
            panel.add(celsiusTemperatureField, c);

            JLabel label1 = new JLabel("Перевести ");
            c.gridx = 0;
            c.gridy = 1;
            panel.add(label1, c);

            String[] entriesArray = new String[]{"из шкалы Цельсия в шкалу Кельвина",
                    "из шкалы Цельсия в шкалу Фаренгейта",
                    "из шкалы Фаренгейта в шкалу Цельсия",
                    "из шкалы Фаренгейта в шкалу Кельвина",
                    "из шкалы Кельвина в шкалу Цельсия",
                    "из шкалы Кельвина в шкалу Фаренгейта"
            };

            JComboBox<String> comboBox = new JComboBox<>(entriesArray);
            c.gridx = 1;
            c.gridy = 1;
            panel.add(comboBox, c);

            JLabel label2 = new JLabel("Результат:");
            c.gridx = 0;
            c.gridy = 3;
            panel.add(label2, c);

            JLabel resultLabel = new JLabel();

            JButton okButton = new JButton("Перевести");

            okButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(celsiusTemperatureField.getText());
                    int checkIndex = comboBox.getSelectedIndex();

                    if (checkIndex == 0) {
                        resultLabel.setText(temperatureConverter.convertCelsiusToKelvin(temperature));
                    } else if (checkIndex == 1) {
                        resultLabel.setText(temperatureConverter.convertCelsiusToFahrenheit(temperature));
                    } else if (checkIndex == 2) {
                        resultLabel.setText(temperatureConverter.convertFahrenheitToCelsius(temperature));
                    } else if (checkIndex == 3) {
                        resultLabel.setText(temperatureConverter.convertFahrenheitToKelvin(temperature));
                    } else if (checkIndex == 4) {
                        resultLabel.setText(temperatureConverter.convertKelvinToCelsius(temperature));
                    } else if (checkIndex == 5) {
                        resultLabel.setText(temperatureConverter.convertKelvinToFahrenheit(temperature));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            c.gridx = 1;
            c.gridy = 2;
            panel.add(okButton, c);

            c.gridx = 1;
            c.gridy = 3;
            panel.add(resultLabel, c);

            frame.setVisible(true);
        });
    }
}