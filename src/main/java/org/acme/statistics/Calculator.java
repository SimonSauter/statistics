package org.acme.statistics;
import java.util.*;

public class Calculator {

    public static double sumFromRecentTransactions(List<Transaction> recentTransactions) {
        return recentTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static double averageFromRecentTransactions(List<Transaction> recentTransactions) {
        OptionalDouble average = recentTransactions.stream()
                .map(Transaction::getAmount)
                .mapToDouble(Double::doubleValue)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0.0;
    }

    public static double maxFromRecentTransactions(List<Transaction> recentTransactions) {
        OptionalDouble max = recentTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .max();
        return max.isPresent() ? max.getAsDouble() : 0.0;
    }

    public static double minFromRecentTransactions(List<Transaction> recentTransactions) {
        OptionalDouble min = recentTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .min();
        return min.isPresent() ? min.getAsDouble() : 0.0;

    }

    public static long amountOfRecentTransactions(List<Transaction> recentTransactions) {
        return recentTransactions.size();
    }

}
