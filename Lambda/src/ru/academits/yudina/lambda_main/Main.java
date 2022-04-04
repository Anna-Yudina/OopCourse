package ru.academits.yudina.lambda_main;

import ru.academits.yudina.lambda.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static List<String> getUniqueNamesList(List<Person> list) {
        return list.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static String getUniqueNamesString(List<Person> list) {
        return list.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
    }

    public static double getUnderagePersonsAverageAge(List<Person> list) {
        return list.stream()
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
    }

    public static Map<String, Double> getAverageAgesByNames(List<Person> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
    }

    public static void printPersonsFrom45To20Age(List<Person> list) {
        System.out.println(list.stream()
                .filter(p -> p.getAge() <= 45 && p.getAge() >= 20)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", ", "", ".")));
    }

    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Иван", 17),
                new Person("Максим", 18),
                new Person("Тимур", 25),
                new Person("Сергей", 34),
                new Person("Максим", 19),
                new Person("Игорь", 27),
                new Person("Денис", 14),
                new Person("Иван", 23),
                new Person("Евгений", 28),
                new Person("Марина", 16),
                new Person("Оксана", 12),
                new Person("Елена", 34),
                new Person("Марина", 60),
                new Person("Света", 24),
                new Person("Аня", 27),
                new Person("Соня", 17),
                new Person("Юля", 45),
                new Person("Аня", 13),
                new Person("Света", 29),
                new Person("Мария", 30)
        );

        List<String> uniqueNamesList = getUniqueNamesList(personsList);
        System.out.println("Изначальный список людей: " + personsList);
        System.out.println("Список людей с уникальными именами." + uniqueNamesList);

        String uniqueNamesString = getUniqueNamesString(personsList);
        System.out.println("Список уникальных имен. " + uniqueNamesString);

        double underagePersonsAverageAge = getUnderagePersonsAverageAge(personsList);
        System.out.printf("Средний возраст людей младше 18 лет: %.2f%n", underagePersonsAverageAge);

        Map<String, Double> averageAgesByNames = getAverageAgesByNames(personsList);
        System.out.println("Получение списка (ключ - имена, значение - средний возраст): " + averageAgesByNames);

        System.out.println("Печатаем список людей возрастом от 20 до 45 лет в порядке убывания: ");
        printPersonsFrom45To20Age(personsList);
    }
}