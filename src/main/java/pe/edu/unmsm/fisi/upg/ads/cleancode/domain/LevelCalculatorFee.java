package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

public abstract class LevelCalculatorFee implements CalculatorFee {

    protected int experiencesMinimum;
    protected int experiencesMaximun;
    protected int registrationFee;

    public LevelCalculatorFee() {
    }

    public LevelCalculatorFee(int experiencesMinimum,  int registrationFee) {
        this.experiencesMinimum = experiencesMinimum;
        this.registrationFee = registrationFee;
    }

    public LevelCalculatorFee(int experiencesMinimum, int experiencesMaximun, int registrationFee) {
        this.experiencesMinimum = experiencesMinimum;
        this.experiencesMaximun = experiencesMaximun;
        this.registrationFee = registrationFee;
    }

}
