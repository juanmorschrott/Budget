package com.habitissimo.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private State state;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}