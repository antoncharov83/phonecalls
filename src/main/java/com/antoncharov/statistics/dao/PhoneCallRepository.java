package com.antoncharov.statistics.dao;

import com.antoncharov.statistics.model.PhoneCall;
import com.antoncharov.statistics.model.PhoneCallStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

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

    @Query("SELECT " +
            " new com.antoncharov.statistics.model.PhoneCallStat(p.Destination, p.Operator, COUNT(p.Id))" +
            "FROM " +
            "    PhoneCall p " +
            "WHERE p.CallDate >= :start AND p.CallDate <= :end " +
            "GROUP BY " +
            "    p.Operator, p.Destination " +
            "ORDER BY p.Operator")
    public Iterable<PhoneCallStat> getStatByDate(@Param("start") Date start, @Param("end") Date end);
}
