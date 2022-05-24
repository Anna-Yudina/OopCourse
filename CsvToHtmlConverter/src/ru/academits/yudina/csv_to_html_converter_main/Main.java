package ru.academits.yudina.csv_to_html_converter_main;

import ru.academits.yudina.csv_to_html_converter.CsvToHtmlConverter;

public class Main {
    public static void main(String[] args) {
        String inputUrl = args[0];
        //"C:/Users/anuta/IdeaProjects/OopCourse/CsvToHtmlConverter/InputFile3.csv";
        String outputUrl = args[1];
        //"C:/Users/anuta/IdeaProjects/OopCourse/CsvToHtmlConverter/OutputFile.html";

        CsvToHtmlConverter csv = new CsvToHtmlConverter(inputUrl, outputUrl);
        csv.writeHtmlFile();
    }
}