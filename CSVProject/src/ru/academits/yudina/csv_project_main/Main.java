package ru.academits.yudina.csv_project_main;
import ru.academits.yudina.csv_project.CSVProject;

public class Main {
    public static void main(String[] args) {
        String inputUrl = "C:/Users/anuta/IdeaProjects/OopCourse/CSVProject/InputFile.txt";
        String outputUrl = "C:/Users/anuta/IdeaProjects/OopCourse/CSVProject/OutputFile.txt";

        CSVProject csv = new CSVProject(inputUrl,outputUrl);
        csv.getHtmlFile();
    }
}