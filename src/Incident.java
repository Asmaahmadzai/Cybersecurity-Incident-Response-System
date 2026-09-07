import java.util.LinkedList;
public abstract class Incident implements Reportable
{
    private int incidentId;
    private String title;
    private int severity;
    private boolean resolved;
    private LinkedList<String> eventLog;

    public Incident(int incidentId, String title, int severity)
    {
        if (incidentId <= 0)
        {
            throw new IllegalArgumentException("Incident ID must be positive.");
        }
        if (title == null || title.isBlank())
        {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
        if (severity < 1 || severity > 5)
        {
            throw new IllegalArgumentException("Severity must be between 1 and 5.");
        }
        this.incidentId = incidentId;
        this.title = title;
        this.severity = severity;
        this.resolved = false;
        this.eventLog = new LinkedList<>();
        eventLog.add("Incident created");
    }
    public int getIncidentId()
    {
        return incidentId;
    }
    public String getTitle()
    {
        return title;
    }
    public int getSeverity()
    {
        return severity;
    }
    public boolean isResolved()
    {
        return resolved;
    }

    public void addEvent(String event)
    {
        if (event == null || event.isBlank())
        {
            throw new IllegalArgumentException("Event cannot be blank.");
        }
        eventLog.add(event);
    }

    public LinkedList<String> getEventLog()
    {
        return new LinkedList<>(eventLog);
    }

    public void resolve(String note)
    {
        if (note == null || note.isBlank())
        {
            throw new IllegalArgumentException("Resolution note cannot be blank.");
        }
        if (resolved)
        {
            throw new IllegalStateException("Incident is already resolved.");
        }
        resolved = true;
        eventLog.add(note);
    }
    public abstract String getType();

    protected String getAdditionalDetails()
    {
        return "";
    }
    @Override
    public String generateReport()
    {
        String report = "Incident Report\n";
        report += "---------------\n";
        report += "ID: " + incidentId + "\n";
        report += "Type: " + getType() + "\n";
        report += "Title: " + title + "\n";
        report += "Severity: " + severity + "\n";
        report += "Status: " + (resolved ? "Resolved" : "Unresolved") + "\n";
        report += getAdditionalDetails();
        report += "Event log:\n";
        for (int i = 0; i < eventLog.size(); i++)
        {
            report += (i + 1) + ". " + eventLog.get(i) + "\n";
        }
        return report;
    }
}