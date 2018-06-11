package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_FIVE_EXPERIENCES_MINIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_FIVE_REGISTRATION_FEE;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_FOUR_EXPERIENCES_MAXIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_FOUR_EXPERIENCES_MINIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_FOUR_REGISTRATION_FEE;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_ONE_EXPERIENCES_MAXIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_ONE_EXPERIENCES_MINIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_ONE_REGISTRATION_FEE;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_THREE_EXPERIENCES_MAXIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_THREE_EXPERIENCES_MINIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_THREE_REGISTRATION_FEE;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_TWO_EXPERIENCES_MAXIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_TWO_EXPERIENCES_MINIMUN;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.LEVEL_TWO_REGISTRATION_FEE;

public class EnrollmentFactoryFee extends LevelCalculatorFee {

    private CalculatorFee calculatorFeeNext;

    @Override
    public CalculatorFee getNext() {
        return calculatorFeeNext;
    }

    @Override
    public void calculateFee(Speaker speaker) {
        EnrollmentCalculatorFee enrollmentCalculatorFeeLevelOne = new EnrollmentCalculatorFee(LEVEL_ONE_EXPERIENCES_MINIMUN, LEVEL_ONE_EXPERIENCES_MAXIMUN, LEVEL_ONE_REGISTRATION_FEE);
        this.setNext(enrollmentCalculatorFeeLevelOne);
        
        EnrollmentCalculatorFee enrollmentCalculatorFeeLevelTwo = new EnrollmentCalculatorFee(LEVEL_TWO_EXPERIENCES_MINIMUN, LEVEL_TWO_EXPERIENCES_MAXIMUN, LEVEL_TWO_REGISTRATION_FEE);
        enrollmentCalculatorFeeLevelTwo.setNext(enrollmentCalculatorFeeLevelOne);
        
        EnrollmentCalculatorFee enrollmentCalculatorFeeLevelThree = new EnrollmentCalculatorFee(LEVEL_THREE_EXPERIENCES_MINIMUN, LEVEL_THREE_EXPERIENCES_MAXIMUN, LEVEL_THREE_REGISTRATION_FEE);
        enrollmentCalculatorFeeLevelThree.setNext(enrollmentCalculatorFeeLevelTwo);
        
        EnrollmentCalculatorFee enrollmentCalculatorFeeLevelFour= new EnrollmentCalculatorFee(LEVEL_FOUR_EXPERIENCES_MINIMUN, LEVEL_FOUR_EXPERIENCES_MAXIMUN, LEVEL_FOUR_REGISTRATION_FEE);
        enrollmentCalculatorFeeLevelFour.setNext(enrollmentCalculatorFeeLevelThree);

        EnrollmentFreeFee enrollmentCalculatorFeeLevelFive = new EnrollmentFreeFee(LEVEL_FIVE_EXPERIENCES_MINIMUN,  LEVEL_FIVE_REGISTRATION_FEE);
        enrollmentCalculatorFeeLevelFive.setNext(enrollmentCalculatorFeeLevelFour);
        
        enrollmentCalculatorFeeLevelOne.calculateFee(speaker);
    }

    @Override
    public void setNext(CalculatorFee calculadorFee) {
        calculatorFeeNext = calculatorFeeNext;
    }
}
