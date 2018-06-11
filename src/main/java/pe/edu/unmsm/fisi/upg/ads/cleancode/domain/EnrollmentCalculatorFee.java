package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

public class EnrollmentCalculatorFee extends LevelCalculatorFee {
    private CalculatorFee calculatorFeeNext;

    public EnrollmentCalculatorFee(int experiencesMinimum, int experiencesMaximun, int registrationFee) {
        super(experiencesMinimum, experiencesMaximun, registrationFee);
    }

    
    @Override
    public CalculatorFee getNext() {
        return calculatorFeeNext;
    }

    @Override
    public void calculateFee(Speaker speaker) {
        if (speaker.getExperiences() >= this.experiencesMinimum  && speaker.getExperiences() <= experiencesMaximun) {
            speaker.setRegistrationFee(this.registrationFee);
        }else{
            calculatorFeeNext.calculateFee(speaker);
        }
    }

    @Override
    public void setNext(CalculatorFee calculadorFee) {
        calculatorFeeNext = calculatorFeeNext; 
    }
}
