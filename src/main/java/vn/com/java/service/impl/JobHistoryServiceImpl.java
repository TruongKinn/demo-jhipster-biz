package vn.com.java.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.java.domain.JobHistory;
import vn.com.java.repository.JobHistoryRepository;
import vn.com.java.service.JobHistoryService;
import vn.com.java.service.dto.JobHistoryDTO;
import vn.com.java.service.mapper.JobHistoryMapper;

/**
 * Service Implementation for managing {@link JobHistory}.
 */
@Service
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService {

    private final Logger log = LoggerFactory.getLogger(JobHistoryServiceImpl.class);

    private final JobHistoryRepository jobHistoryRepository;

    private final JobHistoryMapper jobHistoryMapper;

    public JobHistoryServiceImpl(JobHistoryRepository jobHistoryRepository, JobHistoryMapper jobHistoryMapper) {
        this.jobHistoryRepository = jobHistoryRepository;
        this.jobHistoryMapper = jobHistoryMapper;
    }

    @Override
    public JobHistoryDTO save(JobHistoryDTO jobHistoryDTO) {
        log.debug("Request to save JobHistory : {}", jobHistoryDTO);
        JobHistory jobHistory = jobHistoryMapper.toEntity(jobHistoryDTO);
        jobHistory = jobHistoryRepository.save(jobHistory);
        return jobHistoryMapper.toDto(jobHistory);
    }

    @Override
    public Optional<JobHistoryDTO> partialUpdate(JobHistoryDTO jobHistoryDTO) {
        log.debug("Request to partially update JobHistory : {}", jobHistoryDTO);

        return jobHistoryRepository
            .findById(jobHistoryDTO.getId())
            .map(existingJobHistory -> {
                jobHistoryMapper.partialUpdate(existingJobHistory, jobHistoryDTO);

                return existingJobHistory;
            })
            .map(jobHistoryRepository::save)
            .map(jobHistoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobHistories");
        return jobHistoryRepository.findAll(pageable).map(jobHistoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JobHistoryDTO> findOne(Long id) {
        log.debug("Request to get JobHistory : {}", id);
        return jobHistoryRepository.findById(id).map(jobHistoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobHistory : {}", id);
        jobHistoryRepository.deleteById(id);
    }
}
