/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.services;

import org.example.tariff.model.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author root
 */
public interface UserService {

    @Transactional(readOnly = true, timeout = 30, propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT)
    Page<UserDTO> findAll(PageRequest page);
    
}
