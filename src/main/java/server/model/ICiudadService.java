package server.model;

import model.Ciudad;
import model.Result;

import java.util.List;

public interface ICiudadService {
    List<Ciudad> getAll();
    Result<Ciudad> get(String nombre);
    Result<Ciudad> update(Ciudad ciudad);
    Result<Ciudad> delete(String nombre);
    Result<Ciudad> add(Ciudad ciudad);
}
