package com.snack24h.snack24h.model.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ServiceResult implements Serializable {
  private boolean OK;
  private String message;
  private String returnURI;
  private Map<String,Object> map;

  public Map<String,Object> getMap(){
    if(this.map==null){
      map = new HashMap<>();
    }
    return this.map;
  }
}
