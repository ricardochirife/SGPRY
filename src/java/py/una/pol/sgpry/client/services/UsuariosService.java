/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.sgpry.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import py.una.pol.sgpry.server.db.responses.ResponseLogin;

/**
 *
 * @author Ricardo Chirife <chirife.ricardo@gmail.com>
 */
@RemoteServiceRelativePath("UsuariosService")
public interface UsuariosService extends RemoteService {
    public ResponseLogin login (String usuario, String password) throws Exception;
}
