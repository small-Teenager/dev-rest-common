package com.dev.rest.common.utils;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingDeque;

public class LimitLinkedBlockingDeque<E> extends LinkedBlockingDeque<E> {

    private static final long serialVersionUID = 1472790343326283322L;

    private int limitSize;

    public LimitLinkedBlockingDeque() {
        limitSize = -1;
    }

    public LimitLinkedBlockingDeque(int limitSize) {
        this.limitSize = limitSize;
    }

    private boolean checkLimitSize() {
        if (size() > limitSize) {
            return false;
        }
        return true;
    }


    @Override
    public boolean offerFirst(E e) {
        if (!checkLimitSize()) {
            return false;
        }
        return super.offerFirst(e);
    }

    @Override
    public boolean offerLast(E e) {
        if (!checkLimitSize()) {
            return false;
        }
        return super.offerLast(e);
    }

    @Override
    public void putFirst(E e) throws InterruptedException {
        if (!checkLimitSize()) {
            throw new RuntimeException("size " + size() + "limit size is " + limitSize);
        }
        super.putFirst(e);
    }

    @Override
    public void putLast(E e) throws InterruptedException {
        if (!checkLimitSize()) {
            throw new RuntimeException("size " + size() + "limit size is " + limitSize);
        }
        super.putLast(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() > (limitSize - size())) {
            return false;
        }
        return super.addAll(c);
    }

}
