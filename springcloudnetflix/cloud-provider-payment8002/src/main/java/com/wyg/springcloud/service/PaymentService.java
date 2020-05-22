package com.wyg.springcloud.service;

import com.wyg.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    public  int create(Payment payment);

    public Payment getPayment(@Param("id") long id);
}
