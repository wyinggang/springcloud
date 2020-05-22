package com.wyg.springcloud.service.impl;

import com.wyg.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.springcloud.dao.PaymentDao;
import com.wyg.springcloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPayment(long id) {
        return paymentDao.getPayment(id);
    }
}
