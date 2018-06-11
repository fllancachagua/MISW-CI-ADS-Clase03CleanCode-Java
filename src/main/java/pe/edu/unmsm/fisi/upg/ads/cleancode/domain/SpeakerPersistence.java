package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

public class SpeakerPersistence {

    public Integer insertSpeaker(IRepository repository, Speaker speaker) {
        Integer speakerId = null;
        try {
            speakerId = repository.saveSpeaker(speaker);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return speakerId;

    }
}
