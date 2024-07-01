package com.example.toolrentall;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class RentalAgreement {
    private Tool tool;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int rentalDays;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountPercent;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, double discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be in the range 0-100.");
        }

        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDays - 1);
        calculateCharges();
    }

    public void calculateCharges() {
        chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1); // Start from the day after checkout

        for (int i = 0; i < rentalDays; i++) {
            boolean isHoliday = HolidayUtil.isHoliday(currentDate);
            boolean isWeekend = isWeekend(currentDate);
            if (tool.isWeekdayCharge() && !isWeekend && !isHoliday) {
                chargeDays++;
            } else if (tool.isWeekendCharge() && isWeekend) {
                chargeDays++;
            } else if (tool.isHolidayCharge() && isHoliday) {
                chargeDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        preDiscountCharge = Math.round(chargeDays * tool.getDailyCharge() * 100.0) / 100.0;
        discountAmount = Math.round((discountPercent / 100) * preDiscountCharge * 100.0) / 100.0;
        finalCharge = Math.round((preDiscountCharge - discountAmount) * 100.0) / 100.0;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public void printAgreement() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.println("Daily rental charge: $" + String.format("%.2f", tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: $" + String.format("%.2f", preDiscountCharge));
        System.out.println("Discount percent: " + String.format("%.0f", discountPercent) + "%");
        System.out.println("Discount amount: $" + String.format("%.2f", discountAmount));
        System.out.println("Final charge: $" + String.format("%.2f", finalCharge));
    }

    // Getters
    public Tool getTool() {
        return tool;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }
}
