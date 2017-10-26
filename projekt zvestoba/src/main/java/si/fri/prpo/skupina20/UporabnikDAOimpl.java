package si.fri.prpo.skupina20;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import static java.util.logging.Logger.*;


public class UporabnikDAOimpl implements BaseDao {
    private Connection con;

    @Override
    public Connection getConnection() {
        try {
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            con = ds.getConnection();
        } catch (javax.naming.NamingException N){
            N.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }

    @Override
    public Entiteta vrni(int id) {
        return null;
    }

    @Override
    public void vstavi(Entiteta ent) {

    }

    @Override
    public void odstrani(int id) {

    }

    @Override
    public void posodobi(Entiteta ent) {

    }

    @Override
    public List<Entiteta> vrniVse() {
        return null;
    }
}
