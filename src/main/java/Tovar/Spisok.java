package Tovar;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import java.util.Scanner;

public class Spisok {

    private Node head;
    private Node tail;

    public Spisok() {
        head = null;
        tail = null;
    }

    private boolean IsEmpty() {
        return head == null;
    }

    public void AddTovar (Tovar data) {
        Node temp = new Node(data);
        if (IsEmpty()) {
            head = temp;
            temp.prev = tail;
            tail = temp;
        } else {
            Node cur = head;
            int a = temp.data.getID();
            int b = cur.data.getID();
            while (a > b) {
                if (cur.next == null) {
                    break;
                }
                cur = cur.next;
                b = cur.data.getID();
            }
            if (a > b) {
                tail.next = temp;
                temp.prev = tail;
                tail = temp; }
            else if (a == b) {
                System.out.println("Такой ID уже существует.");
                return; }
            else if (cur == head) {
                head.prev = temp;
                temp.next = head;
                head = temp; }
            else if (cur != head && cur != tail || a < b) {
                cur.prev.next = temp;
                temp.prev = cur.prev;
                cur.prev = temp;
                temp.next = cur;
            }
        }
    }

    private String PrintLine() {
        String enter;
        Scanner input = new Scanner(System.in);
        enter = input.nextLine();
        return enter;
    }

    public void Calc () {
        if (IsEmpty()) {
            System.out.println("Ошибка. Товары отсутствуют.");
            return;
        }
        String i;
        double x;
        Node temp;
        while (true) {
            System.out.print("\nВведите ID товара: ");
            i = PrintLine();
            try {
                temp = head;
                int b = temp.data.getID();
                while (Integer.parseInt(i) != b) {
                    if (temp.next == null) {
                        b = temp.data.getID();
                        break;
                    }
                    temp = temp.next;
                    b = temp.data.getID();
                }
                if (Integer.parseInt(i) != b) {
                    System.out.println("Такого ID не существует.");
                } else {
                    System.out.println("Расчёт стоимости для товара: " + temp.data.getName());
                    System.out.println("Единица товара стоит: " + temp.data.getPrice());
                    x = temp.data.getPrice()-temp.data.getPrice()*temp.data.getDisc()/100;
                    if (x != temp.data.getPrice()) {
                        System.out.println("Скидка на товар: " + temp.data.getDisc());
                        System.out.println("С учётом скидки: " + x);
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println("Одна ошибка - и ты ошибся!");
            }
        }
        while (true) {
            System.out.print("Введите количество необходимого товара: ");
            i = PrintLine();
            try {
                if (Integer.parseInt(i) < 0) {
                    System.out.println("Вы ввели отрицательное число.");
                } else {
                    x = (temp.data.getPrice()*Integer.parseInt(i))-(temp.data.getPrice()*Integer.parseInt(i)*temp.data.getDisc()/100);
                    System.out.println("Товар " + temp.data.getName() + " в количестве " + i + " будет стоить " + x + " рублей");
                    if (Integer.parseInt(i) > temp.data.getBalance()) {
                        System.out.println("Предупреждаем, что у нас " + temp.data.getBalance() + " единиц товара на складе."); }
                }
                break;
            } catch (Exception e) {
                System.out.println("Одна ошибка - и ты ошибся!");
            }
        }
    }

    public void Rezervate () {
        if (IsEmpty()) {
            System.out.println("Ошибка. Товары отсутствуют.");
            return;
        }
        String i;
        Node temp;
        while (true) {
            System.out.print("\nВведите ID товара для добавления в корзину: ");
            i = PrintLine();
            try {
                temp = head;
                int b = temp.data.getID();
                while (Integer.parseInt(i) != b) {
                    if (temp.next == null) {
                        b = temp.data.getID();
                        break;
                    }
                    temp = temp.next;
                    b = temp.data.getID();
                }
                if (Integer.parseInt(i) != b) {
                    System.out.println("Такого ID не существует.");
                } else if (temp.data.getBalance() == 0) {
                    System.out.println("Товар " + temp.data.getName() + ", к сожалению, закончился. Выберите другой товар.");
                } else {
                    System.out.println("Вы выбрали товар: " + temp.data.getName());
                    break;
                }
            } catch (Exception e) {
                System.out.println("Одна ошибка - и ты ошибся!");
            }
        }
        if (temp.data.getRezerv() != 0) {
            System.out.println("Подсказка: в данный момент вы зарезервировали " + temp.data.getRezerv() + " единиц товара.");
            System.out.println("Для удаления товара из корзины введите 0.");
        }
        while (true) {
            System.out.print("Введите количество товара, добавляемого в корзину: ");
            i = PrintLine();
            try {
                if (Integer.parseInt(i) < 0) {
                    System.out.println("Вы ввели отрицательное число.");
                } else if (Integer.parseInt(i) > temp.data.getBalance()) {
                    System.out.println("На складе нет столько товаров. Сейчас на складе " + temp.data.getBalance() + " единиц товара.");
                } else if (Integer.parseInt(i) == 0 && temp.data.getRezerv() != 0) {
                    temp.data.setRezerv(Integer.parseInt(i));
                    System.out.println("Товар удалён.");
                    break;
                } else if (Integer.parseInt(i) == 0 && temp.data.getRezerv() == 0) {
                    System.out.println("Товар не добавлен.");
                    break;
                } else {
                    temp.data.setRezerv(Integer.parseInt(i));
                    System.out.println("Товар зарезервирован.");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Одна ошибка - и ты ошибся!");
            }
        }
    }

    public void ClearAll () {
        if (IsEmpty()) {
            System.out.println("Ошибка. Товары отсутствуют.");
            return;
        }
        Node temp = head;
        while (temp != tail) {
            temp.data.setRezerv(0);
            temp = temp.next;
        }
        temp.data.setRezerv(0);
    }

    public String PrintSpisok() {
        if (IsEmpty()) {
            return "\nОшибка. Товары отсутствуют.";
        }
        Node temp = head;
        double x;
        String msg = "";
        while (temp != null) {
            msg += "\n\nID: " + temp.data.getID() + "       Наименование товара: " + temp.data.getName() +
                    "\nКатегория: " + temp.data.getCath() + "     Цена: " + temp.data.getPrice() + " руб" +
                    "\nКоличество: " + temp.data.getBalance() + " шт.     Рейтинг: " + temp.data.getRate() + "/5     Скидка: " + temp.data.getDisc() + "%";
            x = temp.data.getPrice()-temp.data.getPrice()*temp.data.getDisc()/100;
            if (temp.data.getPrice() != x) {
                msg += "\nЦена с учётом скидки: " + x;
            }
            temp = temp.next;
        }
        if (msg.equals("")) {
            return "Товары отсутствуют";
        } else {
            return msg;
        }
    }

    public String PrintRezerv() {
        if (IsEmpty()) {
            return "\nОшибка. Товары отсутствуют.";
        }
        Node temp = head;
        double x, FinalPrice = 0f;
        String msg = "";
        while (temp != null) {
            if (temp.data.getRezerv() != 0) {
                msg += "\nID: " + temp.data.getID() + "     Товар: " + temp.data.getName() + "     В корзине: " + temp.data.getRezerv();
                x = (temp.data.getPrice()*temp.data.getRezerv())-(temp.data.getPrice()*temp.data.getRezerv()*temp.data.getDisc()/100);
                msg += "\nСтоимость: " + x;
                FinalPrice += x;
            }
            temp = temp.next;
        }
        if (msg.equals("")) {
            return "В корзине нет товаров.";
        } else {
            if (FinalPrice >= 1500) {
                msg += "\nДоставка бесплатно";
            } else if (FinalPrice >= 1000) {
                FinalPrice += 100f;
                msg += "\nДоставка 100 рублей";
            } else {
                FinalPrice += 200f;
                msg += "\nДоставка 200 рублей";
            }
            msg += "\nИтоговая сумма заказа = " + FinalPrice;
            return msg;
        }
    }

    public void Sklad () {
        if (IsEmpty()) {
            System.out.println("Ошибка. Товары отсутствуют.");
            return;
        }
        ClearAll();
        String i;
        Node temp;
        while (true) {
            System.out.print("\nВведите ID товара для изменений на складе: ");
            i = PrintLine();
            try {
                temp = head;
                int b = temp.data.getID();
                while (Integer.parseInt(i) != b) {
                    if (temp.next == null) {
                        b = temp.data.getID();
                        break;
                    }
                    temp = temp.next;
                    b = temp.data.getID();
                }
                if (Integer.parseInt(i) != b) {
                    System.out.println("Такого ID не существует.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Одна ошибка - и ты ошибся!");
            }
        }
        System.out.println("Идут изменения на складе для товара: " + temp.data.getName());
        System.out.println("В данный момент количество товара равно: " + temp.data.getBalance());
        System.out.println("Подсказка: для списания товара введите отрицательное число.");
        while (true) {
            System.out.print("\nВведите количество добавляемого (или списываемого) товара: ");
            i = PrintLine();
            try {
                if (Integer.parseInt(i) == 0) {
                    System.out.println("Изменений нет. На складе по-прежнему " + temp.data.getBalance() + " единиц товара.");
                    break;
                } else if (Integer.parseInt(i) > 0) {
                    temp.data.setBalance(temp.data.getBalance() + Integer.parseInt(i));
                    System.out.println("На складе теперь " + temp.data.getBalance() + " единиц товара.");
                    break;
                } else {
                    if (temp.data.getBalance() == 0) {
                        System.out.println("Ты издеваешься?");
                    } else if (Integer.parseInt(i) + temp.data.getBalance() >= 0) {
                        temp.data.setBalance(temp.data.getBalance() + Integer.parseInt(i));
                        System.out.println("На складе теперь " + temp.data.getBalance() + " единиц товара.");
                        break;
                    } else {
                        System.out.println("Ну это уже перебор, пацанчик. На складе нет столько.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Одна ошибка - и ты ошибся!");
            }
        }
    }

    public String Sale () {
        if (IsEmpty()) {
            return "\nОшибка. Товары отсутствуют.";
        }
        String check = "";
        if (!PrintRezerv().equals("В корзине нет товаров.")) {

            check +="\nДобро пожаловать в онлайн-магазин 'Рыба и пиво'" +
                    "\n               КАССОВЫЙ ЧЕК №5                 " +
                    "\nПриход                  " + DataTime() +
                    "\nСмена                                       555" +
                    "\nКассир                             Мендаль В.А." +
                    "\nИНН кассира                        482709584816" +
                    "\nЭл. адрес покупателя           tysobaka@mail.ru" +
                    "\nЭл. адрес отправителя         samsobaka@mail.ru" +
                    "\n";
            Node temp = head;
            int i;
            double x, FinalPrice = 0f;
            while (temp != null) {
                if (temp.data.getRezerv() != 0) {
                    x = (temp.data.getPrice() * temp.data.getRezerv()) - (temp.data.getPrice() * temp.data.getRezerv() * temp.data.getDisc() / 100);
                    check +="\n" + temp.data.getName() + "                                  " + temp.data.getRezerv() + " х " + temp.data.getPrice() +
                            "\nСкидка                                      " + temp.data.getDisc() + "%" +
                            "\nИтого                                     " + x +
                            "\n";
                    i = temp.data.getBalance() - temp.data.getRezerv();
                    temp.data.setBalance(i);
                    temp.data.setRezerv(0);
                    FinalPrice += x;
                }
                temp = temp.next;
            }
            if (FinalPrice >= 1500) {
                check +="\nДоставка                                    0.0";
            } else if (FinalPrice >= 1000) {
                FinalPrice += 100f;
                check +="\nДоставка                                  100.0";
            } else {
                FinalPrice += 200f;
                check +="\nДоставка                                  200.0";
            }
            check +="\n\nИтого                                     " + FinalPrice +
                    "\n               СПАСИБО ЗА ПОКУПКУ!             ";
        } else {
            check = "Корзина пуста.";
        }
        return check;
    }

    private String DataTime() {
        LocalDate Data = LocalDate.now();
        LocalTime Time = LocalTime.now();
        return Data + " " + Time;
    }
}
