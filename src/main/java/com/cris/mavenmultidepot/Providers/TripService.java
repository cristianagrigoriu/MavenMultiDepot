/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Providers;

import com.cris.mavenmultidepot.Models.DepotModel;
import com.cris.mavenmultidepot.Models.TripModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static java.util.UUID.fromString;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author crist
 */
@Named
@ApplicationScoped
@ManagedBean
public class TripService {
    
    public static List<TripModel> trips = new ArrayList<>();

    public static List<TripModel> getTrips() {
        if (trips.isEmpty()) {
            trips = getTripsFromDatabase();
        }
        return trips;
    }
    
    private static List<TripModel> getTripsFromDatabase() {
        Connection conn = null;
        try {
            
            ResultSet rs = null;
            PreparedStatement pst = null;
            String stm = "select * from trips";
            
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/vehicles?user=postgres&password=Java1sland";
            conn = DriverManager.getConnection(url);
            
            pst = conn.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            
            List<DepotModel> depots = new DepotService().getDepots();
            
            while(rs.next()) {
                String id = rs.getString(1);
                Timestamp startingTime = rs.getTimestamp(2);;
                int duration = rs.getInt(3);
                String sourceDepotId = rs.getString(4);
                String destinationDepotId = rs.getString(5);
                
                trips.add(new TripModel(fromString(id), startingTime, duration, 
                        DepotService.findDepotById(depots, fromString(sourceDepotId)), DepotService.findDepotById(depots, fromString(destinationDepotId))));
            }
            
            rs.close();
            
            return trips;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public static String saveTripsToDatabase() {
        Connection conn = null;
        try {
            PreparedStatement pst = null;
            
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/vehicles?user=postgres&password=Java1sland";
            conn = DriverManager.getConnection(url);
            
            String stm = "delete from trips";
            pst = conn.prepareStatement(stm);
            pst.execute();
            
            for (TripModel trip : TripService.getTrips()) {
                stm = "INSERT INTO trips(id, starting_time, duration, source_depot_id, destination_depot_id) "
                    + "VALUES(?,?,?,?,?)";
                pst = conn.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
                pst.setObject(1, trip.getId(), Types.OTHER);
                pst.setTimestamp(2, trip.getStartingTime());
                pst.setInt(3, trip.getDuration());
                pst.setObject(4, trip.getSourceDepot().getId(), Types.OTHER);
                pst.setObject(5, trip.getDestinationDepot().getId(), Types.OTHER);

                int affectedRows = pst.executeUpdate();
            }
            
            pst.close();
            
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "All records saved";
    }
}
