package com.antoncharov.statistics.dao;

import com.antoncharov.statistics.model.PhoneCall;
import com.antoncharov.statistics.model.PhoneCallStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCallRepository extends CrudRepository<PhoneCall, Long> {
    @Query("SELECT " +
            " new com.antoncharov.statistics.model.PhoneCallStat(p.Destination, p.Operator, COUNT(p.Id))" +
            "FROM " +
            "    PhoneCall p " +
            "GROUP BY " +
            "    p.Operator, p.Destination " +
            "ORDER BY p.Operator")
    public Iterable<PhoneCallStat> getStat();
}
