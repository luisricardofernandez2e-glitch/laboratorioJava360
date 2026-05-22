package com.axity.parquededinosaurios.zonas;

public enum TipoExperiencia {BASIC(1, 3),
    PREMIUM(2, 4),
    VIP(3, 5);

    private final int minScore;
    private final int maxScore;

    TipoExperiencia(int minScore, int maxScore) {

        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }}
