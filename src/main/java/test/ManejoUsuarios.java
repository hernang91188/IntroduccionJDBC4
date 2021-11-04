package test;

import datos.UsuarioJDBC;
import domain.Usuario;

import java.util.List;

public class ManejoUsuarios {
    public static void main(String[] args) {
        UsuarioJDBC UsuarioJDBC=new UsuarioJDBC();
        List<Usuario> usuarios=UsuarioJDBC.seleccionar();
        for (Usuario usuario:usuarios){
            System.out.println("usuario="+usuario.toString());
        };
       Usuario usuario2=new Usuario("username11","passs2222");
        UsuarioJDBC.insertar(usuario2);
        //creando e insterando un objeto persona en l base
        //PersonaDTO persona2=new PersonaDTO("carlos","mercurio","carlos@gmail.com",324243);
        //PersonaDTO persona3=new PersonaDTO(6,"juan","gonzalez","update@gmail.com",77777);

        //System.out.println("linas afectadas: "+personaDAO.insertar(persona2));
        //System.out.println("linas afectadas al actualizarrrr: "+personaDAO.actualizar(persona3));
        //System.out.println("linas borradas: "+personaDAO.eliminar(persona3));


    }
}
