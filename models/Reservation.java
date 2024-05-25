package models;

import java.io.Serializable;

public class Reservation implements Serializable {
    private int id;
    private int numeroChambre;
    private String dateDebut;
    private String dateFin;
    private String clientUsername;
    private String status;
private String type;
    private static int counter = 1;

    public Reservation(int numeroChambre, String dateDebut, String dateFin, String clientUsername) {
        this.id = counter++;
        this.numeroChambre = numeroChambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.clientUsername = clientUsername;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public int getNumeroChambre() {
        return numeroChambre;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", numeroChambre=" + numeroChambre +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", clientUsername='" + clientUsername + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }
}
