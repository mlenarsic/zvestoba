import javax.interceptor.*;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {
    BelezenjeKlicevZrno BKzrno = new BelezenjeKlicevZrno();
    @AroundInvoke
    public Object zabeleziKlic(InvocationContext context) throws Exception {

        BKzrno.izvedenKlic();

        return context.proceed();
    }
}