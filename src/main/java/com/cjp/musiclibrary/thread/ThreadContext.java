package com.cjp.musiclibrary.thread;

import com.auth0.jwt.interfaces.Claim;

/**
 * @author CJP
 * @version 1.0
 */
public class ThreadContext {
    private static final ThreadLocal<Claim> tl = new ThreadLocal<>();

    public static void setTl(Claim claim) {
        tl.set(claim);
    }

    public static Claim getTl() {
        return tl.get();
    }

    public static void removeTl() {
        tl.remove();
    }
}
