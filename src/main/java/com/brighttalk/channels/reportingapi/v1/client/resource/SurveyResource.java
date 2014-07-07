/**
 * ****************************************************************************
 * Copyright BrightTALK Ltd, 2014.
 * All Rights Reserved.
 * $Id:$
 * ****************************************************************************
 */
package com.brighttalk.channels.reportingapi.v1.client.resource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

/**
 * A set of questions to be answered by BrightTALK users on subscribing to a Channel or before viewing a Webcast.
 */
@XmlRootElement(name = "survey")
@XmlAccessorType(XmlAccessType.FIELD)
public class SurveyResource {
  @XmlAttribute
  private int id;
  private boolean active;
  @XmlElementWrapper(name = "questions")
  @XmlElement(name = "question")  
  private List<Question> questions;
  @XmlElement(name = "link")
  private List<Link> links;
  
  /** URI template string for the relative URL of an individual, identified Survey resource. */
  public static final String RELATIVE_URI_TEMPLATE = "/v1/survey/{surveyId}";  

  // Private, as only exists only to keep JAXB implementation happy. 
  private SurveyResource() {
  }
  
  public SurveyResource(int id, boolean active, List<Question> questions, List<Link> links) {
    this.id = id;
    this.active = active;
    this.questions = questions;
    this.links = links;
  }

  public final int getId() {
    return this.id;
  }

  public final boolean isActive() {
    return this.active;
  }

  public final List<Question> getQuestions() {
    return this.questions != null ? this.questions : new ArrayList<Question>();
  }

  public final List<Link> getLinks() {
    return this.links != null ? this.links : new ArrayList<Link>();
  }

  @Override
  public String toString() {
    /* @formatter:off */    
    return Objects.toStringHelper(this).omitNullValues()
      .add("id", this.id)
      .add("active", this.active)
      .add("questions", this.questions)
      .add("links", this.links)      
      .toString();
    /* @formatter:on */
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.active ? 1231 : 1237);
    result = prime * result + this.id;
    result = prime * result + ((this.links == null) ? 0 : this.links.hashCode());
    result = prime * result + ((this.questions == null) ? 0 : this.questions.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SurveyResource other = (SurveyResource) obj;
    if (this.active != other.active) {
      return false;
    }
    if (this.id != other.id) {
      return false;
    }
    if (this.links == null) {
      if (other.links != null) {
        return false;
      }
    } else if (!this.links.equals(other.links)) {
      return false;
    }
    if (this.questions == null) {
      if (other.questions != null) {
        return false;
      }
    } else if (!this.questions.equals(other.questions)) {
      return false;
    }
    return true;
  }    
}