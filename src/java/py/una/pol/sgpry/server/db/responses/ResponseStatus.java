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
public class ResponseStatus implements Serializable {

    String estado;
    String respuesta;

    public ResponseStatus() {
    }

    public ResponseStatus(String estado) {
        this.estado = estado;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "estado: " + this.estado + "; respuesta: " + this.respuesta;
    }

}
