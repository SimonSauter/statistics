package org.acme.statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/api")
public class TransactionsController {

    @Autowired
    TransactionRepository transactionRepo;

    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<String> saveNewTransaction(@RequestBody Transaction transaction) {
        Long sixtySecondsAgo = Utilities.get60SecondCutoff();
        if (Utilities.transactionOlderThan60Seconds(transaction, sixtySecondsAgo)) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            transactionRepo.save(transaction);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(path = "/statistics")
    public ResponseEntity<Map<String, Object>> statisticsForLast60Seconds() {
        Long latest60SecondCutoff = Utilities.get60SecondCutoff();
        List<Transaction> recentTransactions = transactionRepo.findByTimestampMillisGreaterThan(latest60SecondCutoff);
        Map<String, Object> statistics = Utilities.compileStats(recentTransactions);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

}
