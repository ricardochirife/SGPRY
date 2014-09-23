/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.sgpry.server.db.responses;

import java.io.Serializable;

/**
 *
 * @author Ricardo Chirife <chirife.ricardo@gmail.com>
 */
public class ResponseLogin implements Serializable {
    
    String usuario;
    String sesion;
    ResponseStatus status;
    
    public ResponseLogin() {
        this.status = new ResponseStatus("-2000");
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getSesion() {
        return sesion;
    }
    
    public void setSesion(String sesion) {
        this.sesion = sesion;
    }
    
    public ResponseStatus getStatus() {
        return status;
    }
    
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "usuario: " + this.usuario + "; sesion: " + this.sesion + "; " + status.toString();
    }
    
}
