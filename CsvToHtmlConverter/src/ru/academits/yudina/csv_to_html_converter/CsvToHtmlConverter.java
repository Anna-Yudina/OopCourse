package ru.academits.yudina.csv_to_html_converter;

import java.io.*;

public class CsvToHtmlConverter {
    private String inputFileName;
    private String outputFileName;

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public CsvToHtmlConverter(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void writeHtmlFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write("<!DOCTYPE html>" + System.lineSeparator() + System.lineSeparator());
            writer.write("<html>" + System.lineSeparator());
            writer.write("  <head>" + System.lineSeparator());
            writer.write("  <meta charset=\"UTF-8\">" + System.lineSeparator());
            writer.write("  </head>" + System.lineSeparator() + System.lineSeparator());
            writer.write("  <body>" + System.lineSeparator());
            writer.write("    <table>" + System.lineSeparator());

            String line;
            boolean cellInQuotes = false;

            while ((line = reader.readLine()) != null) {
                if (!cellInQuotes) {
                    writer.write("      <tr>" + System.lineSeparator());
                    writer.write("          <td>");
                }

                for (int i = 0; i < line.length(); ++i) {
                    char c = line.charAt(i);

                    if (c == '"') {
                        if (!cellInQuotes) {
                            cellInQuotes = true;
                        } else {
                            if (i != line.length() - 1 && line.charAt(i + 1) == '"') {
                                writer.write("\"");
                                i++;
                            } else {
                                cellInQuotes = false;
                            }
                        }
                    } else if (c == ',') {
                        if (!cellInQuotes) {
                            writer.write("</td>" + System.lineSeparator());
                            writer.write("          <td>");
                        } else {
                            writer.write(c);
                        }
                    } else if (c == '>') {
                        writer.write("&gt;");
                    } else if (c == '<') {
                        writer.write("&lt;");
                    } else if (c == '&') {
                        writer.write("&amp;");
                    } else {
                        writer.write(c);
                    }
                }

                if (!cellInQuotes) {
                    writer.write("</td>" + System.lineSeparator());
                    writer.write("      </tr>" + System.lineSeparator());
                } else {
                    writer.write("<br/>");
                }
            }

            writer.write("    </table>" + System.lineSeparator());
            writer.write("  </body>" + System.lineSeparator());
            writer.write("</html>" + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Не удалось записать файл");
        }
    }
}
