package pe.edu.unmsm.fisi.upg.ads.cleancode.domain;

import pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Session;
import pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Speaker;
import pe.edu.unmsm.fisi.upg.ads.cleancode.domain.WebBrowser;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Session;
import pe.edu.unmsm.fisi.upg.ads.cleancode.domain.Speaker;
import pe.edu.unmsm.fisi.upg.ads.cleancode.domain.WebBrowser;

import pe.edu.unmsm.fisi.upg.ads.cleancode.exceptions.SpeakerDoesntMeetRequirementsException;
import pe.edu.unmsm.fisi.upg.ads.cleancode.exceptions.NoSessionsApprovedException;
import pe.edu.unmsm.fisi.upg.ads.cleancode.infrastructure.SqlServerRepository;

public class SpeakerTest {
	private SqlServerRepository repository = new SqlServerRepository(); // Hard coding to single concrete implementation for simplicity here.

	@Test(expected = IllegalArgumentException.class)
	public void register_EmptyFirstName_ThrowsArgumentNullException() throws Exception {
		Speaker speaker = getSpeakerThatWouldBeApproved();
		speaker.setFirstName("");
                Enrollment enrollment    = new Enrollment();
                enrollment.register(repository, speaker);
	}

	@Test(expected = IllegalArgumentException.class)
	public void register_EmptyLastName_ThrowsArgumentNullException() throws Exception {
		Speaker speaker = getSpeakerThatWouldBeApproved();
		speaker.setLastName("");
                Enrollment enrollment    = new Enrollment();
                enrollment.register(repository, speaker);
	}

	@Test(expected = IllegalArgumentException.class)
	public void register_EmptyEmail_ThrowsArgumentNullException() throws Exception {
		Speaker speaker = getSpeakerThatWouldBeApproved();
		speaker.setEmail("");
	
                Enrollment enrollment    = new Enrollment();
                enrollment.register(repository, speaker);
	}

	@Test
	public void register_WorksForPrestigiousEmployerButHasRedFlags_ReturnsSpeakerId() throws Exception {
		// arrange
		Speaker speaker = getSpeakerWithRedFlags();
		speaker.setEmployer("Microsoft");

		// act
                Enrollment enrollment    = new Enrollment();
		Integer speakerId = enrollment.register(new SqlServerRepository(), speaker); 

		// assert
		assertNotNull(speakerId);
	}

	@Test
	public void register_HasBlogButHasRedFlags_ReturnsSpeakerId() throws Exception {
		Speaker speaker = getSpeakerWithRedFlags();
                
                Enrollment enrollment    = new Enrollment();
		Integer speakerId = enrollment.register(new SqlServerRepository(), speaker); 

		assertNotNull(speakerId);
	}

	@Test
	public void register_HasCertificationsButHasRedFlags_ReturnsSpeakerId() throws Exception {
		Speaker speaker = getSpeakerWithRedFlags();
		speaker.setCertifications(Arrays.asList("cert1", "cert2", "cert3", "cert4"));

                Enrollment enrollment    = new Enrollment();
		Integer speakerId =  enrollment.register(new SqlServerRepository(), speaker); 

		assertNotNull(speakerId);
	}

	@Test(expected = NoSessionsApprovedException.class)
	public void register_SingleSessionThatsOnOldTech_ThrowsNoSessionsApprovedException() throws Exception {
		Speaker speaker = getSpeakerThatWouldBeApproved();
		speaker.setSessions(Arrays.asList(new Session("Cobol for dummies", "Intro to Cobol")));

                Enrollment enrollment    = new Enrollment();
		enrollment.register(repository, speaker);                 
	}

	@Test(expected = IllegalArgumentException.class)
	public void register_NoSessionsPassed_ThrowsArgumentException() throws Exception {
		Speaker speaker = getSpeakerThatWouldBeApproved();
		speaker.setSessions(new ArrayList<Session>());
	
                Enrollment enrollment    = new Enrollment();
		enrollment.register(repository, speaker);  
	}

	@Test(expected = SpeakerDoesntMeetRequirementsException.class)
	public void register_DoesntAppearExceptionalAndUsingOldBrowser_ThrowsNoSessionsApprovedException() throws Exception {
		Speaker speakerThatDoesntAppearExceptional = getSpeakerThatWouldBeApproved();
		speakerThatDoesntAppearExceptional.setHasBlog(false);
		speakerThatDoesntAppearExceptional.setBrowser(new WebBrowser("IE", 6));

                Enrollment enrollment    = new Enrollment();
		enrollment.register(repository, speakerThatDoesntAppearExceptional);  
	}

	@Test(expected = SpeakerDoesntMeetRequirementsException.class)
	public void register_DoesntAppearExceptionalAndHasAncientEmail_ThrowsNoSessionsApprovedException() throws Exception {
		Speaker speakerThatDoesntAppearExceptional = getSpeakerThatWouldBeApproved();
		speakerThatDoesntAppearExceptional.setHasBlog(false);
		speakerThatDoesntAppearExceptional.setEmail("name@aol.com");

                Enrollment enrollment    = new Enrollment();
		enrollment.register(repository, speakerThatDoesntAppearExceptional);  
	}

	private Speaker getSpeakerThatWouldBeApproved() {
		Speaker speaker = new Speaker();

		speaker.setFirstName("First");
		speaker.setLastName("Last");
		speaker.setEmail("example@domain.com");
		speaker.setEmployer("Example Employer");
		speaker.setHasBlog(true);
		speaker.setBrowser(new WebBrowser("test", 1));
		speaker.setExperiences(1);
		speaker.setCertifications(new ArrayList<String>());
		speaker.setBlogURL("");
		speaker.setSessions(Arrays.asList(new Session("test title", "test description")));

		return speaker;
	}

	private Speaker getSpeakerWithRedFlags() {
		Speaker speaker = getSpeakerThatWouldBeApproved();
		speaker.setEmail("tom@aol.com");
		speaker.setBrowser(new WebBrowser("IE", 6));
		return speaker;
	}
}