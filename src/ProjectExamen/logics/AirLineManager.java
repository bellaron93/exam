package ProjectExamen.logics;

import ProjectExamen.data.Flight;
import ProjectExamen.data.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ProjectExamen.Constants.Messages.*;
import static java.lang.Integer.parseInt;

public class AirLineManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/exemplu2";
    private static final String USER = "root";

    private static final String PASSWORD = "";


    private List<User> allUser = new ArrayList<>();
    private User currentUser;
    private List<Flight> allFlights = new ArrayList<>();
    WriterManager writerManager = new WriterManager();

    public WriterManager getWriterManager() {
        return writerManager;
    }

    public void SIGNUPS(String[] arguments) {
        String email = arguments[1];
        String name = arguments[2];
        String password = arguments[3];
        String password2 = arguments[4];

        if (allUser.contains(name)) {
            writerManager.write(ExistUser());
        } else {
            User user = new User(name, email, password);
            allUser.add(user);
            writerManager.write(UserAdd(email));
        }

        if (!password.equals(password2)) {
            writerManager.write(CannotAddUserShortpasstiff());
        } else if (password.length() < 8) {
            writerManager.write(cannotAddUserPasswordiff());

        } else {
            User user = new User(name, email, password);
            allUser.add(user);
            writerManager.write(UserAdd(email));
        }

    }

    public void LOGINS(String[] arguments) {
        String email = arguments[1];
        String parola = arguments[2];
        String name = arguments[3];


        User user = new User(name, email, parola);
        if (!user.getEmail().equals(email)) {
            writerManager.write(CanNotFindUser(email));
            return;
        }

        if (!user.getParola().equals(parola)) {
            writerManager.write(IncorectPass());
            return;
        }

        if (currentUser != null) {
            writerManager.write(AnotherUser());
        } else {
            currentUser = user;
            LocalDateTime localDateTime = LocalDateTime.now();
            writerManager.write(UserCurent(email, String.valueOf(localDateTime)));
        }


    }

    public User LOGOUT(String[] arguments) {
        String email = arguments[1];
        String name = arguments[2];
        String parola = arguments[3];
        User user = new User(name, email, parola);

        if (!currentUser.getEmail().equals(email)) {
            writerManager.write(NotConect(email));
        } else {
            LocalDateTime localDateTime = LocalDateTime.now();
            writerManager.write(Disconect(email, String.valueOf(LocalDateTime.now())));
        }

        return currentUser = null;
    }

    public void DISPLAY_MY_FLIGHTS(String[] arguments) {
        String email = arguments[1];
        String name = arguments[2];
        String parola = arguments[3];
        User user = new User(name, email, parola);
        if (currentUser == null) {
            writerManager.write(NoConect());
            return;
        }
        if (!currentUser.getUserFlights().isEmpty()) {
            currentUser = user;
            System.out.println("Flight from <from> to <to>, date <date>, duration <duration>");
        }
    }


    public void ADD_FLIGHTS(String[] arguments) {
        if (currentUser == null) {
            writerManager.write(NoConect());
            return;
        }
        Optional<Flight> optionalFlight = allFlights.stream()
                .filter(flight -> flight.getId() == Integer.parseInt(arguments[1]))
                .findFirst();
        if (optionalFlight.isEmpty()) {
            writerManager.write(IdFlightnotExist(arguments[1]));
            return;
        }
        Flight flight = optionalFlight.get();
        for (User user1 : allUser) {
            currentUser = user1;
            if (user1.getUserFlights().contains(flight)) {
                writerManager.write(UserAlreadyTicket(user1.getEmail(), String.valueOf(flight.getId())));
            } else {
                currentUser.getUserFlights().add(flight);
                writerManager.write(UserHasTicket(user1.getEmail(), String.valueOf(flight.getId())));
            }
        }
    }


    public void CANCEL_FLIGHTS(String[] arguments) {
        if (currentUser == null) {
            writerManager.write(NoConect());
            return;
        }
        Optional<Flight> optionalFlight = allFlights.stream()
                .filter(flight -> flight.getId() == Integer.parseInt(arguments[1]))
                .findFirst();
        if (optionalFlight.isEmpty()) {
            writerManager.write(IdFlightnotExist(arguments[1]));
            return;
        }

        if (currentUser != null) {
            Flight flight = optionalFlight.get();
            for (User user1 : allUser) {
                currentUser = user1;
                if (!currentUser.getUserFlights().contains(flight)) {
                    writerManager.write(DonthaveTicket(user1.getEmail(), String.valueOf(flight.getId())));
                } else {
                    user1.getUserFlights().remove(flight);
                    writerManager.write(CancelTicket(user1.getEmail(), String.valueOf(flight.getId())));
                }

            }

        }

    }

    public void DELETE_FLIGHTS(String[] arguments) {
        Optional<Flight> optionalFlight = allFlights.stream()
                .filter(flight -> flight.getId() == Integer.parseInt(arguments[1]))
                .findFirst();
        if (optionalFlight.isEmpty()) {
            writerManager.write(IdFlightnotExist(arguments[1]));
            return;
        }


        Flight flight = optionalFlight.get();
        allFlights.remove(flight);
        writerManager.write(DeleteFlight(String.valueOf(flight.getId())));
        for (User user : allUser) {
            if (user.getUserFlights().contains(flight)) {
                user.deleteFlight(flight);
                writerManager.write(NoticedDeletedFlight(user.getEmail(), String.valueOf(flight.getId())));
            }
        }
    }

    public void ADD_FLIGHTS_DETAILS(String[] arguments) {
        Integer id = parseInt(arguments[1]);
        String from = arguments[2];
        String to = arguments[3];
        LocalDate localDatedate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String strdate = localDatedate.format(formatter);
        String data = String.valueOf(localDatedate);
        int dur = parseInt(arguments[5]);
        Flight flight = new Flight(id, from, to, localDatedate, dur);
        if (allFlights.contains(id)) {
            writerManager.write(FlightExist(String.valueOf(flight.getId())));
        } else {
            allFlights.add(flight);
           writerManager.write(AddFlight(from,to,String.valueOf(flight.getDuration()),String.valueOf(flight.getId()),LocalDateTime.from(LocalDateTime.now())));
        }

    }


    public void DISPLAY_FLIGHTS(String[] arguments) {
        Integer id = Integer.valueOf(arguments[1]);
        String from = arguments[2];
        String to = arguments[3];
        LocalDate localDatedate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String strdate = localDatedate.format(formatter);
        String data = String.valueOf(localDatedate);
        int dur = parseInt(arguments[5]);
        Flight flight = new Flight(id, from, to, localDatedate, dur);
        allFlights.stream()
                .forEach(flight1 ->
                        writerManager.write(UserFlight(from,to,LocalDateTime.from(LocalDate.now()),String.valueOf(dur))));
    }

    public void PERSIST_FLIGHTS(String[] arguments) {

    }

    public void PERSIST_USERS(String[] arguments) {
    }

    public void PERSIST_USERS(String[] arguments, Statement statement) throws SQLException {
        String email = arguments[1];
        String name = arguments[2];
        String parola = arguments[3];
        try (Connection connection= DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
             Statement statement1=connection.createStatement();
        ){

           // String insertAsStudent="INSERT INTO students VALUES (3, 'ALexandru', 'Marin', 'alexandru.marin@gmail.com', '1998/2/24')";
            //statement.executeUpdate(insertAsStudent);
            String insertAsStudent="INSERT INTO students VALUES (1, 'costi.peticila@gmail.com', 'costi', 'test1234')";
            String insertAsStudent1="INSERT INTO students VALUES (2, 'mihai.georgescu@mail.com', 'mihai', 'parolaMea')";
            statement.executeUpdate(insertAsStudent);
            statement.executeUpdate(insertAsStudent1);
            writerManager.write(PersistUsers(String.valueOf(LocalDateTime.now())));

        }
    }

    /*public void PERSIST_USERS(String[] arguments) throws SQLException {
        String email = arguments[1];
        String name = arguments[2];
        String parola = arguments[3];
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement1 = connection.createStatement();
        ) {
            String select = "SELECT * FROM user";
            ResultSet resultSet=statement1.executeQuery(select);
            resultSet.moveToInsertRow();
            resultSet.moveToInsertRow();
            resultSet.updateInt(1, 1);
            resultSet.updateString(2, email);
            resultSet.updateString(3, name);
            resultSet.updateString(4, parola);
            resultSet.insertRow();


        }
    }*/
}



