package com.pranjal.intuit.jobms;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        return jobRepository.findById(id).map(job -> {
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }).orElse(false);
    }

    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/

    /*         CASCADING DELETE WHEN A COMPANY DELETED         */

    /*------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------*/

    @Override
    @Transactional
    public boolean deleteByCompanyId(Long companyId) {
        try {
            System.out.println("try 3-----");
            System.out.println(companyId);
            jobRepository.deleteByCompanyId(companyId);
            System.out.println("try 12-----");
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace(); // For a more detailed stack trace
            System.out.println("try 19-----");
            return false;
        }
    }
}