package com.ennovtest.ticket.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Email(message = "email invalide")
    @NotBlank(message = "email obligatoire")
    @NotEmpty(message = "email obligatoire")
    @NotNull(message = "email obligatoire")
    @Column(name = "email", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, orphanRemoval = true)
    @OrderBy
    @JsonIgnore
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

}