package com.pranjal.intuit.jobms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    void deleteByCompanyId(Long companyId);
}
