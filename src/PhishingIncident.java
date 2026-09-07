public class PhishingIncident extends Incident
{
    private String senderAddress;
    public PhishingIncident(int incidentId, String title, int severity, String senderAddress)
    {
        super(incidentId, title, severity);
        if (senderAddress == null || senderAddress.isBlank())
        {
            throw new IllegalArgumentException("Sender address cannot be blank.");
        }
        this.senderAddress = senderAddress;
    }
    public String getSenderAddress()
    {
        return senderAddress;
    }
    @Override
    public String getType()
    {
        return "Phishing";
    }
    @Override
    protected String getAdditionalDetails()
    {
        return "Sender address: " + senderAddress + "\n";
    }
}