package com.twu.refactoring;

public class Receipt {
    private static final int fixed_Charge = 50;
    private static final double peak_Time_Multiplier = 1.2;
    private static final double off_Peak_Multiplier = 1.0;
    private static final int rate_Change_Distance = 10;
    private static final int pre_Rate_Change_Non_Ac_Rate = 15;
    private static final int post_Rate_Change_Non_Ac_Rate = 12;
    private static final int pre_Rate_Change_Ac_Rate = 20;
    private static final int post_Rate_Change_Ac_Rate = 17;
    private static final double sales_Tax_Rate = 0.1;

    private final Taxi taxi;

    public Receipt(Taxi taxi) {
        this.taxi = taxi;
    }

    public double getTotalCost() {
        double totalCost = 0;

        // fixed charges
        totalCost += fixed_Charge;

        // taxi charges
        int totalKms = taxi.getTotalKms();
        double peakTimeMultiple = taxi.isPeakTime() ? peak_Time_Multiplier : off_Peak_Multiplier;
        if(taxi.isAirConditioned()) {
            totalCost = getTotalCost(totalCost, totalKms, peakTimeMultiple, pre_Rate_Change_Ac_Rate, post_Rate_Change_Ac_Rate);
        } else {
            totalCost = getTotalCost(totalCost, totalKms, peakTimeMultiple, pre_Rate_Change_Non_Ac_Rate, post_Rate_Change_Non_Ac_Rate);
        }

        return totalCost * (1 + sales_Tax_Rate);
    }

    private double getTotalCost(double totalCost, int totalKms, double peakTimeMultiple, int pre_rate_change_ac_rate, int post_rate_change_ac_rate) {
        totalCost += Math.min(rate_Change_Distance, totalKms) * pre_rate_change_ac_rate * peakTimeMultiple;
        totalCost += Math.max(0, totalKms - rate_Change_Distance) * post_rate_change_ac_rate * peakTimeMultiple;
        return totalCost;
    }
}
