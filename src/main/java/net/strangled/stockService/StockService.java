package net.strangled.stockService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/stockService")
public class StockService {

    // http://localhost:8080/stock-service/rest/stockService/stock
    @GET
    @Path("/stock")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStock(){
        return "{\"hello\":\"hello\"}";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHi(){
        return "Hi";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello" + "</title>"
                + "<body><h1>" + "Hello" + "</body></h1>" + "</html> ";
    }


}
