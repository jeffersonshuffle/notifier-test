
package org.example.tariff.services;

import org.example.tariff.exceptions.EntityNotFoundException;
import org.example.tariff.exceptions.EntityUpdateException;
import org.example.tariff.model.TariffDTO;
import org.example.tariff.model.TariffDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author root
 */
public interface TariffService {

    @Transactional(readOnly = true, timeout = 30, propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT)
    Page<TariffDTO> findAll(PageRequest page);

    /**
     * Tariff find By Id
     *
     * @param id Tariff
     * @return TariffDTO
     */
    @Transactional(readOnly = true, timeout = 30, propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT)
    TariffDTO findById(Long id) throws EntityNotFoundException;

    @Transactional(readOnly = false, timeout = 30, propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT)
    void updateDetails(TariffDetailsDTO details) throws EntityUpdateException;
    
}
