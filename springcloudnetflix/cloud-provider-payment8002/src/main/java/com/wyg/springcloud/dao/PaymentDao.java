package com.wyg.springcloud.dao;

import com.wyg.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaymentDao {
    @Insert("insert into payment(id,serial) values(#{id},#{serial})")
    int create(Payment payment);

    @Select("select * from payment where id=#{id}")
    Payment getPayment(@Param("id") long id);

}
