package classes;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/v1")
@CrossOrigin(supportedMethods = "GET,POST,PUT,DELETE")
public class RestStoritve extends javax.ws.rs.core.Application {

}
