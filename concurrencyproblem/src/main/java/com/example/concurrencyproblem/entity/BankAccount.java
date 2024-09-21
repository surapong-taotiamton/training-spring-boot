package com.example.concurrencyproblem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Accessors(chain = true)
@Data
@Entity
@Table
public class BankAccount {
    @Id
    private String bankAccountId;
    private BigDecimal amount;
}
