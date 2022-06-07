package ru.academits.yudina.csv_to_html_converter;

import java.io.*;

public class CsvToHtmlConverter {
    public void writeHtmlFile(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.write("<!DOCTYPE html>" + System.lineSeparator());
        writer.write("<html>" + System.lineSeparator());
        writer.write("    <head>" + System.lineSeparator());
        writer.write("        <meta charset=\"UTF-8\">" + System.lineSeparator());
        writer.write("        <title>Сконвертированный файл из CSV в HTML</title>" + System.lineSeparator());
        writer.write("    </head>" + System.lineSeparator());
        writer.write("    <body>" + System.lineSeparator());
        writer.write("        <table>" + System.lineSeparator());

        String line;
        boolean isCellInQuotes = false;

        while ((line = reader.readLine()) != null) {
            if (!isCellInQuotes) {
                writer.write("            <tr>" + System.lineSeparator());
                writer.write("                <td>");
            }

            for (int i = 0; i < line.length(); ++i) {
                char c = line.charAt(i);

                if (c == '"') {
                    if (!isCellInQuotes) {
                        isCellInQuotes = true;
                    } else {
                        if (i != line.length() - 1 && line.charAt(i + 1) == '"') {
                            writer.write("\"");
                            i++;
                        } else {
                            isCellInQuotes = false;
                        }
                    }
                } else if (c == ',') {
                    if (!isCellInQuotes) {
                        writer.write("</td>" + System.lineSeparator());
                        writer.write("                <td>");
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

            if (!isCellInQuotes) {
                writer.write("</td>" + System.lineSeparator());
                writer.write("            </tr>" + System.lineSeparator());
            } else {
                writer.write("<br/>");
            }
        }

        writer.write("        </table>" + System.lineSeparator());
        writer.write("    </body>" + System.lineSeparator());
        writer.write("</html>" + System.lineSeparator());
    }
}