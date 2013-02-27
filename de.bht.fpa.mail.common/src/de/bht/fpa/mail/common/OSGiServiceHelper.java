package de.bht.fpa.mail.common;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public final class OSGiServiceHelper {
  private OSGiServiceHelper() {

  }

  /**
   * This method returns one service instance, or <code>null</code> if no
   * service is currently registered.
   * 
   * @param clazz
   *          The {@link Class} of the service. Typically an Java Interface
   * @return service instance of the given {@link Class} type, or
   *         <code>null</code>
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static final <T> T getService(BundleContext bundleContext, Class<T> clazz) {
    ServiceReference serviceReference = bundleContext.getServiceReference(clazz.getName());
    if (serviceReference == null) {
      return null;
    }

    Object service = bundleContext.getService(serviceReference);
    if (service == null) {
      return null;
    }
    return (T) service;
  }
}
