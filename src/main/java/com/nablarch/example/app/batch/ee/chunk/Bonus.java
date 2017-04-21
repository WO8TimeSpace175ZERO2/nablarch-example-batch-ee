package com.nablarch.example.app.batch.ee.chunk;


import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity(immutable = true)
@Table
public class Bonus implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 社員ID */
    @Id
    public final Long employeeId;

    /** 支給額 */
    public final Long payments;

    public Bonus(final Long employeeId, final Long payments) {
        this.employeeId = employeeId;
        this.payments = payments;
    }
}
