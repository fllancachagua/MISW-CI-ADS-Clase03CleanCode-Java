package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

public class EnrollmentFreeFee extends LevelCalculatorFee {

    private CalculatorFee calculatorFeeNext;

    public EnrollmentFreeFee(int experiencesMinimum, int registrationFee) {
        super(experiencesMinimum, registrationFee);
    }

    @Override
    public CalculatorFee getNext() {
        return calculatorFeeNext;
    }

    @Override
    public void calculateFee(Speaker speaker) {
        if (speaker.getExperiences() >= this.experiencesMinimum) {
            speaker.setRegistrationFee(this.registrationFee);
        }
    }

    @Override
    public void setNext(CalculatorFee calculadorFee) {
        calculatorFeeNext = calculatorFeeNext;
    }
}
