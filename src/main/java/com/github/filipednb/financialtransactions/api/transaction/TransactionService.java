package com.github.filipednb.financialtransactions.api.transaction;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionService {

    List<TransactionEntity> getAll();

    void uploadFile(MultipartFile file);

    TransactionsResume getTransactionsResumeByStoreName(String storeName);

}
