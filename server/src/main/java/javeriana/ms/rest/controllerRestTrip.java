package javeriana.ms.rest;

import jakarta.json.*;
import jakarta.ws.rs.*;

import javax.ws.rs.core.Response;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Path("restapp")
public class controllerRestTrip {

    List<Trip> trips = readFile();

    @GET
    @Path("trip")
    @Produces("application/json")
    public List<Trip> getTripList() {
        return trips;
    }

    @PUT
    @Path("trip")
    @Produces("text/plain")
    public String putNameTrip(@QueryParam("id") int id, @QueryParam("arrival") String arrival, @QueryParam("departure") String departure) {
        Boolean successModifying = modifyFile(id, arrival, departure);
        String returnAnswer = "";
        if (successModifying == true) {
            returnAnswer = "Se realizo la modificacion exitosamente";
        } else {
            returnAnswer = "Hubo problemas realizando la modificacion";
        }
        return returnAnswer;
    }

    @DELETE
    @Path("trip")
    @Produces("text/plain")
    public String deleteTrip(@QueryParam("id") int id) {
        Boolean successModifying = deleteFromFile(id);
        String returnAnswer = "";
        if (successModifying == true) {
            returnAnswer = "Se realizo la modificacion exitosamente";
        } else {
            returnAnswer = "Hubo problemas realizando la modificacion";
        }
        return returnAnswer;
    }

    @POST
    @Path("trip")
    @Consumes("application/json")
    @Produces("text/plain")
    public String postTrip(InputStream inputStream) {

        Trip newTrip = parseJsonToTrip(inputStream);
        Boolean successModifying = postFile(newTrip);
        String returnAnswer = "";
        if (successModifying == true) {
            returnAnswer = "Se realizo la modificacion exitosamente";
        } else {
            returnAnswer = "Hubo problemas realizando la modificacion";
        }
        return returnAnswer;

    }


    public List<Trip> readFile() {
        List<Trip> trips = new ArrayList<>();
        try (JsonReader reader = Json.createReader(new FileReader("src/main/java/Resource/jsonTrip.json"))) {
            JsonArray jsonArray = reader.readArray();
            for (JsonValue value : jsonArray) {
                JsonObject jsonObject = (JsonObject) value;
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String departureLocation = jsonObject.getString("departureLocation");
                String arrivalLocation = jsonObject.getString("arrivalLocation");
                String date = jsonObject.getString("date");

                trips.add(new Trip(id, name, departureLocation, arrivalLocation, date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public boolean modifyFile(int id, String arrivalLocation, String departureLocation) {
        //Read file
        try (JsonReader reader = Json.createReader(new FileReader("src/main/java/Resource/jsonTrip.json"))) {
            JsonArray jsonArray = reader.readArray();
            List<JsonObject> jsonObjects = new ArrayList<>();
            //Find the trip with the help of the id
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.getJsonObject(i);
                if (id == jsonObject.getInt("id")) {
                    JsonObjectBuilder builder = Json.createObjectBuilder(jsonObject);
                    builder.add("departureLocation", departureLocation);
                    builder.add("arrivalLocation", arrivalLocation);
                    JsonObject updatedJsonObject = builder.build();
                    jsonObjects.add(updatedJsonObject);
                } else {
                    //Copy all the elements
                    jsonObjects.add(jsonObject);
                }
            }
            //Create a new JSonArray with the update information
            JsonArray updatedJsonArray = Json.createArrayBuilder(jsonObjects).build();
            //Write the info in the file
            try (JsonWriter writer = Json.createWriter(new FileWriter("src/main/java/Resource/jsonTrip.json"))) {
                writer.writeArray(updatedJsonArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteFromFile(int id) {
        //Read file
        try (JsonReader reader = Json.createReader(new FileReader("src/main/java/Resource/jsonTrip.json"))) {
            JsonArray jsonArray = reader.readArray();
            List<JsonObject> jsonObjects = new ArrayList<>();
            //Find the trip with the help of the id
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.getJsonObject(i);
                if (id != jsonObject.getInt("id")) {
                    jsonObjects.add(jsonObject);
                }
            }
            //Create a new JSonArray with the update information
            JsonArray updatedJsonArray = Json.createArrayBuilder(jsonObjects).build();
            //Write the info in the file
            try (JsonWriter writer = Json.createWriter(new FileWriter("src/main/java/Resource/jsonTrip.json"))) {
                writer.writeArray(updatedJsonArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Trip parseJsonToTrip(InputStream input) {
        JsonObject jsonObject = Json.createReader(input).readObject();
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String departureLocation = jsonObject.getString("departureLocation");
        String arrivalLocation = jsonObject.getString("arrivalLocation");
        String date = jsonObject.getString("date");


        Trip newTrip = new Trip(id, name, departureLocation, arrivalLocation, date);

        return newTrip;
    }

    public boolean postFile(Trip trip) {

        try (JsonReader reader = Json.createReader(new FileReader("src/main/java/Resource/jsonTrip.json"))) {
            JsonArray jsonArray = reader.readArray();
            JsonObjectBuilder newTrip = Json.createObjectBuilder().
                    add("id", trip.getId()).
                    add("name", trip.getName()).
                    add("arrivalLocation", trip.getArrivalLocation()).
                    add("date", trip.getDate()).
                    add("departureLocation", trip.getDepartureLocation());
            JsonObject jsonObject = newTrip.build();
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (JsonValue value : jsonArray) {
                jsonArrayBuilder.add(value);
            }
            jsonArrayBuilder.add(jsonObject);

            try (JsonWriter writer = Json.createWriter(new FileWriter("src/main/java/Resource/jsonTrip.json"))) {
                writer.writeArray(jsonArrayBuilder.build());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
