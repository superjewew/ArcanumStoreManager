package com.arcanum.arcanumstoremanager.domain.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by norman on 29/01/18.
 */

@Entity
public class Visit {

    @PrimaryKey
    public int id;

    private String visitTime;
    private String visitor;

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }
}
