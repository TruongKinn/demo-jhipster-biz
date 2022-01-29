package vn.com.java.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.java.service.dto.JobHistoryDTO;

/**
 * Service Interface for managing {@link vn.com.java.domain.JobHistory}.
 */
public interface JobHistoryService {
    /**
     * Save a jobHistory.
     *
     * @param jobHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    JobHistoryDTO save(JobHistoryDTO jobHistoryDTO);

    /**
     * Partially updates a jobHistory.
     *
     * @param jobHistoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JobHistoryDTO> partialUpdate(JobHistoryDTO jobHistoryDTO);

    /**
     * Get all the jobHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JobHistoryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" jobHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JobHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" jobHistory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
