package org.jetbrains.idea.tomcat;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RuntimeConfigurationWarning;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.Ref;
import com.intellij.util.containers.ContainerUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */

@SuppressWarnings({"unchecked"})
public class TomcatServerXmlWrapper {

  @NonNls private static final String SERVICE_ELEMENT_NAME = "Service";
  @NonNls private static final String CONNECTOR_ELEMENT_NAME = "Connector";
  @NonNls private static final String PROTOCOL_ATTR = "protocol";
  @NonNls private static final String CLASS_NAME_ATTR = "className";
  @NonNls private static final String SSL_ATTR_ANY_VALUE = "sslProtocol";
  @NonNls private static final String[] SSL_ATTR_NEEDS_TRUE = {"secure", "SSLEnabled"};
  @NonNls private static final List<String> HTTP_CONNECTOR_CLASS_NAMES
    = Arrays.asList("org.apache.catalina.connector.http.HttpConnector", "org.apache.coyote.tomcat4.CoyoteConnector");
  @NonNls private static final String PROTOCOL_HANDLER_ATTRIBUTE = "protocolHandlerClassName";
  @NonNls private static final String PORT_ATTR = "port";

  private static final Map<File, Pair<Long, Integer>> ourCachedHttpPort = new HashMap<>();

  private static final int DEFAULT_HTTP_PORT = 8080;
  private static final int DEFAULT_SHUTDOWN_PORT = 8005;

  private final File myFile;

  private Document myDocument;
  private Element myRootElement;
  private ExecutionException myLoadException;
  private boolean myWasLoad;

  public TomcatServerXmlWrapper(String baseDirPath) {
    this(new File(TomcatUtil.baseConfigDir(baseDirPath), TomcatConstants.SERVER_XML));
  }

  public TomcatServerXmlWrapper(File serverXml) {
    myFile = serverXml;
    myWasLoad = false;
  }

  private void loadDoc() throws ExecutionException {
    if (!myWasLoad) {
      doLoad();
      myWasLoad = true;
    }
    if (myLoadException != null) {
      throw myLoadException;
    }
  }

  private void doLoad() {
    myDocument = null;
    myRootElement = null;
    myLoadException = null;
    try {
      myDocument = TomcatUtil.loadXMLFile(myFile);
      myRootElement = myDocument.getRootElement();
    }
    catch (ExecutionException e) {
      myLoadException = e;
    }
  }

  public int getHttpPort() {
    Pair<Long, Integer> cached = ourCachedHttpPort.get(myFile);
    if (cached != null && myFile.lastModified() == cached.getFirst()) {
      return cached.getSecond();
    }
    int port = new HttpPortHandler().getPort();
    ourCachedHttpPort.put(myFile, Pair.create(myFile.lastModified(), port));
    return port;
  }

  public void setHttpPort(int httpPort) throws ExecutionException {
    new HttpPortHandler().setPort(httpPort);
  }

  public void setHttpsPort(int httpsPort) throws ExecutionException {
    new HttpsPortHandler().setPort(httpsPort);
  }

  public int getShutdownPort() {
    return new ShutdownPortHandler().getPort();
  }

  public void setShutdownPort(int shutdownPort) throws ExecutionException {
    new ShutdownPortHandler().setPort(shutdownPort);
  }

  public Integer getAjpPort() {
    return new AjpPortHandler().getPort();
  }

  public void setAjpPort(int ajpPort) throws ExecutionException {
    new AjpPortHandler().setPort(ajpPort);
  }

  public Element findLocalHost() throws ExecutionException {
    loadDoc();
    Element result = TomcatUtil.findElementByAttr(myRootElement, "Host", "name", "localhost");
    if (result == null) {
      throw new ExecutionException(TomcatBundle.message("exception.text.server.xml.does.not.contain.virtual.host.localhost"));
    }
    return result;
  }

  public void save() throws ExecutionException {
    loadDoc();
    TomcatUtil.saveXMLFile(myDocument, myFile, true);
  }

  public void checkHttpConnectorsAmount() throws RuntimeConfigurationWarning {
    new HttpPortHandler().checkConnectorsAmount();
  }

  public void checkHttpsConnectorsAmount() throws RuntimeConfigurationWarning {
    new HttpsPortHandler().checkConnectorsAmount();
  }

  public void checkAjpConnectorsAmount() throws RuntimeConfigurationWarning {
    new AjpPortHandler().checkConnectorsAmount();
  }

  public boolean hasSourceLocalPort() {
    try {
      loadDoc();
    }
    catch (ExecutionException e) {
      return false;
    }

    return new HttpPortHandler().getPortNode() != null;
  }

  private abstract class PortHandler {

    public Integer getPort() {
      try {
        loadDoc();
      }
      catch (ExecutionException e) {
        //
      }
      Integer result = getDefaultPort();
      Element portNode = getPortNode();
      if (portNode != null) {
        String portAttribute = portNode.getAttributeValue(PORT_ATTR);
        if (portAttribute != null) {
          try {
            result = parsePort(portAttribute);
          }
          catch (NumberFormatException e) {
            //
          }
        }
      }
      return result;
    }

    public void setPort(int port) throws ExecutionException {
      loadDoc();
      Element portNode = getPortNode();
      if (portNode == null) {
        throw new ExecutionException(getNodeNotFoundMessage());
      }
      portNode.setAttribute(PORT_ATTR, String.valueOf(port));
    }

    protected int parsePort(String portAttribute) throws NumberFormatException {
      return Integer.parseInt(portAttribute);
    }

    protected abstract Element getPortNode();

    protected abstract Integer getDefaultPort();

    protected abstract String getNodeNotFoundMessage();
  }

  private abstract class ConnectorPortHandler extends PortHandler {

    @Override
    protected Element getPortNode() {
      List<Element> connectors = getConnectors();
      return ContainerUtil.getFirstItem(connectors);
    }

    protected List<Element> getConnectors() {
      List<Element> result = new ArrayList<>();
      Ref<Element> portNodeRef = new Ref<>();

      if (myRootElement == null) {
        return Collections.EMPTY_LIST;
      }

      for (Element service : myRootElement.getChildren(SERVICE_ELEMENT_NAME)) {
        for (final Element connector : service.getChildren(CONNECTOR_ELEMENT_NAME)) {
          if (checkConnector(connector, portNodeRef)) {
            result.add(portNodeRef.get());
          }
        }
      }

      return result;
    }

    protected void checkConnectorsAmount() throws RuntimeConfigurationWarning {
      try {
        loadDoc();
      }
      catch (ExecutionException e) {
        return;
      }

      if (getConnectors().size() > 1) {
        throw new RuntimeConfigurationWarning(
          TomcatBundle.message("TomcatServerXmlWrapper.http.connectors.more.than.one", getPortName()));
      }
    }

    protected abstract String getPortName();

    protected abstract boolean checkConnector(Element connector, Ref<Element> portNodeRef);
  }

  private abstract class HttpPortHandlerBase extends ConnectorPortHandler {

    @Override
    protected boolean checkConnector(Element connector, Ref<Element> portNodeRef) {
      String protocol = connector.getAttributeValue(PROTOCOL_ATTR);

      if (protocol != null && (protocol.equals("HTTP/1.1") || protocol.startsWith("org.apache.coyote.http11."))) {
        portNodeRef.set(connector);
        return true;
      }

      String className = connector.getAttributeValue(CLASS_NAME_ATTR);

      if (protocol == null && className == null
          || HTTP_CONNECTOR_CLASS_NAMES.contains(className) && connector.getAttributeValue(PROTOCOL_HANDLER_ATTRIBUTE) == null) {
        portNodeRef.set(connector);
        return true;
      }

      return false;
    }

    protected boolean isConnectorSecured(Element connector) {
      if (connector.getAttributeValue(SSL_ATTR_ANY_VALUE) != null) {
        return true;
      }

      for (String nextNeedsTrue : SSL_ATTR_NEEDS_TRUE) {
        String secure = connector.getAttributeValue(nextNeedsTrue);
        if (Boolean.TRUE.toString().equals(secure)) {
          return true;
        }
      }

      return false;
    }
  }

  protected class HttpPortHandler extends HttpPortHandlerBase {

    @Override
    protected String getPortName() {
      return "HTTP";
    }

    @Override
    protected boolean checkConnector(Element connector, Ref<Element> portNodeRef) {
      if (isConnectorSecured(connector)) {
        return false;
      }
      return super.checkConnector(connector, portNodeRef);
    }

    @Override
    protected Integer getDefaultPort() {
      return DEFAULT_HTTP_PORT;
    }

    @Override
    protected String getNodeNotFoundMessage() {
      return TomcatBundle.message("error.message.http.connector.not.found");
    }
  }

  private class HttpsPortHandler extends HttpPortHandlerBase {

    @Override
    protected String getPortName() {
      return "HTTPs";
    }

    @Override
    protected boolean checkConnector(Element connector, Ref<Element> portNodeRef) {
      if (!isConnectorSecured(connector)) {
        return false;
      }
      return super.checkConnector(connector, portNodeRef);
    }

    @Override
    protected Integer getDefaultPort() {
      return null;
    }

    @Override
    protected String getNodeNotFoundMessage() {
      return TomcatBundle.message("error.message.https.connector.not.found");
    }
  }

  private class ShutdownPortHandler extends PortHandler {

    @Override
    protected Element getPortNode() {
      return myRootElement;
    }

    @Override
    protected Integer getDefaultPort() {
      return DEFAULT_SHUTDOWN_PORT;
    }

    @Override
    protected String getNodeNotFoundMessage() {
      throw new UnsupportedOperationException();
    }
  }

  private class AjpPortHandler extends ConnectorPortHandler {

    @Override
    protected String getPortName() {
      return "AJP";
    }

    @Override
    protected boolean checkConnector(Element connector, Ref<Element> portNodeRef) {
      String protocol = connector.getAttributeValue(PROTOCOL_ATTR);

      if (protocol != null && protocol.equals("AJP/1.3")) {
        portNodeRef.set(connector);
        return true;
      }

      return false;
    }

    @Override
    protected Integer getDefaultPort() {
      return null;
    }

    @Override
    protected String getNodeNotFoundMessage() {
      return TomcatBundle.message("error.message.ajp.connector.not.found");
    }
  }
}
