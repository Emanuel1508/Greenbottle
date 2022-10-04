package com.example.greenbottle.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id = UUID.randomUUID().toString();


    private String name;
    private String phoneNumber;
    private int balance;
    private int sellingLevel;
    private int experience;
    private int bottleQuantity;


}
