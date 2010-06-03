package net.dromard.demenagement.server.services;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dromard.demenagement.shared.model.Model;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.UnexpectedException;

/**
 * Pour Utiliser ce service générique, il faut:
 * <ul>
 * <li>Implementer les 2 interfaces clients : Service + ServiceAsync</li>
 * <li>Les associer à une URL spécifique: @RemoteServiceRelativePath("monUrl")</li>
 * </ul>
 * @author st22085
 */
@SuppressWarnings("serial")
public class RpcDispatcherService extends RemoteServiceServlet {
    private static Map<String, CsvAbstractService<? extends Model>> services = new HashMap<String, CsvAbstractService<? extends Model>>();

    public boolean equalsMethod(Method m1, Method m2) {
        // test the method name
        if (!m1.getName().equals(m2.getName())) {
            return false;
        }

        if (!m1.getReturnType().equals(m2.getReturnType()))
            return false;

        Class<?>[] params1 = m1.getParameterTypes();
        Class<?>[] params2 = m2.getParameterTypes();
        if (params1.length == params2.length) {
            for (int i = 0; i < params1.length; i++) {
                if (params1[i] != params2[i])
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Process a call originating from the given request. Uses the {@link RPC#invokeAndEncodeResponse(Object, java.lang.reflect.Method, Object[])}
     * method to do the actual work.
     * <p>
     * Subclasses may optionally override this method to handle the payload in any way they desire (by routing the request to a framework component,
     * for instance). The {@link HttpServletRequest} and {@link HttpServletResponse} can be accessed via the {@link #getThreadLocalRequest()} and
     * {@link #getThreadLocalResponse()} methods.
     * </p>
     * This is public so that it can be unit tested easily without HTTP.
     * 
     * @param payload the UTF-8 request payload
     * @return a string which encodes either the method's return, a checked exception thrown by the method, or an
     * {@link IncompatibleRemoteServiceException}
     * @throws SerializationException if we cannot serialize the response
     * @throws UnexpectedException if the invocation throws a checked exception that is not declared in the service method's signature
     * @throws RuntimeException if the service method throws an unchecked exception (the exception will be the one thrown by the service)
     */
    @SuppressWarnings("unchecked")
    @Override
    public String processCall(String payload) throws SerializationException {
        try {
            // Retrieve URL
            String url = this.getThreadLocalRequest().getServletPath();
            try {
                // Interpret URL
                String serviceName = url.substring(url.lastIndexOf('/') + 1, url.length() - ".service".length());
                // Retrieve service instance from cache (only one service as to be instantiated ... but I do not want to make a complicated architecture to handle ThreadSafty)
                CsvAbstractService<? extends Model> service = services.get(serviceName);
                // If it is not yet called ... call it:
                if (service == null) {
                    String serviceClassName = this.getClass().getPackage().getName() + ".Csv" + serviceName.substring(0, 1).toUpperCase() + serviceName.substring(1) + "Service";
                    Class<CsvAbstractService<? extends Model>> clazz = (Class<CsvAbstractService<? extends Model>>) Class.forName(serviceClassName);
                    service = clazz.newInstance();
                }
                // Service as been
                if (service != null) {
                    services.put(serviceName, service);
                    RPCRequest rpcRequest = RPC.decodeRequest(payload, service.getClass(), this);
                    onAfterRequestDeserialized(rpcRequest);
                    return RPC.invokeAndEncodeResponse(service, rpcRequest.getMethod(), rpcRequest.getParameters(), rpcRequest.getSerializationPolicy());
                } else {
                    log("Service for url: '" + url + "' has no been found.");
                    return RPC.encodeResponseForFailure(null, new SerializationException("Failed to retrieve Service for url: '" + url + "'"));
                }
            } catch (Exception e) {
                log("Service for '" + url + "' has no been found.");
                return RPC.encodeResponseForFailure(null, new SerializationException("Failed to retrieve Service"));
            }
        } catch (IncompatibleRemoteServiceException ex) {
            log("An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
            return RPC.encodeResponseForFailure(null, ex);
        }
    }
}
