package net.strangled.stockService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/StockService")
public class StockService {

    @GET
    @Path("/stock")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers(){
        return "{hello:hello}";
    }
}
