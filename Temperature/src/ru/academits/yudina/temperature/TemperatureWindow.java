package ru.academits.yudina.temperature;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
            frame.setMinimumSize(new Dimension(500, 300));
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

            Object[] selectedScales = TemperatureConverter.SCALES.toArray();

            JComboBox<Object> ScaleFromComboBox = new JComboBox<>(selectedScales);
            ScaleFromComboBox.setPreferredSize(new Dimension(150, 20));
            ScaleFromComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            scaleFromPanel.add(ScaleFromComboBox);

            JLabel scaleFromLabel2 = new JLabel("в ");
            scaleFromLabel2.setFont(new Font("Comic Sans MS", Font.BOLD, 16)); // установка шрифта
            scaleFromLabel2.setForeground(color);
            scaleFromPanel.add(scaleFromLabel2);

            JComboBox<Object> ScaleToComboBox = new JComboBox<>(selectedScales);
            constrainsRight.gridx = 1;
            constrainsRight.gridy = 1;
            ScaleToComboBox.setPreferredSize(new Dimension(155, 20));
            ScaleToComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            mainPanel.add(ScaleToComboBox, constrainsRight);

            JPanel resultPanel = new JPanel();
            constrainsLeft.gridx = 1;
            constrainsLeft.gridy = 3;
            mainPanel.add(resultPanel, constrainsLeft);

            JLabel resultLabel1 = new JLabel("Результат:");
            resultLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            resultPanel.add(resultLabel1);

            JLabel resultLabel2 = new JLabel();
            resultLabel2.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

            JButton okButton = new JButton("Перевести");
            okButton.setPreferredSize(new Dimension(155, 30));
            okButton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            okButton.setBackground(color);

            okButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(temperatureField.getText());
                    Scale selectedScale1 = (Scale) ScaleFromComboBox.getSelectedItem();
                    Scale selectedScale2 = (Scale) ScaleToComboBox.getSelectedItem();

                    String temperatureAfterConvert = String.format("%.2f", temperatureConverter.convertTemperature(selectedScale1, selectedScale2, temperature));
                    resultLabel2.setText(temperatureAfterConvert);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            constrainsRight.gridx = 1;
            constrainsRight.gridy = 2;
            mainPanel.add(okButton, constrainsRight);

            resultPanel.add(resultLabel2);

            frame.setVisible(true);
        });
    }
}