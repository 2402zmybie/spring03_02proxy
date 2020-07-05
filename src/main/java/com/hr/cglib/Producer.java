package com.hr.cglib;

import com.hr.proxy.IProducer;

/**
 * 生产者
 */
public class Producer{

    /**
     * 生产厂家 销售
     * @param money
     */
    public void saleProduct(float money) {
        System.out.println("销售产品,并拿到钱:" + money);
    }

    /**
     * 生产厂家售后
     * @param money
     */
    public void afterService(float money) {
        System.out.println("提供售后服务并拿到钱" + money);
    }
}
