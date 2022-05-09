package server.controller;

import model.Ciudad;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.ICiudadService;
import server.model.ImpCiudadService;
import server.model.JSonTranformer;
import spark.Request;
import spark.Response;

import java.util.List;

public class CiudadController {
    static Logger logger= LoggerFactory.getLogger(CiudadController.class);

    private static ICiudadService service=new ImpCiudadService();
    private static JSonTranformer<Ciudad> jSonTranformer=new JSonTranformer<>();
    public static List<Ciudad> getCiudades(Request request, Response response) {
        logger.info("reciving request for all citys");
        return service.getAll();
    }

    public static Result<Ciudad> addCiudad(Request request, Response response) {
        logger.info("Add new city");
        String body=request.body();
        Ciudad c = jSonTranformer.getObject(body,Ciudad.class);
        Result<Ciudad> result=service.add(c);
        if (result instanceof Result.Sucess){
            response.status(200);
        }else {
            response.status(404);
        }
        return result;
    }

    public static Result<Ciudad> updateCiudad(Request request, Response response){
        logger.info("Updating City ");
        Ciudad c =  jSonTranformer.getObject(request.body(), Ciudad.class);
        Result<Ciudad> result = service.update(c);
        response.type("application/json");
        response.status((result instanceof Result.Sucess)?200:500);
        return result;
    }
}
