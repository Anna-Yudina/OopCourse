package ru.academits.yudina.csv_project;

import java.io.*;

public class CSVProject {
    String inputFileName;
    String outputFileName;

    public CSVProject(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void getHtmlFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<table>\n");

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append("    <tr>\n        <td>");

                    for (int i = 0; i < line.length(); ++i) {
                        char c = line.charAt(i);

                        if (c != ',' & c != '"') {
                            stringBuilder.append(c);
                        }

                        if (c == ',') {
                            stringBuilder.append("</td>\n        <td>");
                        }
                    }

                    stringBuilder.append("</td>\n    </tr>\n");
                }

                stringBuilder.append("</table>");
                writter.write(stringBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}