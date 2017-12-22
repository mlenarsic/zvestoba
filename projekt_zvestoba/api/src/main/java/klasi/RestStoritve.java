package klasi;
//import io.swagger.oas.annotations.OpenAPIDefinition;
//import io.swagger.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.oas.annotations.info.Info;
//import io.swagger.oas.annotations.security.SecurityRequirement;
//import io.swagger.oas.annotations.security.SecurityScheme;
//import io.swagger.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;

//@SecurityScheme(name = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
//@OpenAPIDefinition(info = @Info(title = "Rest API", version = "v1", description = "JavaSI API for managing conference.", security = @SecurityRequirement(name = "openid-connect"), servers = @Server(url ="http://localhost:8080/v1")))
@ApplicationPath("/v1")
public class RestStoritve extends javax.ws.rs.core.Application {

}
