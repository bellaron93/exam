package ProjectExamen;

import ProjectExamen.Constants.Comenzi1;
import ProjectExamen.Constants.Messages;
import ProjectExamen.logics.AirLineManager;
import ProjectExamen.logics.ReaderManager;
import ProjectExamen.logics.WriterManager;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        AirLineManager airLineManager = new AirLineManager();
        ReaderManager readerManager = new ReaderManager();
        WriterManager writerManager = new WriterManager();

        String line = readerManager.readLine();
        while (line != null) {
            writerManager.write("The user with email \" +email + \" has successfully canceled his ticket for flight with id \"+id+ \".");
            writerManager.flush();
            String[] arguments = line.split(" ");
            Comenzi1 command = Comenzi1.valueOf(arguments[0]);
            /*Comenzi1 comenzi1;
            try {
                comenzi1 = Comenzi1.valueOf(arguments[0]);
            } catch (IllegalArgumentException e) {
                return;
            }*/
            //airLineManager.getWriterManager().flush();
            switch (command) {

                case PERSIST_FLIGHTS: {
                    airLineManager.PERSIST_FLIGHTS(arguments);
                    break;
                }
                case PERSIST_USERS: {
                    airLineManager.PERSIST_USERS(arguments);
                    break;
                }
                case LOGINS: {
                    airLineManager.LOGINS(arguments);
                    break;
                }
                case SIGNUPS: {
                    airLineManager.SIGNUPS(arguments);
                    break;
                }
                case LOGOUT: {
                    airLineManager.LOGOUT(arguments);
                    break;
                }
                case DISPLAY_MY_FLIGHTS: {
                    airLineManager.DISPLAY_MY_FLIGHTS(arguments);
                    break;
                }
                case ADD_FLIGHT_DETAILS: {
                    airLineManager.ADD_FLIGHTS_DETAILS(arguments);
                    break;
                }
                case CANCEL_FLIGHT: {
                    airLineManager.CANCEL_FLIGHTS(arguments);
                    break;
                }
                case DELETE_FLIGHT: {
                    airLineManager.DELETE_FLIGHTS(arguments);
                    break;
                }
                case ADD_FLIGHT: {
                    airLineManager.ADD_FLIGHTS(arguments);
                    break;

                }

            }


            line = readerManager.readLine();


        }

    }
}













