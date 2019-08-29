package com.qe.quatz.service;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@Component
public class SingImpl implements ISing{
    @Override
    public void sing() {
        System.out.println("sing a song <<fly>>");
    }
}
