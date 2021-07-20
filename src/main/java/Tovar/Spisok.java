package Tovar;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import java.util.Scanner;

public class Spisok {

    private Node Head;
    private Node Tail;

    public Spisok() {
        Head = null;
        Tail = null;
    }

    public void AddTovar(Tovar Data) {
        Node temp = new Node();
        temp.setNext(null);
        temp.setData(Data);
        if (IsEmpty()) {
            temp.setPrev(null);
            Head = Tail = temp;
        } else {
            Node cur = Head;
            int a = temp.getData().getID();
            int b = cur.getData().getID();
            while (a > b) {
                if (cur.getNext() == null) {
                    break;
                }
                cur = cur.getNext();
                b = cur.getData().getID();
            }
            if (a > b) {
                Tail.setNext(temp);
                temp.setPrev(Tail);
                Tail = temp;
            } else if (a == b) {
                System.out.println("Такой ID уже существует.");
                return;
            } else if (cur == Head) {
                Head.setPrev(temp);
                temp.setNext(Head);
                Head = temp;
            } else {
                cur.getPrev().setNext(temp);
                temp.setPrev(cur.getPrev());
                cur.setPrev(temp);
                temp.setNext(cur);
            }
        }
    }

    public void PrintSpisok() {
        if (IsEmpty()) {
            System.out.println("Ошибка. Товары отсутствуют.");
            return;
        }
        Node temp = Head;
        while (temp != null) {
            System.out.println(temp.getData().toString(1));
            temp = temp.getNext();
        }
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
                temp = Head;
                int b = temp.getData().getID();
                while (Integer.parseInt(i) != b) {
                    if (temp.getNext() == null) {
                        b = temp.getData().getID();
                        break;
                    }
                    temp = temp.getNext();
                    b = temp.getData().getID();
                }
                if (Integer.parseInt(i) != b) {
                    System.out.println("Такого ID не существует.");
                } else {
                    System.out.println("Расчёт стоимости для товара: " + temp.getData().getName());
                    System.out.println("Единица товара стоит: " + temp.getData().getPrice());
                    x = temp.getData().getPrice()-temp.getData().getPrice()*temp.getData().getDisc()/100;
                    if (x != temp.getData().getPrice()) {
                        System.out.println("Скидка на товар: " + temp.getData().getDisc() + "%");
                        System.out.println("С учётом скидки: " + x);
                    }
                    break;
                }
            } catch (Exception e) {System.out.println("Одна ошибка - и ты ошибся!");}
        }
        while (true) {
            System.out.print("Введите количество необходимого товара: ");
            i = PrintLine();
            try {
                if (Integer.parseInt(i) < 0) {
                    System.out.println("Вы ввели отрицательное число.");
                } else {
                    x = (temp.getData().getPrice()*Integer.parseInt(i))-(temp.getData().getPrice()*Integer.parseInt(i)*temp.getData().getDisc()/100);
                    System.out.println("Товар " + temp.getData().getName() + " в количестве " + i + " будет стоить " + x + " рублей");
                    if (Integer.parseInt(i) > temp.getData().getBalance()) {
                        System.out.println("Предупреждаем, что у нас " + temp.getData().getBalance() + " единиц товара на складе."); }
                    break;
                }
            } catch (Exception e) {System.out.println("Одна ошибка - и ты ошибся!");}
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
                temp = Head;
                int b = temp.getData().getID();
                while (Integer.parseInt(i) != b) {
                    if (temp.getNext() == null) {
                        b = temp.getData().getID();
                        break;
                    }
                    temp = temp.getNext();
                    b = temp.getData().getID();
                }
                if (Integer.parseInt(i) != b) {
                    System.out.println("Такого ID не существует.");
                } else if (temp.getData().getBalance() == 0) {
                    System.out.println("Товар " + temp.getData().getName() + ", к сожалению, закончился. Выберите другой товар.");
                } else {
                    System.out.println("Вы выбрали товар: " + temp.getData().getName());
                    break;
                }
            } catch (Exception e) {System.out.println("Одна ошибка - и ты ошибся!");}
        }
        if (temp.getData().getRezerv() != 0) {
            System.out.println("Подсказка: в данный момент вы зарезервировали " + temp.getData().getRezerv() + " единиц товара.");
            System.out.println("Для удаления товара из корзины введите 0.");
        }
        while (true) {
            System.out.print("Введите количество товара, добавляемого в корзину: ");
            i = PrintLine();
            try {
                if (Integer.parseInt(i) < 0) {
                    System.out.println("Вы ввели отрицательное число.");
                } else if (Integer.parseInt(i) > temp.getData().getBalance()) {
                    System.out.println("На складе нет столько товаров. Сейчас на складе " + temp.getData().getBalance() + " единиц товара.");
                } else if (Integer.parseInt(i) == 0 && temp.getData().getRezerv() != 0) {
                    temp.getData().setRezerv(Integer.parseInt(i));
                    System.out.println("Товар удалён.");
                    break;
                } else if (Integer.parseInt(i) == 0 && temp.getData().getRezerv() == 0) {
                    System.out.println("Товар не добавлен.");
                    break;
                } else {
                    temp.getData().setRezerv(Integer.parseInt(i));
                    System.out.println("Товар зарезервирован.");
                    break;
                }
            } catch (Exception e) {System.out.println("Одна ошибка - и ты ошибся!");}
        }
    }

    public void PrintRezerv() {
        if (IsEmpty()) {
            System.out.println("Ошибка. Товары отсутствуют.");
            return;
        }
        Node temp = Head;
        double x, FinalPrice = 0f;
        while (temp != null) {
            if (temp.getData().getRezerv() != 0) {
                System.out.println(temp.getData().toString(2));
                x = (temp.getData().getPrice()*temp.getData().getRezerv())-(temp.getData().getPrice()*temp.getData().getRezerv()*temp.getData().getDisc()/100);
                FinalPrice += x;
            }
            temp = temp.getNext();
        }
        if (FinalPrice == 0f) {
            System.out.println("В корзине нет товаров.");
        } else {
            if (FinalPrice >= 1500) {
                System.out.println("\nДоставка бесплатно");
            } else if (FinalPrice >= 1000) {
                FinalPrice += 100f;
                System.out.println("\nДоставка 100 рублей");
            } else {
                FinalPrice += 200f;
                System.out.println("\nДоставка 200 рублей");
            }
            System.out.println("\nИтоговая сумма заказа = " + FinalPrice);
        }
    }

    public void ClearAll () {
        if (IsEmpty()) {
            System.out.println("Ошибка. Товары отсутствуют.");
            return;
        }
        Node temp = Head;
        while (temp != Tail) {
            temp.getData().setRezerv(0);
            temp = temp.getNext();
        }
        temp.getData().setRezerv(0);
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
                temp = Head;
                int b = temp.getData().getID();
                while (Integer.parseInt(i) != b) {
                    if (temp.getNext() == null) {
                        b = temp.getData().getID();
                        break;
                    }
                    temp = temp.getNext();
                    b = temp.getData().getID();
                }
                if (Integer.parseInt(i) != b) {
                    System.out.println("Такого ID не существует.");
                } else {
                    break;
                }
            } catch (Exception e) {System.out.println("Одна ошибка - и ты ошибся!");}
        }
        System.out.println("Идут изменения на складе для товара: " + temp.getData().getName());
        System.out.println("В данный момент количество товара равно: " + temp.getData().getBalance());
        System.out.println("Подсказка: для списания товара введите отрицательное число.");
        while (true) {
            System.out.print("\nВведите количество добавляемого (или списываемого) товара: ");
            i = PrintLine();
            try {
                if (Integer.parseInt(i) == 0) {
                    System.out.println("Изменений нет. На складе по-прежнему " + temp.getData().getBalance() + " единиц товара.");
                    break;
                } else if (Integer.parseInt(i) > 0) {
                    temp.getData().setBalance(temp.getData().getBalance() + Integer.parseInt(i));
                    System.out.println("На складе теперь " + temp.getData().getBalance() + " единиц товара.");
                    break;
                } else {
                    if (temp.getData().getBalance() == 0) {
                        System.out.println("Ты издеваешься?");
                    } else if (Integer.parseInt(i) + temp.getData().getBalance() >= 0) {
                        temp.getData().setBalance(temp.getData().getBalance() + Integer.parseInt(i));
                        System.out.println("На складе теперь " + temp.getData().getBalance() + " единиц товара.");
                        break;
                    } else {
                        System.out.println("Ну это уже перебор, пацанчик. На складе нет столько.");
                    }
                }
            } catch (Exception e) {System.out.println("Одна ошибка - и ты ошибся!");}
        }
    }

    public String Sale () {
        if (IsEmpty()) {return "\nОшибка. Товары отсутствуют.";}
        String check = "";
        if (CheckRezerv() == 1) {
            check +="\nДобро пожаловать в онлайн-магазин 'Рыба и пиво'" +
                    "\n               КАССОВЫЙ ЧЕК №5                 " +
                    "\nПриход                  " + DataTime() +
                    "\nСмена                                       555" +
                    "\nКассир                             Мендаль В.А." +
                    "\nИНН кассира                        482709584816" +
                    "\nЭл. адрес покупателя       motherrussia@mail.ru" +
                    "\nЭл. адрес отправителя         harmatter@mail.ru" +
                    "\n";
            Node temp = Head;
            int i;
            double FinalPrice = 0f;
            while (temp != null) {
                if (temp.getData().getRezerv() != 0) {
                    FinalPrice += (temp.getData().getPrice() * temp.getData().getRezerv()) - (temp.getData().getPrice() * temp.getData().getRezerv() * temp.getData().getDisc() / 100);
                    check += temp.getData().toString(3);
                }
                temp = temp.getNext();
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
        } else {check = "В корзине нет товаров.";}
        return check;
    }

    private boolean IsEmpty() {return Head == null;}

    private int CheckRezerv() {
        Node temp = Head;
        int x = 0;
        while (temp != null) {
            if (temp.getData().getRezerv() != 0) {
                x = 1;
            }
            temp = temp.getNext();
        }
        return x;
    }

    private String PrintLine() {
        String enter;
        Scanner input = new Scanner(System.in);
        enter = input.nextLine();
        return enter;
    }

    private String DataTime() {
        LocalDate Data = LocalDate.now();
        LocalTime Time = LocalTime.now();
        return Data + " " + Time;
    }


}
