package net.strangled.stockService;

import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/stockService")
public class StockService {

    private static final String SUCCESS_RESULT="{\"result\":\"success\"";
    private static final String FAILURE_RESULT="{\"result\":\"failure\"";

    // http://localhost:8080/stock-service/rest/stockService/stock
    @GET
    @Path("/stock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStock(){
        ArrayList<Item> items = new ItemDao().getItems();
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(new Gson().toJson(items)).build();
        //return new Gson().toJson(items);
    }

    // http://localhost:8080/stock-service/rest/stockService/items
    // curl -i -v -X POST -H "Content-Type: application/json" http://localhost:8080/stock-service/rest/stockService/item -d '{"id":1,"name":"test","amount":37}'
    @POST
    @Path("/item")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
     public Response updateItem(Item item) {
        int result = new ItemDao().updateItem(item);
        if(result >= 1){
            return Response.status(200)
                           .header("Access-Control-Allow-Origin", "*")
                           .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                           .header("Access-Control-Allow-Headers", "Content-Type")
                           .entity(SUCCESS_RESULT).build();
        }
        return Response.status(200).entity(FAILURE_RESULT).build();
    }

    @POST
    @Path("/sayhi")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String sayHi(String s){
        return s;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello" + "</title>"
                + "<body><h1>" + "Hello" + "</body></h1>" + "</html> ";
    }


}
