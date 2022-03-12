package ru.academits.yudina.lambda_main;

import ru.academits.yudina.lambda.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static List<String> getUniqueNameList(ArrayList<Person> list) {
        return list.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static String getUniqueName(ArrayList<Person> list) {
        return "Имена: " + list.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public static ArrayList<Person> getUnderageList(ArrayList<Person> list) {
        return (ArrayList<Person>) list.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());
    }

    public static double getAverageAge(ArrayList<Person> list) {
        return list.stream()
                .mapToDouble(Person::getAge)
                .average()
                .getAsDouble();
    }

    public static Map<String, Double> getMap(ArrayList<Person> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
    }

    public static ArrayList<Person> getFrom20To45PersonList(ArrayList<Person> list) {
        return (ArrayList<Person>) list.stream()
                .filter(p -> p.getAge() <= 45)
                .filter(p -> p.getAge() >= 20)
                .collect(Collectors.toList());
    }

    public static void printDescendingPerson(ArrayList<Person> list) {
        System.out.println(list.stream()
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", ")));
    }

    public static void main(String[] args) {
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person("Иван", 17));
        personArrayList.add(new Person("Максим", 18));
        personArrayList.add(new Person("Тимур", 25));
        personArrayList.add(new Person("Сергей", 34));
        personArrayList.add(new Person("Максим", 19));
        personArrayList.add(new Person("Игорь", 27));
        personArrayList.add(new Person("Денис", 14));
        personArrayList.add(new Person("Иван", 23));
        personArrayList.add(new Person("Евгений", 28));
        personArrayList.add(new Person("Марина", 16));
        personArrayList.add(new Person("Оксана", 12));
        personArrayList.add(new Person("Елена", 34));
        personArrayList.add(new Person("Марина", 60));
        personArrayList.add(new Person("Света", 24));
        personArrayList.add(new Person("Аня", 27));
        personArrayList.add(new Person("Соня", 17));
        personArrayList.add(new Person("Юля", 45));
        personArrayList.add(new Person("Аня", 13));
        personArrayList.add(new Person("Света", 29));
        personArrayList.add(new Person("Мария", 30));

        List<String> uniqueNameList = getUniqueNameList(personArrayList);
        System.out.println("Изначальный список людей: " + personArrayList);
        System.out.println("Список людей с уникальными именами." + uniqueNameList);

        String uniqueNames = getUniqueName(personArrayList);
        System.out.println("Список уникальных имен. " + uniqueNames);

        ArrayList<Person> underageList = getUnderageList(personArrayList);
        System.out.println("Список людей младше 18 лет: " + underageList);

        double underageAverageAge = getAverageAge(underageList);
        System.out.printf("Средний возраст людей младше 18 лет: %.2f%n", underageAverageAge);

        Map<String, Double> personMap = getMap(personArrayList);
        System.out.println("Получение списка (ключ - имена, знвачение - средний возраст): " + personMap);

        ArrayList<Person> from20To45PersonList = getFrom20To45PersonList(personArrayList);
        System.out.println("Список людей в возрасте от 20 до 45 лет: " + from20To45PersonList);

        System.out.print("Имена людей в возрасте от 20 до 45 лет в порядке убывания: ");
        printDescendingPerson(from20To45PersonList);
    }
}