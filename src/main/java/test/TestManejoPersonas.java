package test;

import datos.Conexion;
import datos.PersonaDao;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;


import java.sql.*;
import java.util.List;

public class TestManejoPersonas {
    public static void main(String[] args)  {
        Connection conexion= null;
        try {
            conexion=Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            PersonaDao personaDao = new PersonaDaoJDBC(conexion);
            PersonaDTO persona1=new PersonaDTO("juan","perez","sd",243);
            personaDao.insertar(persona1);
            //List<PersonaDTO> personas =personaDao.seleccionar();
            //for (PersonaDTO persona:personas){
              //  System.out.println("personaDTO"+persona.toString());
            //};
            conexion.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
            System.out.println("entramos al rollback. aca se agarra cualquier error de SQL en la base");
            try {
                conexion.rollback();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }


    }
}
