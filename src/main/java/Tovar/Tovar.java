package Tovar;

import java.util.Scanner;

public class Tovar {
    private int ID;
    private String Name;
    private String Cath;
    private double Price;
    private int Balance;
    private int Rezerv;
    private float Rate;
    private int Disc;

    public Tovar (int ID, String Name, String Cath, double Price, int Balance, float Rate, int Disc) {
        this.ID = ID;
        this.Name = Name;
        this.Cath = Cath;
        this.Price = Price;
        this.Balance = Balance;
        this.Rate = Rate;
        this.Disc = Disc;
    }
    public Tovar () {
        this.ID = 0;
        this.Name = "Пустая позиция";
        this.Cath = "Категория не выбрана";
        this.Price = 0f;
        this.Balance = 0;
        this.Rate = 0f;
        this.Disc = 0;
    }
    public void setID(int ID){this.ID = ID;}
    public int getID(){return this.ID;}
    public void setName(String Name){this.Name = Name;}
    public String getName(){return this.Name;}
    public void setCath(String Cath){this.Cath = Cath;}
    public String getCath(){return this.Cath;}
    public void setPrice(double Price){this.Price = Price;}
    public double getPrice(){return this.Price;}
    public void setBalance(int Balance){this.Balance = Balance;}
    public int getBalance(){return this.Balance;}
    public void setRezerv(int Rezerv){this.Rezerv = Rezerv;}
    public int getRezerv(){return this.Rezerv;}
    public void setRate(float Rate){this.Rate = Rate;}
    public float getRate(){return this.Rate;}
    public void setDisc(int Disc){this.Disc = Disc;}
    public int getDisc(){return this.Disc;}
}
