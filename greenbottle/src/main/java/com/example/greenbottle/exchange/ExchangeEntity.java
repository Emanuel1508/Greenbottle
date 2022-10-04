package com.example.greenbottle.exchange;

import com.example.greenbottle.bottle.Bottle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String transactionId = UUID.randomUUID().toString();

    String customerId;

    @OneToOne
    private Bottle bottle;
}
