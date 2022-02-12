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
                String string = "";
                int quoteCount = 0;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append("    <tr>\n        <td>");

                    for (int i = 0; i < line.length(); ++i) {
                        char c = line.charAt(i);

                        if (c != ',' & c != '"') {
                            string = string + c;
                        }

                        if (c == '"') {
                            quoteCount += 1;
                        }

                        if (c == ',' && quoteCount % 2 == 0) {
                            stringBuilder.append(string + "</td>\n        <td>");
                            string = "";
                        }
                    }
                    stringBuilder.append(string + "</td>\n    </tr>\n");
                    string = "";
                }

                stringBuilder.append("</table>");
                writter.write(stringBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}