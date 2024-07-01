package com.example.toolrentall;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class RentalAgreementTest {

    @Test
    public void testCase1() {
        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RentalAgreement(tool, 5, 101, LocalDate.of(2015, 9, 3));
        });
        assertEquals("Discount percent must be in the range 0-100.", exception.getMessage());
    }

    @Test
    public void testCase2() {
        Tool tool = new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false);
        RentalAgreement rental = new RentalAgreement(tool, 3, 10, LocalDate.of(2020, 7, 2));

        rental.calculateCharges();
        assertEquals(3, rental.getRentalDays());
        assertEquals(2, rental.getChargeDays());  // Ladder charges for weekdays and weekends, but not holidays
        assertEquals(1.99, rental.getTool().getDailyCharge(), 0.01);
        assertEquals(10, rental.getDiscountPercent(), 0.01);
        assertEquals(3.98, rental.getPreDiscountCharge(), 0.01);
        assertEquals(0.40, rental.getDiscountAmount(), 0.01);
        assertEquals(3.58, rental.getFinalCharge(), 0.01);
    }

    @Test
    public void testCase3() {
        Tool tool = new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
        RentalAgreement rental = new RentalAgreement(tool, 5, 25, LocalDate.of(2015, 7, 2));

        rental.calculateCharges();
        assertEquals(5, rental.getRentalDays());
        assertEquals(3, rental.getChargeDays());  // Chainsaw charges for weekdays and holidays, but not weekends
        assertEquals(1.49, rental.getTool().getDailyCharge(), 0.01);
        assertEquals(25, rental.getDiscountPercent(), 0.01);
        assertEquals(4.47, rental.getPreDiscountCharge(), 0.01);
        assertEquals(1.12, rental.getDiscountAmount(), 0.01);
        assertEquals(3.35, rental.getFinalCharge(), 0.01);
    }

    @Test
    public void testCase4() {
    
            Tool tool = new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false);
            RentalAgreement rental = new RentalAgreement(tool, 6, 0, LocalDate.of(2015, 9, 3));

            rental.calculateCharges();
            assertEquals(6, rental.getRentalDays());
            assertEquals(3, rental.getChargeDays());  // Adjusted: Jackhammer charges for 9/4 (Friday), 9/7 (Monday, observed Labor Day), 9/8 (Tuesday)
            assertEquals(2.99, rental.getTool().getDailyCharge(), 0.01);
            assertEquals(0, rental.getDiscountPercent(), 0.01);
            assertEquals(8.97, rental.getPreDiscountCharge(), 0.01);
            assertEquals(0.00, rental.getDiscountAmount(), 0.01);
            assertEquals(8.97, rental.getFinalCharge(), 0.01);
    }

    @Test
    public void testCase5() {
        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);
        RentalAgreement rental = new RentalAgreement(tool, 9, 0, LocalDate.of(2015, 7, 2));

        rental.calculateCharges();
        assertEquals(9, rental.getRentalDays());
        assertEquals(5, rental.getChargeDays());  // Jackhammer charges only for weekdays
        assertEquals(2.99, rental.getTool().getDailyCharge(), 0.01);
        assertEquals(0, rental.getDiscountPercent(), 0.01);
        assertEquals(14.95, rental.getPreDiscountCharge(), 0.01);
        assertEquals(0.00, rental.getDiscountAmount(), 0.01);
        assertEquals(14.95, rental.getFinalCharge(), 0.01);
    }

    @Test
    public void testCase6() {
        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);
        RentalAgreement rental = new RentalAgreement(tool, 4, 50, LocalDate.of(2020, 7, 2));

        rental.calculateCharges();
        assertEquals(4, rental.getRentalDays());
        assertEquals(1, rental.getChargeDays());  // Jackhammer charges only for weekdays (Friday, 7/3/20)
        assertEquals(2.99, rental.getTool().getDailyCharge(), 0.01);
        assertEquals(50, rental.getDiscountPercent(), 0.01);
        assertEquals(2.99, rental.getPreDiscountCharge(), 0.01);
        assertEquals(1.50, rental.getDiscountAmount(), 0.01);
        assertEquals(1.49, rental.getFinalCharge(), 0.01);
    }
}
