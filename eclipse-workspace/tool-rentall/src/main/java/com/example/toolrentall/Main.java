package com.example.toolrentall;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<RentalAgreement> agreements = new ArrayList<>();

        try {
            Tool jackhammerRidgid = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);
            agreements.add(new RentalAgreement(jackhammerRidgid, 5, 0, LocalDate.of(2015, 9, 3)));

            Tool ladderWerner = new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false);
            agreements.add(new RentalAgreement(ladderWerner, 3, 10, LocalDate.of(2020, 7, 2)));

            Tool chainsawStihl = new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, false);
            agreements.add(new RentalAgreement(chainsawStihl, 5, 25, LocalDate.of(2015, 7, 2)));

            Tool jackhammerDeWalt = new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false);
            agreements.add(new RentalAgreement(jackhammerDeWalt, 6, 0, LocalDate.of(2015, 9, 3)));

            agreements.add(new RentalAgreement(jackhammerRidgid, 9, 0, LocalDate.of(2015, 7, 2)));

            agreements.add(new RentalAgreement(jackhammerRidgid, 4, 50, LocalDate.of(2020, 7, 2)));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        for (RentalAgreement agreement : agreements) {
            System.out.println("Tool code: " + agreement.getTool().getToolCode());
            System.out.println("Tool type: " + agreement.getTool().getToolType());
            System.out.println("Tool brand: " + agreement.getTool().getBrand());
            System.out.println("Rental days: " + agreement.getRentalDays());
            System.out.println("Check out date: " + agreement.getCheckoutDate().format(dateFormatter));
            System.out.println("Due date: " + agreement.getDueDate().format(dateFormatter));
            System.out.println("Daily rental charge: $" + String.format("%.2f", agreement.getTool().getDailyCharge()));
            System.out.println("Charge days: " + agreement.getChargeDays());
            System.out.println("Pre-discount charge: $" + String.format("%.2f", agreement.getPreDiscountCharge()));
            System.out.println("Discount percent: " + String.format("%.0f", agreement.getDiscountPercent()) + "%");
            System.out.println("Discount amount: $" + String.format("%.2f", agreement.getDiscountAmount()));
            System.out.println("Final charge: $" + String.format("%.2f", agreement.getFinalCharge()));
            System.out.println("============================================");
        }
    }
}
