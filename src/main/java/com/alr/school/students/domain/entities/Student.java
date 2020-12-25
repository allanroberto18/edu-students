package com.alr.school.students.domain.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 125)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 125)
    private String lastName;

    @Column(name = "email", nullable = false, length = 125)
    private String email;

}
