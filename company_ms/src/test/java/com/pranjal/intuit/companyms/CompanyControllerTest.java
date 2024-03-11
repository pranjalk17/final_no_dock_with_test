package com.pranjal.intuit.companyms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    public void getAllCompaniesTest() throws Exception {
        Company company1 = new Company(1L, "Company One", "Description One");
        Company company2 = new Company(2L, "Company Two", "Description Two");
        List<Company> companyList = Arrays.asList(company1, company2);

        given(companyService.getAllCompanies()).willReturn(companyList);

        mockMvc.perform(get("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].id").value(company1.getId()))
                .andExpect(jsonPath("$[0].name").value(company1.getName()))
                .andExpect(jsonPath("$[0].description").value(company1.getDescription()))

                .andExpect(jsonPath("$[1].id").value(company2.getId()))
                .andExpect(jsonPath("$[1].name").value(company2.getName()))
                .andExpect(jsonPath("$[1].description").value(company2.getDescription()));
    }


    @Test
    public void updateCompanyTest() throws Exception {
        Company updatedCompany = new Company(1L, "Updated Name", "Updated Description");
        given(companyService.updateCompany(updatedCompany, updatedCompany.getId())).willReturn(true);

        mockMvc.perform(put("/api/v1/companies/{id}", updatedCompany.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"" + updatedCompany.getName() + "\", \"description\": \"" + updatedCompany.getDescription() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Company updated successfully"));
    }

    @Test
    public void addCompanyTest() throws Exception {
        String newCompanyName = "New Company";
        String newCompanyDescription = "New Description";

        mockMvc.perform(post("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"" + newCompanyName + "\", \"description\": \"" + newCompanyDescription + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Company added successfully"));
    }

    @Test
    public void deleteCompanyTest() throws Exception {
        Long companyId = 1L;
        mockMvc.perform(delete("/api/v1/companies/{id}", companyId))
                .andExpect(status().isOk())
                .andExpect(content().string("Company Successfully Deleted"));
    }

    @Test
    public void getCompanyTest() throws Exception {
        Company company = new Company(1L, "Company One", "Description One");
        given(companyService.getCompanyById(company.getId())).willReturn(company);

        mockMvc.perform(get("/api/v1/companies/{id}", company.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(company.getName()))
                .andExpect(jsonPath("$.description").value(company.getDescription()));
    }
}
