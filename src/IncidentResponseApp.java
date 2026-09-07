import java.util.Scanner;
public class IncidentResponseApp
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        IncidentManager manager = new IncidentManager();
        boolean running = true;
        while (running)
        {
            System.out.println("Cybersecurity Incident Response System");
            System.out.println("A) Add incident");
            System.out.println("R) Resolve incident");
            System.out.println("P) Print reports");
            System.out.println("Q) Quit");
            System.out.print("Choice: ");
            String choice = input.nextLine().trim().toUpperCase();
            try
            {
                switch (choice)
                {
                    case "A":
                        System.out.print("Incident type (M = Malware, P = Phishing): ");
                        String type = input.nextLine().trim().toUpperCase();
                        if (!type.equals("M") && !type.equals("P"))
                        {
                            System.out.println("Invalid incident type.");
                            break;
                        }
                        System.out.print("Incident ID: ");
                        int incidentId = Integer.parseInt(input.nextLine());

                        System.out.print("Title: ");

                        String title = input.nextLine();
                        System.out.print("Severity (1-5): ");

                        int severity = Integer.parseInt(input.nextLine());
                        if (type.equals("M"))
                        {
                            System.out.print("Malware family: ");
                            String malwareFamily = input.nextLine();
                            MalwareIncident malwareIncident =
                                    new MalwareIncident(
                                            incidentId,
                                            title,
                                            severity,
                                            malwareFamily
                                    );
                            manager.addIncident(malwareIncident);
                            System.out.println(
                                    "Incident " + incidentId + " added successfully."
                            );
                        }
                        else
                        {
                            System.out.print("Sender address: ");
                            String senderAddress = input.nextLine();
                            PhishingIncident phishingIncident =
                                    new PhishingIncident(
                                            incidentId,
                                            title,
                                            severity,
                                            senderAddress
                                    );
                            manager.addIncident(phishingIncident);
                            System.out.println(
                                    "Incident " + incidentId + " added successfully."
                            );
                        }
                        break;
                    case "R":
                        System.out.print("Incident ID: ");
                        int resolveId = Integer.parseInt(input.nextLine());
                        Incident incident = manager.findIncident(resolveId);
                        if (incident == null)
                        {
                            System.out.println(
                                    "No incident was found with ID " + resolveId + "."
                            );
                            break;
                        }
                        System.out.print("Resolution note: ");
                        String note = input.nextLine();
                        while (note.isBlank())
                        {
                            System.out.println(
                                    "Resolution note cannot be blank."
                            );
                            System.out.print("Resolution note: ");
                            note = input.nextLine();
                        }
                        incident.resolve(note);
                        System.out.println(
                                "Incident " + resolveId + " resolved successfully."
                        );
                        break;
                    case "P":
                        String reports = manager.generateAllReports();
                        if (reports.isEmpty())
                        {
                            System.out.println("No incidents to report.");
                        }
                        else
                        {
                            System.out.print(reports);
                        }
                        break;
                    case "Q":
                        running = false;

                        System.out.println(
                                "Thank you for using the Cybersecurity Incident Response System."
                        );
                        break;
                    default:
                        System.out.println(
                                "Invalid command. Please enter A, R, P, or Q."
                        );
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println(
                        "Invalid numeric input. Please enter a valid number."
                );
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
            catch (IllegalStateException e)
            {
                System.out.println(e.getMessage());
            }
        }
        input.close();
    }
}