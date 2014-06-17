/**
 * ****************************************************************************
 * Copyright BrightTALK Ltd, 2014.
 * All Rights Reserved.
 * $Id: $
 * ****************************************************************************
 */
package com.brighttalk.channels.reportingapi.v1.client;

import java.util.List;
import java.util.Map;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimaps;

/**
 * Builds a map representation of the request parameters used to support paging of collection of API resources across
 * API calls from supplied paging data. Also defines the set of named request parameters supporting paging.
 */
public class PagingRequestParamsBuilder {

  /**
   * Enumeration of request parameter names used to support aspects of paging through collection of API resources.
   * <p>
   * Use {@link #getName()} for the literal value of the request parameter name to be used in URLs. 
   */
  enum ParamName {
    PAGE_SIZE("pageSize"), CURSOR("cursor");

    private String name;

    private ParamName(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }
  }

  // Multimap API uses flattened collection of key/value pairs, with multiple entries for multiple values with same key
  // Multimap.asMap() is subsequently used to convert this to a Map<String, Collection<String>> representation.
  // Use LinkedListMultimap to get reliable (insert) order for keys as well as values
  private LinkedListMultimap<String, String> params = LinkedListMultimap.create();

  /**
   * Builds the request parameters from the paging data contained in the supplied {@link PageCriteria} object.
   * 
   * @param pageCriteria A {@link PageCriteria} object containing the paging data. Can be null.
   */
  public PagingRequestParamsBuilder(PageCriteria pageCriteria) {
    if (pageCriteria == null) {
      return;
    }
    if (pageCriteria.getNextPageUrl() != null) {
      NextPageUrl nextPageUrl = NextPageUrl.parse(pageCriteria.getNextPageUrl());
      this.params.put(ParamName.CURSOR.getName(), nextPageUrl.getCursor());
    }
    if (pageCriteria.getPageSize() != null) {
      this.params.put(ParamName.PAGE_SIZE.getName(), pageCriteria.getPageSize().toString());
    }    
  }

  /**
   * @return A {@code Map<String, List<String>>} representation of thee request parameter names and their values.
   */
  public Map<String, List<String>> asMap() {
    // Multimap's asMap() methods convert
    return Multimaps.asMap(this.params);
  }
}