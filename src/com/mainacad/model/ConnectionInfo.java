package com.mainacad.model;

import java.util.Objects;

public class ConnectionInfo implements Comparable<ConnectionInfo> {

  private User user;
  private Integer sessionId;
  private String userIp;
  private Long time;

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getSessionId() {
    return sessionId;
  }

  public void setSessionId(Integer sessionId) {
    this.sessionId = sessionId;
  }

  public String getUserIp() {
    return userIp;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }

  public ConnectionInfo() {
  }

  public ConnectionInfo(Integer sessionId, String userIp, Long time, User user) {
    this.user = user;
    this.sessionId = sessionId;
    this.userIp = userIp;
    this.time = time;
  }

  @Override
  public int compareTo(ConnectionInfo connectionInfo) {
    if (this.time > connectionInfo.time) {
      return 1;
    } else if (this.time < connectionInfo.time) {
      return -1;
    }
    return 0;
  }

  @Override
  public String toString() {
    return time + " " + sessionId + " " + userIp + " " + user.getLogin() + " " +
        user.getPassword() + " " + user.getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectionInfo that = (ConnectionInfo) o;
    return user.equals(that.user) &&
        sessionId.equals(that.sessionId) &&
        userIp.equals(that.userIp) &&
        time.equals(that.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, sessionId, userIp, time);
  }
}
