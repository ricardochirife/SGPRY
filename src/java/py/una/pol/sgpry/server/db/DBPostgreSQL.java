/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.sgpry.server.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.una.pol.sgpry.server.db.responses.ResponseLogin;

/**
 *
 * @author Ricardo Chirife <chirife.ricardo@gmail.com>
 */
public class DBPostgreSQL {

    private static final Logger logger = LogManager.getLogger(DBPostgreSQL.class.getName());

    public DBPostgreSQL() {
    }

    private Connection getConnection() throws Exception {
        try {
            logger.info("Iniciando conexion a BD");
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/postgres");
            return ds.getConnection();
        } catch (Exception e) {
            logger.error("Error al iniciar conexcion a BD");
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    private String buildProcedureCall(String schemaName, String procedureName, int paramCount) {

        StringBuilder sb = new StringBuilder("{call " + schemaName + "." + procedureName + "(");
        logger.info("Armando Procedimiento Almacenado: " + schemaName + "; " + procedureName);
        for (int n = 1; n <= paramCount; n++) {
            sb.append("?");
            if (n < paramCount) {
                sb.append(",");
            }
        }
        String resp = sb.append(")}").toString();
        logger.info("Procedimiento Almacenado: " + resp);
        return resp;
    }

    public ResponseLogin spLogin(String usuario, String password) {
        logger.info("spLogin: " + usuario + "; " + " ***** ");
        ResponseLogin resp = new ResponseLogin();

        int i = 0;

        Connection con = null;
        CallableStatement cs = null;

        try {
            con = this.getConnection();
            cs = con.prepareCall(this.buildProcedureCall("public", "sp_login", 5));

            cs.setObject(++i, usuario);                     //1
            cs.setObject(++i, password);                    //2

            cs.registerOutParameter(++i, Types.VARCHAR);    //3 sesion
            cs.registerOutParameter(++i, Types.VARCHAR);    //4 estado
            cs.registerOutParameter(++i, Types.VARCHAR);    //5 respuesta

            cs.execute();

            i = 2;

            resp.setUsuario(usuario);
            resp.setSesion(cs.getString(++i));
            resp.getStatus().setEstado(cs.getString(++i));
            resp.getStatus().setRespuesta(cs.getString(++i));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
                logger.error("Error al cerrar statement en spLogin");
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexion en spLogin");
            }
        }
        return resp;
    }
}
