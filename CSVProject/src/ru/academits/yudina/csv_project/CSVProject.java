package ru.academits.yudina.csv_project;

import java.io.*;

public class CSVProject {
    public static void main(String[] args) {
        String inputFileName = "H:/Аня/работа/помойка/CSVProject.csv";
        String outputFileName = "H:/Аня/работа/помойка/OutputFile.html";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
                String line;
                writter.write("<table>" + System.lineSeparator());

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";");
                    writter.write("    <tr>" + System.lineSeparator());

                    for (String element : data) {
                        writter.write("        <td>" + element + "</td>" + System.lineSeparator());
                    }

                    writter.write("    </tr>" + System.lineSeparator());
                }

                writter.write("</table>" + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



