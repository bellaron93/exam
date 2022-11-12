package ProjectExamen.data;

import java.util.ArrayList;
import java.util.List;

public class User {


    private String name;
    private String email;
    private String parola;

    private List<Flight> userFlights=new ArrayList<>();


    public User(String name, String email, String parola) {
        this.name = name;
        this.email = email;
        this.parola = parola;
    }

    public void addFlight (Flight flight){
       userFlights.add(flight);
    }

    public void deleteFlight (Flight flight){
        userFlights.remove(flight);
    }

    public List<Flight> getUserFlights() {
        return userFlights;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
