package server.model;

import model.Ciudad;
import model.MyDataSource;
import model.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpCiudadService implements ICiudadService {


    @Override
    public List<Ciudad> getAll() {
        List<Ciudad> ciudadList=new ArrayList<>();
        DataSource dataSource= MyDataSource.getMyMariaDBDataSource();


        try(Connection con= dataSource.getConnection();
            Statement statement=con.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from ciudad")){


            String name,img;
            double lat,lon;
            while (resultSet.next()){
                name=resultSet.getString("name");
                lat=resultSet.getDouble("lat");
                lon=resultSet.getDouble("lon");
                img=resultSet.getString("imagen");

                ciudadList.add(new Ciudad(name,lat,lon,img));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ciudadList;    }

    @Override
    public Result<Ciudad> get(String nombre) {
        return null;
    }

    @Override
    public Result<Ciudad> update(Ciudad ciudad) {
        DataSource ds = MyDataSource.getMyMariaDBDataSource();
        String sql = "UPDATE ciudad SET lat=?,lon=?,imagen=? WHERE name = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;

            pstmt.setDouble(++pos, ciudad.getLat());
            pstmt.setDouble(++pos, ciudad.getLon());
            pstmt.setString(++pos, ciudad.getImagen());
            pstmt.setString(++pos, ciudad.getName());
            int cant = pstmt.executeUpdate();
            if (cant == 1)
                return new Result.Sucess<Ciudad>(ciudad);
            else
                return new Result.Error("Ninguna ciudad actualizada",400);
        } catch (SQLException e) {
            return new Result.Error(e.getMessage(),e.getErrorCode());
        }

    }



    @Override
    public Result<Ciudad> delete(String nombre) {
        return null;
    }

    @Override
    public Result<Ciudad> add(Ciudad ciudad) {
        DataSource ds=MyDataSource.getMyMariaDBDataSource();

        try(Connection con= ds.getConnection();
            Statement statement = con.createStatement();){
            String sql="insert into ciudad values('"+ciudad.getName()+"',"+ciudad.getLat()+","+ciudad.getLon()+",'"+ciudad.getImagen()+"')";


            int count=statement.executeUpdate(sql);
            if (count==1){
                return new Result.Sucess<>(ciudad);
            }else {
                return new Result.Error("No se pudo insertar",400);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(e.getMessage(),e.getErrorCode());
        }
    }
}
