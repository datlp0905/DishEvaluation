/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.utilities;

import datlp.entity.Nutrition;

/**
 *
 * @author DATLPSE62823
 */
public class MatchNutrition {
    
    private float matchingPercent;
    private Nutrition nutrition;

    public MatchNutrition() {
    }

    public MatchNutrition(float matchingPercent, Nutrition nutrition) {
        this.matchingPercent = matchingPercent;
        this.nutrition = nutrition;
    }
    
    public float getMatchingPercent() {
        return matchingPercent;
    }

    public void setMatchingPercent(float matchingPercent) {
        this.matchingPercent = matchingPercent;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
    
}
