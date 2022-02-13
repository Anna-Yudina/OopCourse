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
                String tempString = "";
                int quoteCount = 0;

                while ((line = reader.readLine()) != null) {

                    if (quoteCount % 2 == 0) {
                        tempString += "    <tr>\n        <td>";
                    }

                    for (int i = 0; i < line.length(); ++i) {
                        char c = line.charAt(i);

                        if (c != ',' & c != '"') {
                            tempString = tempString + c;
                        }

                        if (c == ',') {
                            if (quoteCount % 2 == 0) {
                                stringBuilder.append(tempString + "</td>\n        <td>");
                                tempString = "";
                            } else {
                                tempString = tempString + c;
                            }
                        }

                        if (c == '"') {
                            if (i == line.length() - 1) {
                                quoteCount += 1;
                            } else {
                                if (line.charAt(i + 1) != '"') { // нет дальше кавычек
                                    quoteCount += 1;
                                }

                                if (line.charAt(i + 1) == '"') {
                                    if (quoteCount != 0) {
                                        tempString = tempString + c;
                                    }
                                    quoteCount += 2;
                                    i++;
                                }
                            }
                        }
                    }

                    if (quoteCount % 2 != 0) {
                        tempString += "<br/>";
                    } else {
                        stringBuilder.append(tempString + "</td>\n    </tr>\n");
                        tempString = "";
                    }
                }

                stringBuilder.append("</table>");
                writter.write(stringBuilder.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
