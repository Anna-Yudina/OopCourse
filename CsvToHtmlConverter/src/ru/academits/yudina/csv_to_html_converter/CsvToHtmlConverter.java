package ru.academits.yudina.csv_to_html_converter;

import java.io.*;

public class CsvToHtmlConverter {
    public void writeHtmlFile(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta charset=\"UTF-8\">");
        writer.println("        <title>Сконвертированный файл из CSV в HTML</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <table>");

        String line;
        boolean isCellInQuotes = false;

        while ((line = reader.readLine()) != null) {
            if (!isCellInQuotes) {
                writer.println("            <tr>");
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
                        writer.println("</td>");
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
                writer.println("</td>");
                writer.println("            </tr>");
            } else {
                writer.write("<br/>");
            }
        }

        writer.println("        </table>");
        writer.println("    </body>");
        writer.println("</html>");
    }
}