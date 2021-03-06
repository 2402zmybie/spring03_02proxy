package com.hr.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者
 */
public class Client {

    public static void main(String[] args) {
        final Producer producer = new Producer();

        /**
         * 动态代理:
         *  特点: 字节码随用随创建,随用随加载
         *  作用: 不修改源码的基础上对方法增强
         *  分类:
         *      基于接口的动态代理
         *      基于子类的动态代理
         *   基于接口的动态代理:
         *    涉及的类:Proxy
         *    提供者:JDK
         *   创建代理对象:
         *      Proxy类中的newProxyInstance方法
         *      newProxyInstance方法的参数:
         *          ClassLoader:
         *              用于加载代理对象字节码,和被代理对象使用相同的类加载器,代理谁写谁的类加载器,固定写法
         *          Class[]:
         *              让代理对象和被代理对象有相同的方法,固定写法
         *          InvocationHandler: 用于提供增强的代码
         *              它是让我们写如何代理,我们一般都是写一个该接口的实现类,通常都是匿名内部类
         *
         *
         */
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何接口方法都会经过该方法
                     * @param proxy 代理对象的引用(一般不用)
                     * @param method 当前执行方法
                     * @param args 当前执行方法所需的参数
                     * @return 和被代理对象有相同的返回值
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强的代码
                        Object returnValue = null;
                        //1 获取方法执行的参数
                        Float money = (Float) args[0];
                        //2 判断当前方法是不是 销售方法
                        if("saleProduct".equals(method.getName())) {
                            //执行方法
                            returnValue = method.invoke(producer, money * 0.8f);
                        }
                        return returnValue;

                    }
        });
        proxyProducer.saleProduct(10000f);
    }
}
