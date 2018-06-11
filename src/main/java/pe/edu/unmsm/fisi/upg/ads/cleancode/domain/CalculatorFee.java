package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

public interface CalculatorFee {
    public void setNext(CalculatorFee calculadorFee);
    public CalculatorFee getNext();
    public void calculateFee(Speaker speaker);
}
