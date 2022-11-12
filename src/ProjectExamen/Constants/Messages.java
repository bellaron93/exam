package ProjectExamen.Constants;

import java.time.LocalDateTime;

public class Messages {

    public static String cannotAddUserPasswordiff() {
        return "Cannot add user! The passwords are different!";

    }

    public static String CannotAddUserShortpasstiff() {
        return "Cannot add user! Password too short!";
    }

    public static String ExistUser() {
        return "User already exists!";
    }

    public static String IncorectPass() {
        return "Incorrect password!";
    }

    public static String UserAdd(String email) {
        return "User with email:" + email + " was successfully added!";
    }

    public static String CanNotFindUser(String email) {
        return "Cannot find user with email:" + email + "";
    }

    public static String UserCurent(String email, String localdate) {
        return "User with email " + email + " is the current user started from " + localdate;
    }

    public static String AnotherUser() {
        return "Another user is already connected!";
    }

    public static String NotConect(String email) {
        return "The user with email " + email + " was not connected!";
    }

    public static String Disconect(String email, String localdate) {
        return "User with email " + email + " successfully disconnected at " + localdate + " !";

    }

    public static String NoConect(){
        return "There is no connected user!";
    }

    public static String UserFlight(String from,String to,LocalDateTime localDateTime,String dur){
        return "Flight from " + from + " to "+ to +" , date " + localDateTime + " , duration " +dur + "." ;
    }

    public static String IdFlightnotExist(String id){
        return
                "The flight with id " + id + " does not exist!";
    }

    public static String UserAlreadyTicket(String email,String id){
     return "The user with email " + email + " already have a ticket for flight with id " + id + ".";
    }
    public static String UserHasTicket(String id,String email){
        return "The flight with id " +id+ " was successfully added for user with email"+ email + ".";

    }
    public static String DonthaveTicket(String email,String id){
        return "The user with email " +email + " does not have a ticket for the flight with id " + id+ ".";
    }
     public static String CancelTicket (String email,String id){
        return "The user with email " +email + " has successfully canceled his ticket for flight with id "+id+ ".";
     }

    public static String FlightExist(String id){
        return "Cannot add flight! There is already a flight with id " + id +"." ;
    }
    public static String AddFlight(String from, String to, String dur, String id, LocalDateTime dateTime){
        return "Flight whit id "+id+" from " + from + " to "+ to +"  , duration " + dur + " successfully added!";
    }
     public static String DeleteFlight(String id){
        return
                "Flight with id " + id + " successfully deleted";
     }

     public static String NoticedDeletedFlight(String email,String id){
        return "Flight whit id \"+id+\" from \" + from + \" to \"+ to +\"  , duration \" + dur + \" successfully added!";
     }

     public static String PersistFlight(String localdate){
        return "The flights was successfully saved in the database at " +localdate+ " !";
     }

    public static String PersistUsers(String localdate){
        return "The users was successfully saved in the database at " +localdate+ " !";
    }







}