package com.neta.myssm.io;

public interface BeanFactory {
    // 根据 id 获取 bean
    Object getBean(String id);
}
