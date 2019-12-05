package com.antoncharov.statistics.model;

public class PhoneCallStat {
    private Boolean Destination;    //- направление звонка(Входящий/Исходящий)
    private String Caller;
    private Long Count;

    public PhoneCallStat(Boolean destination, String caller, Long count) {
        Destination = destination;
        Caller = caller;
        Count = count;
    }

    public Boolean getDestination() {
        return Destination;
    }

    public void setDestination(Boolean destination) {
        Destination = destination;
    }

    public String getCaller() {
        return Caller;
    }

    public void setCaller(String caller) {
        Caller = caller;
    }

    public Long getCount() {
        return Count;
    }

    public void setCount(Long count) {
        Count = count;
    }
}
