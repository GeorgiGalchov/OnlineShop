package com.example.online_store.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.example.online_store.service.OfferService.getAllOffers(..))")
    public void trackOfferSearch(){}

    @Pointcut("@annotation(WarnIfExecutionExceeds)")
    public void warnIfExecutionExceeds(){}
}