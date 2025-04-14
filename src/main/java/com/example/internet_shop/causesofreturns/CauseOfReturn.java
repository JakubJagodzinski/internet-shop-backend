package com.example.internet_shop.causesofreturns;

import jakarta.persistence.*;

@Entity
@Table(name = "causes_of_returns")
public class CauseOfReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cause_of_return_id")
    private Long causeOfReturnId;

    @Column(nullable = false, unique = true, length = 100)
    private String cause;

}
