package com.kayak.batchManager.ManagerControl.Client.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table (name = "tb_client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is necessary")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Phone is necessary")
    @Column(nullable = false, length = 100)
    private String phone;

    @NotBlank(message = "Email is necessary")
    @Email(message = "Email need to be valid")
    @Column(nullable = false, length = 100)
    private String email;

}
