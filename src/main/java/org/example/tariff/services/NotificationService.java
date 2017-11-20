/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.services;

import org.example.tariff.exceptions.NotificationProcessException;
import org.example.tariff.model.NotificationDTO;
import org.example.tariff.model.NotifyRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author root
 */
public interface NotificationService {
    public enum NotificationType{TEMPLATE,MESSAGE};

    @Transactional(readOnly = true, timeout = 30, propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT)
    NotificationDTO processRequestNotification(final NotificationType notificationType, NotifyRequest request) throws NotificationProcessException;

    @Scheduled(fixedDelay = 10000)
    @Async
    @Transactional(readOnly = false, timeout = 30, propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT)
    void sendNotifications();
    
}
