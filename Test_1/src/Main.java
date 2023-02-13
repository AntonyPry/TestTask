import java.util.Scanner;

import static javax.management.Query.or;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(calc(s));

    }
    public static String calc(String input) throws Error {
        //массивы с римскми и арабскими цифрами
        String[] arr1 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arr2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] arr10 = {"X", "XX", "XXX", "XL", "L", "LI", "LII", "LIII", "XC", "C"};

        //разделение ввода на строки с числами и знаком
        String[] line = input.split(" ");
        if (line.length != 3){
            throw new Error("throws Exception - ошибка ввода");
        }

        //определение типа чисел
        int type1 = 0;
        int type2 = 0;

        for (int i  = 0; i < 10; i++){
            if (line[0].equals(arr1[i])){
                type1 = 1;
                line[0] = arr2[i];
            }
            if (line[2].equals(arr1[i])){
                type2 = 1;
                line[2] = arr2[i];
            }
            if (line[0].equals(arr2[i]) && type1 == 0){
                type1 = 2;
            }
            if (line[2].equals(arr2[i]) && type2 == 0){
                type2 = 2;
            }
        }

        //проверка на ошибки при вводе
        System.out.println(type1);
        System.out.println(type2);
        if (type1 == 0 || type2 == 0 || type1 != type2 ){
            throw new Error("throws Exception - ошибка типа");
        }

        //исходя из типа находим значение выражения
        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[2]);
        int pre_ans = 0;

        if (line[1].equals("+")){
            pre_ans = a + b;
        }
        else if (line[1].equals("-")){
            pre_ans = a - b;
        }
        else if (line[1].equals("*")){
            pre_ans = a * b;
        }
        else if (line[1].equals("/")){
            pre_ans = a / b;
            if (a % b != 0){
                throw new Error("throws Exception - получается нецелое число");
            }
        }
        else {
            throw new Error("throws Exception - знак не тот");
        }
        String ans = "";
        if (type1 == 2){
            if (pre_ans <= 0){
                throw new Error("throws Exception - минус при римских");
            }
            if (pre_ans / 10 == 0){
                ans += arr1[pre_ans % 10 - 1];
            }
            else{
                ans += arr10[pre_ans / 10 - 1];
                ans += arr1[pre_ans % 10 - 1];
            }
            System.out.println(ans);
        }
        else{
            System.out.println(pre_ans);
        }
        return "";
    }
}
