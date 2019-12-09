package com.antoncharov.statistics.service;

import com.antoncharov.statistics.model.PhoneCall;
import com.antoncharov.statistics.model.PhoneCallStat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface IPhoneCallService {
    @Transactional
    PhoneCall save(PhoneCall call);

    Iterable<PhoneCall> findAll();

    Optional<PhoneCall> findById(Long id);

    @Transactional
    void delete(PhoneCall call);

    Iterable<PhoneCallStat> getStat();

    Iterable<PhoneCallStat> getStatByDate(Date start, Date end);
}
