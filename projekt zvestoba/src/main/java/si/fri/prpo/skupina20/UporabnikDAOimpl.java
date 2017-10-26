package si.fri.prpo.skupina20;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.logging.Logger;


public class UporabnikDAOimpl implements BaseDao {
    private Connection con;
    private Logger log = Logger.getLogger("UporabnikDAOimpl.log");

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

        PreparedStatement ps = null;

        try {

            if (con == null) {
                con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getUporabnikFromRS(rs);
            } else {
                log.info("Uporabnik ne obstaja");
            }

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
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

    private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException {
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        String uporabniskoime = rs.getString("uporabniskoime");
        String email = rs.getString("email");
        Uporabnik uporabnik = new Uporabnik();
        uporabnik.setIme(ime);
        uporabnik.setPriimek(priimek);
        uporabnik.setEmail(email);
        uporabnik.setUporabniskoIme(uporabniskoime);
        return uporabnik;

    }
}
