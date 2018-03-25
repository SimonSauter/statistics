package org.acme.statistics;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utilities {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Long get60SecondCutoff() {
        return (Instant.now().toEpochMilli() - 60000);
    }

    public static boolean transactionOlderThan60Seconds(Transaction transaction, Long sixtySecondsAgo) {
        return (transaction.getTimestampMillis() < sixtySecondsAgo) ? true : false;
    }

    public static Map<String, Object> compileStats(List<Transaction> recentTransactions) {
        Map<String, Object> statistics = new LinkedHashMap<>();
        statistics.put("sum", round(Calculator.sumFromRecentTransactions(recentTransactions), 0));
        statistics.put("avg", round(Calculator.averageFromRecentTransactions(recentTransactions),0));
        statistics.put("max", round(Calculator.maxFromRecentTransactions(recentTransactions),0));
        statistics.put("min", round(Calculator.minFromRecentTransactions(recentTransactions),0));
        statistics.put("count", Calculator.amountOfRecentTransactions(recentTransactions));
        return statistics;
    }

}
