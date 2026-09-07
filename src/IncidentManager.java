import java.util.ArrayList;
public class IncidentManager
{
    private ArrayList<Incident> incidents;
    public IncidentManager()
    {
        incidents = new ArrayList<>();
    }

    public void addIncident(Incident incident)
    {
        if (incident == null)
        {
            throw new IllegalArgumentException("Incident cannot be null.");
        }
        if (findIncident(incident.getIncidentId()) != null)
        {
            throw new IllegalArgumentException("An incident with this ID already exists.");
        }
        incidents.add(incident);
    }
    public Incident findIncident(int incidentId)
    {
        for (Incident incident : incidents)
        {
            if (incident.getIncidentId() == incidentId)
            {
                return incident;
            }
        }
        return null;
    }
    public ArrayList<Incident> getIncidents()
    {
        return new ArrayList<>(incidents);
    }
    public String generateAllReports()
    {
        String reports = "";
        for (Incident incident : incidents)
        {
            reports += incident.generateReport();
            reports += "\n";
        }
        return reports;
    }
}