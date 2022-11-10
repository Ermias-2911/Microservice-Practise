package com.dailycodebuffer.user.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    @NotBlank
    @Size(min=3, max= 100)
    private String firstName;
    @NotBlank
    @Size(min=3, max= 100)
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private Long departmentId;
}
