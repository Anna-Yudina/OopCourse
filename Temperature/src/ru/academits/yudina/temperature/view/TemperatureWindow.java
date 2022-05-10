package ru.academits.yudina.temperature.view;

import ru.academits.yudina.temperature.model.Scale;
import ru.academits.yudina.temperature.model.TemperatureConverter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
            frame.setSize(550, 200);
            frame.setMinimumSize(new Dimension(600, 250));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            JPanel mainPanel = new JPanel(new GridBagLayout());
            frame.add(mainPanel);

            Color color = new Color(100, 100, 200);

            GridBagConstraints constrainsLeft = new GridBagConstraints();
            constrainsLeft.anchor = GridBagConstraints.WEST;
            constrainsLeft.gridy = 0;
            JLabel enterTemperatureLabel = new JLabel("Введите температуру:");
            enterTemperatureLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
            enterTemperatureLabel.setForeground(color); // установка цвета букв
            enterTemperatureLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
            mainPanel.add(enterTemperatureLabel, constrainsLeft);

            GridBagConstraints constrainsRight = new GridBagConstraints();
            constrainsRight.anchor = GridBagConstraints.EAST;

            JTextField temperatureField = new JTextField(15);
            temperatureField.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            constrainsRight.gridx = 1;
            constrainsRight.gridy = 0;
            mainPanel.add(temperatureField, constrainsRight);

            JPanel scaleFromPanel = new JPanel();
            constrainsLeft.gridx = 0;
            constrainsLeft.gridy = 1;
            mainPanel.add(scaleFromPanel, constrainsLeft);

            JLabel scaleFromLabel1 = new JLabel("Перевести из ");
            scaleFromLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 16)); // установка шрифта
            scaleFromLabel1.setForeground(color);
            scaleFromPanel.add(scaleFromLabel1);

            Object[] selectedScales = temperatureConverter.getScales().toArray();

            JComboBox<Object> inputScalesComboBox = new JComboBox<>(selectedScales);
            inputScalesComboBox.setPreferredSize(new Dimension(150, 20));
            inputScalesComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            scaleFromPanel.add(inputScalesComboBox);

            JLabel scaleFromLabel2 = new JLabel("в ");
            scaleFromLabel2.setFont(new Font("Comic Sans MS", Font.BOLD, 16)); // установка шрифта
            scaleFromLabel2.setForeground(color);
            scaleFromPanel.add(scaleFromLabel2);

            JComboBox<Object> outputScalesComboBox = new JComboBox<>(selectedScales);
            constrainsRight.gridx = 1;
            constrainsRight.gridy = 1;
            outputScalesComboBox.setPreferredSize(new Dimension(155, 20));
            outputScalesComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            mainPanel.add(outputScalesComboBox, constrainsRight);

            JPanel resultPanel = new JPanel();
            constrainsLeft.gridx = 1;
            constrainsLeft.gridy = 3;
            mainPanel.add(resultPanel, constrainsLeft);

            JLabel resultTextLabel = new JLabel("Результат:");
            resultTextLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            resultPanel.add(resultTextLabel);

            JLabel outputTemperatureLabel = new JLabel();
            outputTemperatureLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

            JButton okButton = new JButton("Перевести");
            okButton.setPreferredSize(new Dimension(155, 30));
            okButton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            okButton.setBackground(color);

            okButton.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(temperatureField.getText());
                    Scale inputScale = (Scale) inputScalesComboBox.getSelectedItem();
                    Scale outputScale = (Scale) outputScalesComboBox.getSelectedItem();

                    String outputTemperature = String.format("%.2f", temperatureConverter.convertTemperature(inputScale, outputScale, inputTemperature));
                    outputTemperatureLabel.setText(outputTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            constrainsRight.gridx = 1;
            constrainsRight.gridy = 2;
            mainPanel.add(okButton, constrainsRight);

            resultPanel.add(outputTemperatureLabel);

            frame.setVisible(true);
        });
    }
}