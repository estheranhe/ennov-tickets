package com.ennovtest.ticket.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(name = "description")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String description;

    @Column(name = "title", nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status")
    @JdbcTypeCode(SqlTypes.CHAR)
    private TicketStatus ticketStatus;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}