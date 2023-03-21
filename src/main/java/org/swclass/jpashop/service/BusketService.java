package org.swclass.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.Busket;
import org.swclass.jpashop.repository.BusketRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BusketService {

    private final BusketRepository busketRepository;

    public void saveBusket(Busket busket) {
        busketRepository.save(busket);
    }
}
