package com.keydividend.entity;


import java.util.List;

/**
 *
 * @author rupau
 *
 */

public class Subscription {

    private String planId;
    private String planName;
    private double planCost;

    private List<PlanFeatures> planFeaturesList;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getPlanCost() {
        return planCost;
    }

    public void setPlanCost(double planCost) {
        this.planCost = planCost;
    }

    public List<PlanFeatures> getPlanFeaturesList() {
        return planFeaturesList;
    }

    public void setPlanFeaturesList(List<PlanFeatures> planFeaturesList) {
        this.planFeaturesList = planFeaturesList;
    }
}
