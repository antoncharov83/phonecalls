package com.antoncharov.statistics.service;

import com.antoncharov.statistics.dao.PhoneCallRepository;
import com.antoncharov.statistics.model.PhoneCall;
import com.antoncharov.statistics.model.PhoneCallStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PhoneCallService implements IPhoneCallService {

    @Autowired
    private PhoneCallRepository phoneCallRepository;

    @Override
    @Transactional
    public PhoneCall save(PhoneCall call){
        return phoneCallRepository.save(call);
    }

    @Override
    public Iterable<PhoneCall> findAll(){
        return phoneCallRepository.findAll();
    }

    @Override
    public Optional<PhoneCall> findById(Long id){
        return phoneCallRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(PhoneCall call){
        phoneCallRepository.delete(call);
    }

    @Override
    public Iterable<PhoneCallStat> getStat(){
        return phoneCallRepository.getStat();
    }

    @Override
    public Iterable<PhoneCallStat> getStatByDate(Date start, Date end){
        return phoneCallRepository.getStatByDate(start, end);
    }
}
