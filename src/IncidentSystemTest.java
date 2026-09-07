import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class IncidentSystemTest
{
    @Test
    public void testValidSubclassConstruction()
    {
        MalwareIncident malware =
                new MalwareIncident(101, "Ransomware", 5, "LockBit");
        PhishingIncident phishing =
                new PhishingIncident(
                        102,
                        "Fake password reset email",
                        3,
                        "security@example.com"
                );
        assertEquals(101, malware.getIncidentId());
        assertEquals("Ransomware", malware.getTitle());
        assertEquals(5, malware.getSeverity());
        assertEquals("LockBit", malware.getMalwareFamily());
        assertEquals("Malware", malware.getType());
        assertEquals(102, phishing.getIncidentId());
        assertEquals("security@example.com", phishing.getSenderAddress());
        assertEquals("Phishing", phishing.getType());
    }
    @Test
    public void testInvalidSeverityThrowsException()
    {
        assertThrows(
                IllegalArgumentException.class,
                () -> new MalwareIncident(
                        101,
                        "Ransomware",
                        8,
                        "LockBit"
                )
        );
    }
    @Test
    public void testEventLogOrderAndDefensiveCopy()
    {
        MalwareIncident incident =
                new MalwareIncident(
                        101,
                        "Ransomware",
                        5,
                        "LockBit"
                );
        incident.addEvent("Malware detected");
        incident.addEvent("Computer isolated");

        LinkedList<String> log = incident.getEventLog();
        assertEquals("Incident created", log.get(0));
        assertEquals("Malware detected", log.get(1));
        assertEquals("Computer isolated", log.get(2));
        log.clear();
        assertEquals(3, incident.getEventLog().size());
    }
    @Test
    public void testResolutionAndRepeatedResolution()
    {
        MalwareIncident incident =
                new MalwareIncident(
                        101,
                        "Ransomware",
                        5,
                        "LockBit"
                );
        incident.resolve("Malware removed.");
        assertTrue(incident.isResolved());
        assertTrue(
                incident.getEventLog().contains("Malware removed.")
        );
        assertThrows(
                IllegalStateException.class,
                () -> incident.resolve("Resolve again.")
        );
    }
    @Test
    public void testPolymorphicReportGeneration()
    {
        IncidentManager manager = new IncidentManager();
        Incident malware =
                new MalwareIncident(
                        101,
                        "Ransomware",
                        5,
                        "LockBit"
                );
        Incident phishing =
                new PhishingIncident(
                        102,
                        "Fake email",
                        3,
                        "security@example.com"
                );
        manager.addIncident(malware);
        manager.addIncident(phishing);
        String reports = manager.generateAllReports();
        assertTrue(reports.contains("Type: Malware"));
        assertTrue(reports.contains("Malware family: LockBit"));
        assertTrue(reports.contains("Type: Phishing"));
        assertTrue(
                reports.contains(
                        "Sender address: security@example.com"
                )
        );
    }
}