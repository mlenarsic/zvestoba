package classes;

import javax.inject.Inject;
import javax.interceptor.*;
import java.util.logging.Logger;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {
    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;
    Logger logger = Logger.getLogger(BelezenjeKlicevInterceptor.class.getName());
    @AroundInvoke
    public Object zabeleziKlic(InvocationContext context) throws Exception {
        // implementacija
        logger.info("Belezi klic: " + belezenjeKlicevZrno.izvedenKlic());
        return context.proceed();
    }
}