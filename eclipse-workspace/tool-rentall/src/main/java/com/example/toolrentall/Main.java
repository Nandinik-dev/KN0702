package com.example.toolrentall;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       

        createAndPrintAgreement(new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false), 5, 102, LocalDate.of(2015, 9, 3));
        createAndPrintAgreement(new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false), 0, 10, LocalDate.of(2020, 7, 2));
        createAndPrintAgreement(new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, false), 5, 25, LocalDate.of(2015, 7, 2));
        createAndPrintAgreement(new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false), 6, 0, LocalDate.of(2015, 9, 3));
        createAndPrintAgreement(new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false), 9, 0, LocalDate.of(2015, 7, 2));
        createAndPrintAgreement(new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false), 4, 50, LocalDate.of(2020, 7, 2));
    }

    private static void createAndPrintAgreement(Tool tool, int rentalDays, double discountPercent, LocalDate checkoutDate) {
        try {
            RentalAgreement agreement = new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate);
            agreement.printAgreement();
            
            System.out.println("===============================");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("===============================");
        }
        
        
        
    }
}