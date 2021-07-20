package Tovar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Spisok list = new Spisok();
        list.AddTovar(new Tovar(1, "Пиво", "Алкоголь", 120, 20, 4.8f, 0));
        list.AddTovar(new Tovar(0, "Рыба", "Морепродукты", 100, 30, 4.6f, 10));
        list.AddTovar(new Tovar(3, "Молоко", "Молочные", 50, 15, 4.4f, 0));
        list.AddTovar(new Tovar(4, "Кефир", "Молочные", 60, 15, 3.9f, 0));
        list.AddTovar(new Tovar(5, "Мэшап", "Шиза", 9999999, 1, 0f, 100));
        list.AddTovar(new Tovar(2, "Несквик", "Сухие завтраки", 80, 0, 4.2f, 15));
        boolean ex = false;
        Scanner input = new Scanner(System.in);
        String choose;
        while (!ex){
            System.out.print("\nСправка:\n1 - вывести список товаров;\n2 - рассчитать стоимость товара;" +
                    "\n3 - добавление/изменение/удаление товаров для корзины;\n4 - вывести зарезервированные товары;" +
                    "\n5 - очистить корзину;\n6 - склад (только для персонала);\n7 - покупка товаров;\n0 - выход");
            System.out.print("\nВведите номер действия: ");
            choose = input.nextLine();
            try {
                if (Integer.parseInt(choose) == 1) {
                    list.PrintSpisok();
                } else if (Integer.parseInt(choose) == 2) {
                    list.Calc();
                } else if (Integer.parseInt(choose) == 3) {
                    list.Rezervate();
                } else if (Integer.parseInt(choose) == 4) {
                    list.PrintRezerv();
                } else if (Integer.parseInt(choose) == 5) {
                    list.ClearAll();
                    System.out.println("\nКорзина очищена.");
                } else if (Integer.parseInt(choose) == 6) {
                    list.Sklad();
                } else if (Integer.parseInt(choose) == 7) {
                    System.out.println(list.Sale());
                } else if (Integer.parseInt(choose) == 0) {
                    ex = true;
                }

            } catch (Exception e) {
                System.out.println("Одна ошибка - и ты ошибся!");
            }
        }
    }
}
