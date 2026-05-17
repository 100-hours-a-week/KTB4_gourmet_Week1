package org.example;

import java.util.Scanner;

class Product {
    protected String name;
    protected int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class Drink extends Product {
    protected int volume;

    public Drink(String name, int price, int volume) {
        super(name, price);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}

class Soda extends Drink {
    public Soda(String name, int price, int volume) {
        super(name, price, volume);
    }

    public void printInfo(int number) {
        System.out.println(number + ". " + name + " - " + price + "원");
    }
}

class ChangeCalculator {
    private final int[] moneyUnits = {50000, 10000, 5000, 1000, 500, 100};

    public void printChange(int change) {
        System.out.println();
        System.out.println("얼마를 거스름 돈으로 드릴게요: " + change + "원");

        for (int unit : moneyUnits) {
            int count = change / unit;

            if (count > 0) {
                if (unit >= 1000) {
                    System.out.println(unit + "원: " + count + "장");
                } else {
                    System.out.println(unit + "원: " + count + "개");
                }

                change %= unit;
            }
        }
    }
}

// 자판기 클래스
class VendingMachine {
    private final Soda[] drinks;
    private final ChangeCalculator changeCalculator;

    public VendingMachine() {
        drinks = new Soda[5];

        drinks[0] = new Soda("콜라", 1000, 500);
        drinks[1] = new Soda("사이다", 1500, 500);
        drinks[2] = new Soda("환타", 2000, 500);
        drinks[3] = new Soda("커피", 2500, 300);
        drinks[4] = new Soda("이온음료", 3000, 500);

        changeCalculator = new ChangeCalculator();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== 음료수 자판기 =====");
        System.out.println("1. 원하시는 음료수 버튼을 눌러주세요. (1~5)");
        System.out.println("=================================================");

        printMenu();

        System.out.print("선택: ");
        int selectedNumber = scanner.nextInt();

        if (selectedNumber < 1 || selectedNumber > 5) {
            System.out.println("잘못된 번호를 입력했습니다.");
            System.out.println("4. 이용해 주셔서 감사합니다.");
            scanner.close();
            return;
        }

        Soda selectedDrink = drinks[selectedNumber - 1];

        System.out.println();
        System.out.println(selectedDrink.getName() + "를 선택하셨습니다.");
        System.out.println("가격은 " + selectedDrink.getPrice() + "원입니다.");

        System.out.println();
        System.out.println("2. 원하는 금액을 투입 해주세요");
        System.out.print("투입 금액: ");
        int inputMoney = scanner.nextInt();

        if (inputMoney < selectedDrink.getPrice()) {
            System.out.println("투입 금액이 부족합니다.");
            System.out.println("투입하신 " + inputMoney + "원을 반환합니다.");
            System.out.println("4. 이용해 주셔서 감사합니다.");
            scanner.close();
            return;
        }

        int change = inputMoney - selectedDrink.getPrice();

        System.out.println();
        System.out.println(selectedDrink.getName() + "가 나왔습니다.");

        System.out.println();
        System.out.println("3. 거스름돈 반환");

        if (change > 0) {
            changeCalculator.printChange(change);
        } else {
            System.out.println("거스름돈은 없습니다.");
        }

        System.out.println();
        System.out.println("4. 이용해 주셔서 감사합니다.");

        scanner.close();
    }

    private void printMenu() {
        for (int i = 0; i < drinks.length; i++) {
            drinks[i].printInfo(i + 1);
        }
    }
}

// 실행 클래스
public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.start();
    }
}