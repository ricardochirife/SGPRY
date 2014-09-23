/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.sgpry.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.una.pol.sgpry.client.services.UsuariosService;
import py.una.pol.sgpry.server.db.DBPostgreSQL;
import py.una.pol.sgpry.server.db.responses.ResponseLogin;

/**
 *
 * @author Ricardo Chirife <chirife.ricardo@gmail.com>
 */
public class UsuariosServiceImpl extends RemoteServiceServlet implements UsuariosService {

    private static final Logger logger = LogManager.getLogger(UsuariosServiceImpl.class.getName());

    @Override
    public ResponseLogin login(String usuario, String password) throws Exception {

        logger.info(usuario);

        HttpServletRequest request = this.getThreadLocalRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("sesion");

        ResponseLogin resp = null;

        try {
            DBPostgreSQL db = new DBPostgreSQL();
            resp = db.spLogin(usuario, password);

            if (resp == null) {
                throw new Exception("Error al iniciar sesion");
            }

            if (resp.getStatus().getEstado().equalsIgnoreCase("0")) {
                session.setAttribute("sesion", resp.getSesion());
                return resp;
            } else {
                throw new Exception(resp.getStatus().getRespuesta());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception(e.getMessage());
        }
    }

}
