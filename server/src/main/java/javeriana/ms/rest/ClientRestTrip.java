package javeriana.ms.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class ClientRestTrip {

	public static void main(String[] args) {

		// 1.
		
		  Client client = ClientBuilder.newClient(); WebTarget target =
		  client.target(Main.BASE_URI).path("restapp/trip"); List<Trip> trips =
		  target.request(MediaType.APPLICATION_JSON).get(new
		  GenericType<List<Trip>>(){});
		  
		  System.out.println("Available trips:"); for(Trip trip : trips){
		  System.out.println(trip.getId() + " "+ trip.getName()+ " "+ trip.getArrivalLocation() + " " + 
		  trip.getDepartureLocation() + " " + trip.getDate());
		  
		 
	}

	//2.
	  int tripIdToDelete = 1; // ID del viaje que deseas eliminar String
	  String response = client.target(Main.BASE_URI).path("restapp/trip")
	  .queryParam("id", tripIdToDelete) .request(MediaType.TEXT_PLAIN)
	  .delete(String.class);
	  
	  System.out.println(response);
	 

	 //3.
	  controllerRestTrip controller = new controllerRestTrip(); int tripId = 4;
	  controller.putNameTrip(tripId, "quibdo", "medellin");
	 
	 
      Trip trip = new Trip();
      trip.setName("Santander");
      trip.setArrivalLocation("Cucuta");
      trip.setDepartureLocation("Villavicencio");
      trip.setDate("21/08/2022");
      trip.setId(7);
      
      ObjectMapper objectMapper = new ObjectMapper();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      try {
			objectMapper.writeValue(byteArrayOutputStream, trip);
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

      // Llamar al método postTrip de ControllerRestTrip
      controller.postTrip(inputStream);
	 
	  }
   }
