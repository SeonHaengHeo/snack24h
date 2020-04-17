package com.snack24h.snack24h.model.enumtype;

public enum YN {
  Y("O"),N("X");


  private String title;
  YN(String title){
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }
}
