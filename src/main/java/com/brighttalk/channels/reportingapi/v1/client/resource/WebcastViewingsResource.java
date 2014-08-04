/**
 * ****************************************************************************
 * Copyright BrightTALK Ltd, 2014.
 * All Rights Reserved.
 * $Id: $
 * ****************************************************************************
 */
package com.brighttalk.channels.reportingapi.v1.client.resource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

/**
 * A collection of {@link WebcastViewingResource webcast viewing}.
 */
@XmlRootElement(name = "webcastViewings")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebcastViewingsResource {
  
  /** URI template string for the relative URL of the Webcast Viewings for an identified channel. */
  public static final String FOR_CHANNEL_RELATIVE_URI_TEMPLATE = "/v1/channel/{channelId}/webcast_viewings";  
  
  /** URI template string for the relative URL of the Webcast Viewings for an identified webcast. */
  public static final String FOR_WEBCAST_RELATIVE_URI_TEMPLATE = "/v1/channel/{channelId}/webcast/{webcastId}/viewings";  
  
  @XmlElementRef
  private List<WebcastViewingResource> webcastViewings;
  
  @XmlElement(name = "link")
  private List<Link> links;
  
  // Private, as only exists only to keep JAXB implementation happy.
  private WebcastViewingsResource() {
  }  

  public WebcastViewingsResource(List<WebcastViewingResource> webcastViewings, List<Link> links) {
    this.webcastViewings = webcastViewings;
    this.links = links;
  }

  public final List<WebcastViewingResource> getWebcastViewings() {
    return this.webcastViewings != null ? this.webcastViewings : new ArrayList<WebcastViewingResource>();
  }

  public final List<Link> getLinks() {
    return this.links != null ? this.links : new ArrayList<Link>();
  }

  @Override
  public String toString() {
    /* @formatter:off */    
    return Objects.toStringHelper(this).omitNullValues()
      .add("webcastViewings", this.webcastViewings)
      .add("links", this.links)      
      .toString();    
    /* @formatter:on */
  }
}