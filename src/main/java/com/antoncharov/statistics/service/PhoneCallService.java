package com.antoncharov.statistics.service;

import com.antoncharov.statistics.dao.PhoneCallRepository;
import com.antoncharov.statistics.model.PhoneCall;
import com.antoncharov.statistics.model.PhoneCallStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PhoneCallService {

    @Autowired
    private PhoneCallRepository phoneCallRepository;

    @Transactional
    public PhoneCall save(PhoneCall call){
        return phoneCallRepository.save(call);
    }

    public Iterable<PhoneCall> findAll(){
        return phoneCallRepository.findAll();
    }

    public Optional<PhoneCall> findById(Long id){
        return phoneCallRepository.findById(id);
    }

    @Transactional
    public void delete(PhoneCall call){
        phoneCallRepository.delete(call);
    }

    public Iterable<PhoneCallStat> getStat(){
        return phoneCallRepository.getStat();
    }
}
