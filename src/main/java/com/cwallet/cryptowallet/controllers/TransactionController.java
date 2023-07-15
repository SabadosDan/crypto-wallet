package com.cwallet.cryptowallet.controllers;

import com.cwallet.cryptowallet.controllers.responses.BaseResponse;
import com.cwallet.cryptowallet.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping(value = "")
    private ResponseEntity<BaseResponse> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping(value = "/greater_than/{amountOfUsd}")
    private ResponseEntity<BaseResponse> getAllTransactionsGreaterThan(@PathVariable("amountOfUsd") Double amountOfUsd){
        return new ResponseEntity<>(transactionService.getAllTransactionsGreaterThan(amountOfUsd), HttpStatus.OK);
    }
}
