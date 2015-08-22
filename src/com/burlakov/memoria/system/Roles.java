package com.burlakov.memoria.system;

import java.math.BigDecimal;

/**
 * Created by denysburlakov on 14.03.15.
 */
public interface Roles {
    public static final BigDecimal GUEST = new BigDecimal(0);
    public static final BigDecimal ADMIN = new BigDecimal(1);
    public static final BigDecimal USER = new BigDecimal(2);
}
