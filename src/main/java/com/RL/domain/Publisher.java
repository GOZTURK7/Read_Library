package com.RL.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message="Name can not be null")
    @NotBlank(message="Name can not be white space")
    @Size(min=2,max=50, message="Name '${validatedValue}' must be between {min} and {max} chars long")
    @Column(length = 50, nullable = false)
    private String name;

    private Boolean builtIn=false;

    @OneToMany(mappedBy="publisherId")
    @JsonIgnoreProperties("publisherId")
    private List<Book> books;


}