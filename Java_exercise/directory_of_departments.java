import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class directory_of_departments {

    static class DepartmentCompare implements Comparator<String>
    {
        /**
         * По условию задачи необходимо "Реализовать сортировку массива кодов подразделений по убыванию,
         * при котором сохранялась бы иерархическая структура".
         * То есть, если у нас есть подразделения "K2" и "K2\SK1", то "K2" должно находится выше, чтобы сохранилась
         * иерархическая структура.
         * В то же время, если нам даны “K2\SK1\SSK1” и “K2\SK1\SSK2”, то второе подразделение должно оказаться выше,
         * чтобы подразделения были отсортированы по УБЫВАНИЮ.
         * Для решения этой задачи реализуем свой интерфейс Comparator.
         */

        @Override
        public int compare(String o1, String o2)
        {
            // Мы сравниваем две строки - названия подразделений
            String[] splitDep1 = o1.split("\\\\");
            String[] splitDep2 = o2.split("\\\\");
            // Разделим названия подразделений по "\", чтобы можно было сравнить родительские подразделения

            // Проходим по всем названиям подразделений, которые содержатся в сравниваемых объектах.
            // Если название департамента в 1-ом объекте меньше, то возвращаем 1, например:
            // K1 < K2, то есть compareTo будет <0, тогда нужно вернуть 1, так как K2 должно стоять выше
            // Для обратной ситуации возвращаем -1.
            // Чтобы сохранить иерархическую структуру, сравним длины названий подразделений,
            // Например, length(K2\SK1) > length(K2), но K2 выше по иерархии, следовательно возвращаем 1.
            for (int i = 0; i < splitDep1.length || i < splitDep2.length; i++) {
                if (splitDep1[i].compareTo(splitDep2[i]) < 0){
                    return 1;
                } else if (splitDep1[i].compareTo(splitDep2[i]) > 0){
                    return -1;
                }

                if (splitDep1.length > splitDep2.length){
                    return 1;
                } else if (splitDep1.length < splitDep2.length) {
                    return -1;
                }

            }
            return 0;
        }
    }

    private static List<String> supplementDepartment(List<String> dep){
        /**
         * Согласно заданию во входных данных "Возможны случаи, когда в массиве отсутствуют строки
         * с кодом верхнеуровнего подразделения".
         * Чтобы дополнить входные данные недостоющими кодами верхнеуровневых подразделений реализован этот метод.
         */

        HashSet<String> uniqDepartments = new HashSet<>();

        for(String item : dep){
            String[] subStr = item.split("\\\\");

            String temp = subStr[0];
            uniqDepartments.add(temp);

            for (int i = 1; i < subStr.length ; i++) {
                temp = temp + "\\" + subStr[i];
                uniqDepartments.add(temp);
            }
        }

        return new ArrayList<>(uniqDepartments);
    }



    public static void main(String[] args) throws IOException {

        List<String> departaments = Files.lines(Paths.get("src/some_text.txt"), StandardCharsets.UTF_8)
                                    .collect(Collectors.toList());
        // Считываем входные данные из файла

        System.out.println("Входные данные: ");
        departaments.forEach(System.out::println);
        System.out.println("----------------------");

        List<String> allDepartments = supplementDepartment(departaments); // Дополняем недостающие данные

        allDepartments.sort(new DepartmentCompare()); // Сортируем данные согласно заданию

        System.out.println("Отсортированные данные кодов подразделений по убыванию, " +
                "с сохранением иерархической структуры");
        allDepartments.forEach(System.out::println); // Вывод результата

    }
}
