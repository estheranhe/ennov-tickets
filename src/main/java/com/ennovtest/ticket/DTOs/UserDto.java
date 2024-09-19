package com.ennovtest.ticket.DTOs;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ennovtest.ticket.Entities.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final String email;
    private final String username;
}