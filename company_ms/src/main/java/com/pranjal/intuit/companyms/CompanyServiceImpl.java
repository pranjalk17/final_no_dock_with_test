package com.pranjal.intuit.companyms;

import com.pranjal.intuit.companyms.feign.JobFeignInterface;
import com.pranjal.intuit.companyms.feign.ReviewFeignInterface;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    private final JobFeignInterface jobFeignInterface;
    private final ReviewFeignInterface reviewFeignInterface;

    @Transactional
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        return companyRepository.findById(id).map(existingCompany -> {
            existingCompany.setDescription(company.getDescription());
            existingCompany.setName(company.getName());
            companyRepository.save(existingCompany);
            return true;
        }).orElseThrow(() -> new NoSuchElementException("Company with id " + id + " not found"));
    }

    @Override
    public void createCompany(Company company) {
        System.out.println(company);
        companyRepository.save(company);
    }


    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Company with id " + id + " not found"));
    }


    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/

    /*         CASCADING DELETE WHEN A COMPANY DELETED         */

    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/


    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            System.out.println("try 8-----");
            jobFeignInterface.deleteJobsByCompanyId(id);
            reviewFeignInterface.deleteJobsByCompanyId(id);

            System.out.println("try 9-----");
            companyRepository.deleteById(id);
            System.out.println("try 10-----");
            return true;
        } else {
            System.out.println("try 11-----");
            throw new NoSuchElementException("Company with id " + id + " not found");
        }
    }

}
