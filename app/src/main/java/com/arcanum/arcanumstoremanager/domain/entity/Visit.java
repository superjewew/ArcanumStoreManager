package com.arcanum.arcanumstoremanager.domain.entity;

import java.util.Date;

/**
 * Created by norman on 29/01/18.
 */

public class Visit {
    private Date visitTime;
    private String visitor;

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }
}
