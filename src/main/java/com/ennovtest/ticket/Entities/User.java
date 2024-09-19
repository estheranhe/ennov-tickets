package com.ennovtest.ticket.Entities;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, orphanRemoval = true)
    @OrderBy
    private List<Ticket> tickets = new ArrayList<>();

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}