import server.API;
import server.controller.CiudadController;
import server.model.JSonTranformer;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        get(API.CIUDADES, CiudadController::getCiudades,new JSonTranformer<>());
        post(API.ADD,CiudadController::addCiudad,new JSonTranformer<>());
        put(API.UPDATE,CiudadController::updateCiudad,new JSonTranformer<>());
    }
}
