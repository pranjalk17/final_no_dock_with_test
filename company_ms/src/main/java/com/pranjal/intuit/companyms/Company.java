package com.pranjal.intuit.companyms;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String description;

//    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
//    private List<Job> jobs;
//
//    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
//    private List<Review> reviews;

}