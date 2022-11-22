package com.RL.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please provide name")
    @Size(min = 4, max = 70, message = "Your name '${validatedValue}' must be between {min} and {max} chars long")
    @Column(length = 70, nullable = false)
    private String name;

    @Email(message = "Please provide valid email")
    @NotNull(message="Please provide email")
    @Size(min=10, max=80,message="Email '${validatedValue}' must be between {min} and {max} chars long")
    @Column(length = 80,nullable = false)
    private String email;

    @Size(min=5,max=50,message="Your subject '${validatedValue}' must be between {min} and {max} chars long")
    @NotNull(message="Please provide message subject")
    @Column(length = 50,nullable = false)
    private String subject;


    @Size(min=20,max=200,message="Your message body '${validatedValue}' must be between {min} and {max} chars long")
    @NotNull(message="Please provide message body")
    @Column(length = 200,nullable = false)
    private String body;

    @NotNull(message = "Please provide select builtIn")
    @Column(nullable = false)
    private Boolean isread = false;

    public Contact(String name, String email, String subject, String body, Boolean isread) {
        this.name = name;
        this.email= email;
        this.subject= subject;
        this.body= body;
        this.isread = isread;
    }

}
