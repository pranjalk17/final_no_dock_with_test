package com.pranjal.intuit.companyms;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.pranjal.intuit.companyms.feign.JobFeignInterface;
import com.pranjal.intuit.companyms.feign.ReviewFeignInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private JobFeignInterface jobFeignInterface;

    @Mock
    private ReviewFeignInterface reviewFeignInterface;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllCompaniesTest() {
        Company company1 = new Company(1L, "Company 1", "Description 1");
        Company company2 = new Company(2L, "Company 2", "Description 2");
        List<Company> expectedCompanies = Arrays.asList(company1, company2);
        when(companyRepository.findAll()).thenReturn(expectedCompanies);

        List<Company> result = companyService.getAllCompanies();

        assertThat(result).hasSize(2).isEqualTo(expectedCompanies);
    }

    @Test
    void createCompanyTest() {
        Company newCompany = new Company(null, "New Company", "New Description");
        when(companyRepository.save(any(Company.class))).thenReturn(newCompany);

        companyService.createCompany(newCompany);

        verify(companyRepository, times(1)).save(newCompany);
    }

    @Test
    void getCompanyByIdTest() {
        Company existingCompany = new Company(1L, "Existing Company", "Existing Description");
        when(companyRepository.findById(1L)).thenReturn(Optional.of(existingCompany));

        Company foundCompany = companyService.getCompanyById(1L);

        assertThat(foundCompany).isNotNull();
        assertThat(foundCompany.getId()).isEqualTo(1L);
    }

    @Test
    void updateCompanyTest() {
        Company existingCompany = new Company(1L, "Existing Company", "Existing Description");
        Company updatedCompany = new Company(1L, "Updated Company", "Updated Description");
        when(companyRepository.findById(1L)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);

        boolean result = companyService.updateCompany(updatedCompany, 1L);

        assertTrue(result);
        verify(companyRepository).save(updatedCompany);
    }

    @Test
    void deleteCompanyByIdTest() {
        Long companyId = 1L;
        when(companyRepository.existsById(companyId)).thenReturn(true);

        boolean result = companyService.deleteCompanyById(companyId);

        verify(jobFeignInterface, times(1)).deleteJobsByCompanyId(companyId);
        verify(reviewFeignInterface, times(1)).deleteJobsByCompanyId(companyId);
        verify(companyRepository,  times(1)).deleteById(companyId);
        assertTrue(result, "Expected deleteCompanyById to return true indicating successful deletion.");
    }
}

