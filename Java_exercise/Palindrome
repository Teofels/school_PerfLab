import java.util.Scanner;

public class Palindrome {

    public static void isPalindrome(String target){

        String clearTarget = target.replaceAll("\\W", "").toLowerCase();
        // убираю из строки знаки препинания и привожу к нижнему регистру
        String reverseTarget = new StringBuilder(clearTarget).reverse().toString();
        // получаю перевернутую строку очищенной строки

        if (clearTarget.equals(reverseTarget)){
            // если строка равна перевернутой, то это палиндром
            System.out.println("Введенная строка - палиндром");
        }
        else{
            System.out.println("Введенная строка НЕ палиндром");
        }

    }


    public static void main(String[] args) {

        System.out.println("Запуск программы. Чтобы завершить выполнение введите: exit");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            // исполнять инструкции пока есть ввод в консоль

            String target = scanner.nextLine();

            if(target.equals("exit")){
                // если в консоль ввели "exit" завершить исполнение инструкций
                System.out.println("Завершение программы.");
                break;
            }

            isPalindrome(target);
            // проверка, является ли введенная строка палиндромом
        }

        scanner.close();

    }
}
