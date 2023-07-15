package com.cwallet.cryptowallet.services;

import com.cwallet.cryptowallet.controllers.responses.ListTransactionResponse;
import com.cwallet.cryptowallet.controllers.responses.TransactionResponse;
import com.cwallet.cryptowallet.domain.dtos.Transaction;
import com.cwallet.cryptowallet.domain.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /** return a list with all transactions */
    public ListTransactionResponse getAllTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction transaction : transactions){
            transactionResponses.add(new TransactionResponse(transaction.getId(),
                    transaction.getDate(), transaction.getCoin(), transaction.getAmount(),
                    transaction.getTotalValue()));
        }
        return new ListTransactionResponse(transactionResponses);
    }

    /** return a list with all transactions greater than 'amountOfUsd' */
    public ListTransactionResponse getAllTransactionsGreaterThan(Double amountOfUsd){
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction transaction : transactions){
            if (transaction.getTotalValue() > amountOfUsd){
                transactionResponses.add(new TransactionResponse(transaction.getId(),
                        transaction.getDate(), transaction.getCoin(), transaction.getAmount(),
                        transaction.getTotalValue()));
            }
        }
        return new ListTransactionResponse(transactionResponses);
    }
}
