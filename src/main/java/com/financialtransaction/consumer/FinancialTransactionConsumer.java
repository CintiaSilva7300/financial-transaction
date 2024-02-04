package com.financialtransaction.consumer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialtransaction.model.Installment;
import com.financialtransaction.model.Person;
import com.financialtransaction.model.Transaction;
import com.financialtransaction.service.ProccessFinancialTransactionService;

@Component
public class FinancialTransactionConsumer {

    @Autowired
    ProccessFinancialTransactionService proccessFinancialTransactionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "financial-transaction") // escutando na fila "financial-transaction"
    public void listen(String message) {

        try {
            // Converta a mensagem JSON para os objetos(Person e Transaction)
            JsonNode jsonNode = objectMapper.readTree(message);

            Person person = new Person();
            person.setId(jsonNode.get("cpf").asText());
            person.setName(jsonNode.get("name").asText());
            person.setAge(jsonNode.get("age").asInt());

            Transaction transaction = new Transaction();
            transaction.setId(jsonNode.get("id").asText());
            transaction.setPersonId(jsonNode.get("cpf").asText());
            transaction.setAmount(BigDecimal.valueOf(jsonNode.get("amount").asDouble()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(jsonNode.get("date").asText());
            transaction.setTransactionDate(date);

            Integer installmentNumber = jsonNode.get("installmentNumber").asInt();

            proccessFinancialTransactionService.execute(person, transaction, installmentNumber);
            // Chamar a service com as regras de negocio

        } catch (Exception e) {
            e.printStackTrace();
            // Lide com exceções apropriadamente
        }
    }

}
