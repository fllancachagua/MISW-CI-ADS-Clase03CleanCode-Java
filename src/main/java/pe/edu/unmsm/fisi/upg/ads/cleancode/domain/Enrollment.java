package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

import java.util.Arrays;
import java.util.List;
import static pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Constants.BROWSER_MAJOR_VERSION;
import pe.edu.unmsm.fisi.upg.ads.cleancode.exceptions.NoSessionsApprovedException;
import pe.edu.unmsm.fisi.upg.ads.cleancode.exceptions.SpeakerDoesntMeetRequirementsException;

public class Enrollment {

    private SpeakerPersistence speakerPersistence; 
    String[] themes = new String[]{"Cobol", "Punch Cards", "Commodore", "VBScript"};

    public Integer register(IRepository repository, Speaker speaker) throws Exception {
        speakerPersistence = new SpeakerPersistence(); 
        Integer speakerId = null;
        if (meetAllRequirements(speaker)) speakerId = speakerPersistence.insertSpeaker(repository, speaker);
        return speakerId;
    }

    public boolean meetAllRequirements(Speaker speaker) throws Exception {
        meetPersonalInformation(speaker);
        if(!meetProfessionalRequirements(speaker)) throw new SpeakerDoesntMeetRequirementsException("Speaker doesn't meet the standards required.");
        if (!meetAcademicRequirements(speaker)) throw new NoSessionsApprovedException("No sessions approved.");
        calculateRegistrationFee(speaker);
        return true;
    }

    public boolean meetPersonalInformation(Speaker speaker) throws Exception {
        if (speaker.getFirstName().isEmpty()) throw new IllegalArgumentException("First Name is required");
        if (speaker.getLastName().isEmpty()) throw new IllegalArgumentException("Last name is required.");
        if (speaker.getEmail().isEmpty()) throw new IllegalArgumentException("Email is required.");
        return true;
    }

    public boolean meetProfessionalRequirements(Speaker speaker) throws Exception {
        boolean complyProfessionalRequirement = false;

        List<String> domains = Arrays.asList("aol.com", "hotmail.com", "prodigy.com", "compuserve.com");
        List<String> emps = Arrays.asList("Pluralsight", "Microsoft", "Google", "Fog Creek Software", "37Signals", "Telerik");

        complyProfessionalRequirement = ((speaker.getExperiences() > 10 || speaker.hasBlog() || speaker.getCertifications().size() > 3 || emps.contains(speaker.getEmployer())));
        if (complyProfessionalRequirement) return complyProfessionalRequirement;

        String[] splitted = speaker.getEmail().split("@");
        String emailDomain = splitted[splitted.length - 1];

        if (!domains.contains(emailDomain) && (!(speaker.getBrowser().getName() == WebBrowser.BrowserName.InternetExplorer && speaker.getBrowser().getMajorVersion() < BROWSER_MAJOR_VERSION))) {
            complyProfessionalRequirement = true;
        }
        return complyProfessionalRequirement;
    }

    public boolean meetAcademicRequirements(Speaker speaker) {
        boolean meetAcademicRequirements = false;
        if (speaker.getSessions().isEmpty()) throw new IllegalArgumentException("Can't register speaker with no sessions to present.");
        for (Session session : speaker.getSessions()) {
            meetAcademicRequirements = existSession(session);
        }
        return meetAcademicRequirements;
    }
    
    public boolean existSession(Session session){
        boolean existSession = false;
        for (String tech : themes) {
            if (session.getTitle().contains(tech) || session.getDescription().contains(tech)) {
                session.setApproved(false);
                break;
            } else {
                session.setApproved(true);
                existSession = true;
            }
        }    
        return existSession;
    }
    public void calculateRegistrationFee(Speaker speaker){
            EnrollmentFactoryFee enrollmentFactoryFee = new EnrollmentFactoryFee();
            enrollmentFactoryFee.calculateFee(speaker);
    }
}
