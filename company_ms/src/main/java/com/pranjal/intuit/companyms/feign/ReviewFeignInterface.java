package com.pranjal.intuit.companyms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "review-service")
public interface ReviewFeignInterface {
    @DeleteMapping("/api/v1/reviews/deleteByCompany/{companyId}")
    ResponseEntity<String> deleteJobsByCompanyId(@PathVariable Long companyId);
}
