package com.financialtransaction.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financialtransaction.model.Installment;
import com.financialtransaction.model.Person;
import com.financialtransaction.model.Transaction;
import com.financialtransaction.repository.InstallmentRepository;
import com.financialtransaction.repository.PersonRepository;
import com.financialtransaction.repository.TransactionRepository;

@Service
public class ProccessFinancialTransactionService {

    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;
    private final InstallmentRepository installmentRepository;

    @Autowired
    public ProccessFinancialTransactionService(PersonRepository personRepository,
            TransactionRepository transactionRepository, InstallmentRepository installmentRepository) {
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
        this.installmentRepository = installmentRepository;
    }

    public void execute(Person person, Transaction transaction, Integer installmentNumber) {

        // Verificar se a pessoa ja existe
        Optional<Person> personFinded = personRepository.findById(person.getId());
        UUID uuid = UUID.randomUUID();

        if (!personFinded.isPresent()) { // Caso ela não exista, criar a pessoa
            personRepository.save(person);
        }

        transactionRepository.save(transaction); // salvar a transaction

        @SuppressWarnings("deprecation") // Divisão do valor total da transação pelo número de parcelas
        BigDecimal installmentValue = transaction.getAmount().divide(BigDecimal.valueOf(installmentNumber),
                BigDecimal.ROUND_HALF_UP); // calcula o valor da parcela

        for (int i = 0; i < installmentNumber; i++) {
            Installment installment = new Installment();
            installment.setId(uuid.randomUUID().toString());
            installment.setTransactionId(transaction.getId());
            installment.setInstallmentNumber(i + 1);
            installment.setValue(installmentValue);
            installmentRepository.save(installment); // para cada parcela salvar
        }

    }
}
