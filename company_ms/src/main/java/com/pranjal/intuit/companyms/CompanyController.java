package com.pranjal.intuit.companyms;


import com.pranjal.intuit.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @Valid @RequestBody Company company){
        try {
            companyService.updateCompany(company, id);
            return ResponseEntity.ok("Company updated successfully");
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@Valid @RequestBody Company company){
        System.out.println("--------------company 2--------------------J");
        System.out.println(company);
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        try {
            System.out.println("try 1-----");
            companyService.deleteCompanyById(id);
            return ResponseEntity.ok("Company Successfully Deleted");
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        try {
            Company company = companyService.getCompanyById(id);
            return ResponseEntity.ok(company);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }





}
