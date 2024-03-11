package com.pranjal.intuit.jobms;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pranjal.intuit.exception.ResourceNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        List<Job> jobs = jobService.findAll();
        if(jobs.isEmpty()) {
            throw new ResourceNotFoundException("No jobs found");
        }
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job == null) {
            throw new ResourceNotFoundException("Job not found for this id :: " + id);
        }
        return ResponseEntity.ok().body(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if (!deleted) {
            throw new ResourceNotFoundException("Job not found for this id :: " + id);
        }
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if (!updated) {
            throw new ResourceNotFoundException("Job not found for this id :: " + id);
        }
        return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
    }

    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/

    /*         CASCADING DELETE WHEN A COMPANY DELETED         */

    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/

    @DeleteMapping("/deleteByCompany/{companyId}")
    public ResponseEntity<String> deleteJobsByCompanyId(@PathVariable Long companyId) {
        System.out.println("try 2-----");
        System.out.println(companyId);
        boolean deleted = jobService.deleteByCompanyId(companyId);
        if (!deleted) {
            throw new ResourceNotFoundException("Job not found for this id :: " + companyId);
        }
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }
}