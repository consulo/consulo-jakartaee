package org.jetbrains.idea.tomcat.server;

import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.javaee.oss.server.JavaeeServerInstance;
import com.intellij.javaee.oss.transport.*;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.SkipDefaultValuesSerializationFilters;
import com.intellij.util.xmlb.XmlSerializer;
import com.intellij.util.xmlb.annotations.Tag;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.TomcatBundle;
import org.jetbrains.idea.tomcat.TomcatDeploymentModelBase;
import org.jetbrains.idea.tomcat.TomcatRemoteRunConfigurationEditor;
import org.jetbrains.idea.tomcat.TomcatUtil;
import org.jetbrains.idea.tomcat.admin.TomEEAgentAdminServerImpl;
import org.jetbrains.idea.tomcat.admin.TomcatAdminRemoteServerImpl;
import org.jetbrains.idea.tomcat.admin.TomcatAdminServerBase;

import java.io.File;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatRemoteModel extends TomcatServerModel implements MultiTargetRemoteServerModel {

  @NonNls
  private static final String DATA_ELEMENT = "data";

  private int myJndiPort = DEFAULT_JNDI_PORT;

  @NonNls
  public static final String CONTEXT_TARGET_NAME = "CONTEXT_XML";
  @NonNls
  public static final String STAGING_TARGET_NAME = "STAGING";

  public static final RemoteStagingTargetDefinition CONTEXT_TARGET_DEFINITION = new RemoteStagingTargetDefinition(CONTEXT_TARGET_NAME,
                                                                                                                  TomcatBundle.message(
                                                                                                                    "TomcatRemoteModel.target.definition.context"),
                                                                                                                  TransportTargetKind.WATCHED);


  public static final RemoteStagingTargetDefinition STAGING_TARGET_DEFINITION = new RemoteStagingTargetDefinition(STAGING_TARGET_NAME,
                                                                                                                  TomcatBundle.message(
                                                                                                                    "TomcatRemoteModel.target.definition.staging"),
                                                                                                                  TransportTargetKind.STAGING);


  public static final MultiTargetRemoteStagingProvider STAGING_PROVIDER = new MultiTargetRemoteStagingProvider(CONTEXT_TARGET_DEFINITION,
                                                                                                               STAGING_TARGET_DEFINITION);


  private MultiTargetRemoteServerModelDelegate myTransportDelegate =
    STAGING_PROVIDER.initModelDelegate(new MultiTargetRemoteServerModelDelegate(), this);

  @Override
  protected TomcatAdminServerBase<?> createTomcatServerAdmin(JavaeeServerInstance processHandler, TomEEAgentAdminServerImpl tomEEAdmin) {
    return new TomcatAdminRemoteServerImpl(this, tomEEAdmin);
  }

  @Override
  public int getJndiPort() {
    return myJndiPort;
  }

  public void setJndiPort(int jndiPort) {
    myJndiPort = jndiPort;
  }

  @Override
  public String getTransportHostId() {
    return myTransportDelegate.getTransportHostId();
  }

  @Override
  public void setTransportHostId(String transportHostId) {
    myTransportDelegate.setTransportHostId(transportHostId);
  }

  @Override
  public TransportTargetModelBase getTarget(String targetName) {
    return myTransportDelegate.getTarget(targetName);
  }

  public boolean transferContextFile(File contextXmlFile) {
    return myTransportDelegate.transferFile(CONTEXT_TARGET_NAME, contextXmlFile);
  }

  @Override
  public boolean isDeployAllowed() {
    return myTransportDelegate.isDeployAllowed();
  }

  public String prepareDeployment(String sourcePath) throws RuntimeConfigurationException {
    return myTransportDelegate.prepareDeployment(STAGING_TARGET_NAME, sourcePath, false);
  }

  @Override
  public boolean isUseJmx() {
    return super.isUseJmx() && hasDeployments();
  }

  public boolean hasDeployments() {
    return !getCommonModel().getDeploymentModels().isEmpty();
  }

  @Override
  public void checkConfiguration() throws RuntimeConfigurationException {
    if (isVersion5OrHigher()) {
      super.checkConfiguration();
      if (hasDeployments() && myJndiPort <= 0) {
        throw new RuntimeConfigurationError(TomcatBundle.message("error.jmx.port.not.specified"));
      }
      myTransportDelegate.checkConfiguration(Collections.singleton(STAGING_TARGET_NAME));
    }
    else {
      if (hasDeployments()) {
        throw new RuntimeConfigurationError(TomcatBundle.message("error.remote.deploy.not.supported.for.under.v5"));
      }
    }
  }

  @Override
  public SettingsEditor<CommonModel> getEditor() {
    return new TomcatRemoteRunConfigurationEditor();
  }

  @Override
  public void readExternal(Element element) throws InvalidDataException {
    Element dataElement = element.getChild(DATA_ELEMENT);
    if (dataElement == null) {
      return;
    }
    final TomcatRemoteModelData settings = XmlSerializer.deserialize(dataElement, TomcatRemoteModelData.class);
    myTransportDelegate.readFromData(settings);
    myJndiPort = settings.getJndiPort();
  }

  @Override
  public void writeExternal(Element element) throws WriteExternalException {
    final TomcatRemoteModelData settings = new TomcatRemoteModelData();
    myTransportDelegate.writeToData(settings);
    settings.setJndiPort(myJndiPort);
    Element dataElement = XmlSerializer.serialize(settings, new SkipDefaultValuesSerializationFilters());
    element.addContent(dataElement);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    TomcatRemoteModel result = (TomcatRemoteModel)super.clone();
    result.myTransportDelegate
      = STAGING_PROVIDER.initModelDelegate((MultiTargetRemoteServerModelDelegate)myTransportDelegate.clone(), result);
    return result;
  }

  public boolean isContextDescriptorExist() {
    return getCommonModel().getDeploymentModels().stream()
      .anyMatch(m -> TomcatUtil.findContextInContextXml((TomcatDeploymentModelBase)m) != null);
  }

  @Tag(DATA_ELEMENT)
  public static class TomcatRemoteModelData extends MultiTargetRemoteServerModelData {

    @Tag("jndi-port")
    private int myJndiPort = DEFAULT_JNDI_PORT;


    public int getJndiPort() {
      return myJndiPort;
    }

    public void setJndiPort(int jndiPort) {
      myJndiPort = jndiPort;
    }
  }
}
