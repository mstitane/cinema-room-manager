package cinema;

import java.util.Scanner;

public class Cinema {

    private static int currentIncome = 0;
    private static int totalPurchasedTickets = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int a = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int b = sc.nextInt();
        String[][] seats = initSeats(a, b);
        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = sc.nextInt();

            switch (choice) {
            case 1:
                print2DArray(seats);
                break;
            case 2:
                buyTickets(sc, a, b, seats);
                break;
            case 3:
                printStats(a, b);
                break;
            case 0:
            default:
                break;
            }
        }
    }

    private static String[][] initSeats(int a, int b) {
        String[][] seats = new String[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                seats[i][j] = "S ";
            }
        }
        return seats;
    }

    private static void buyTickets(Scanner sc, int a, int b, String[][] seats) {
        int i = 0;
        int j = 0;
        while (true) {
            System.out.println("Enter a row number:");
            i = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            j = sc.nextInt();

            if (i > a || j > b) {
                System.out.println("Wrong input!");
                continue;
            }

            if ("B ".equals(seats[i - 1][j - 1])) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            break;
        }
        seats[i - 1][j - 1] = "B ";

        int total = a * b;
        int price;
        if (total < 60) {
            price = 10;
        } else {
            int nRows = seats.length;
            if (i <= (nRows / 2)) {
                price = 10;
            } else {
                price = 8;
            }
        }

        System.out.println("Ticket price: $" + price);
        currentIncome += price;
        totalPurchasedTickets++;
    }

    private static void printStats(int a, int b) {
        System.out.println("Number of purchased tickets: " + totalPurchasedTickets);
        double perc = (double) totalPurchasedTickets / (a * b);
        System.out.println(String.format("Percentage: %.2f%%", perc * 100));
        System.out.println("Current income: $" + currentIncome);

        int total = a * b;
        int income = 0;
        if (total < 60)
            income = 10 * total;
        else {
            income += a / 2 * b * 10;
            income += (a - (a / 2)) * b * 8;
        }
        System.out.println("Total income: $" + income);
    }

    static void print2DArray(String[][] seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int j = 0; j < seats[0].length; j++) {
            System.out.print(j + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}