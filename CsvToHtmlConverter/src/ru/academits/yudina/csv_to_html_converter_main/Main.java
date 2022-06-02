package ru.academits.yudina.csv_to_html_converter_main;

import ru.academits.yudina.csv_to_html_converter.CsvToHtmlConverter;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Аргументы отсутствуют или переданы не в нужном количестве. " + System.lineSeparator() +
                    "Необходимо передать 2 аргумента: " + System.lineSeparator() +
                    "1. путь к файлу с расширением csv, который нужно сконвертировать; " + System.lineSeparator() +
                    "2. путь к файлу с расширением html, куда записывается результат конвертации.");
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            CsvToHtmlConverter csv = new CsvToHtmlConverter(inputFilePath, outputFilePath);
            csv.writeHtmlFile(reader, writer);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка");
        }
    }
}