# Cybersecurity Incident Response System
A Java-based console application for managing cybersecurity incidents, including malware and phishing reports.

## Features
- Create and manage malware and phishing incidents
- Track incident severity and resolution status
- Maintain chronological event logs
- Resolve incidents with resolution notes
- Generate detailed incident reports
- Search for incidents by ID
- Input validation and exception handling
- JUnit 5 unit testing

## Technologies
- Java
- Object-Oriented Programming (OOP)
- JUnit 5
- IntelliJ IDEA

## Concepts Demonstrated
- Inheritance
- Abstract classes
- Interfaces
- Runtime polymorphism
- Method overriding
- ArrayList and LinkedList
- Defensive copying
- Exception handling
- Input validation
- Unit testing

## Project Structure
- `Reportable.java` - Interface for report generation
- `Incident.java` - Abstract base class for cybersecurity incidents
- `MalwareIncident.java` - Represents malware incidents
- `PhishingIncident.java` - Represents phishing incidents
- `IncidentManager.java` - Stores and manages incidents
- `IncidentResponseApp.java` - Console application and user interface
- `IncidentSystemTest.java` - JUnit 5 test suite

## Example
The application provides a console-based menu:

A) Add incident  
R) Resolve incident  
P) Print reports  
Q) Quit

Users can create incidents, resolve them, and generate reports containing incident details and event history.
