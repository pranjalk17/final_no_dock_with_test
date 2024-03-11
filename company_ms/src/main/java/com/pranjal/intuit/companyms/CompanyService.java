package com.pranjal.intuit.companyms;

import java.util.List;


public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    Company getCompanyById(Long id);

    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/

    /*         CASCADING DELETE WHEN A COMPANY DELETED         */

    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/

    boolean deleteCompanyById(Long id);




}
