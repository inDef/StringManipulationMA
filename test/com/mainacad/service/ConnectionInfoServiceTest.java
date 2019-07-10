package com.mainacad.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mainacad.model.ConnectionInfo;
import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.jupiter.api.Test;

class ConnectionInfoServiceTest {

  @Test
  void testGenerateConnectionInfoQueueNegativeAndZeroSize() {
    Queue<ConnectionInfo> connections2 = ConnectionInfoService.generateConnectionInfoQueue(-5);
    assertEquals(0, connections2.size());
    Queue<ConnectionInfo> connections1 = ConnectionInfoService.generateConnectionInfoQueue(0);
    assertEquals(0, connections1.size());
  }

  @Test
  void testGenerateConnectionInfoQueueProperSize() {
    Queue<ConnectionInfo> connections1 = ConnectionInfoService.generateConnectionInfoQueue(5);
    assertEquals(5, connections1.size());
  }

  @Test
  void testGenerateConnectionQueueNotNullObjects() {
    Queue<ConnectionInfo> connections = ConnectionInfoService.generateConnectionInfoQueue(7);
    for (ConnectionInfo connection : connections) {
      assertNotNull(connection);
    }
  }

  @Test
  void testGetConnectionsAsTextWithEmptyQueueParameter() {
    Queue<ConnectionInfo> emptyConnections = new PriorityQueue<>();
    assertEquals("", ConnectionInfoService.getConnectionsAsText(emptyConnections));
  }

  @Test
  void testGetConnectionsAsTextProperFormatOutput() {
    Queue<ConnectionInfo> connections = ConnectionInfoService.generateConnectionInfoQueue(50);
    String output = ConnectionInfoService.getConnectionsAsText(connections);
    assertTrue(output.matches("(\\d{13}\\s\\d{7}\\s(\\d{1,3}.?){4}\\s" +
        "[A-z0-9]+\\s[A-z0-9]+\\s\\d{5}\\n)+"));
  }

  @Test
  void testGetConnectionsFromTextWrongFormatInputString() {
    String emptyString = "";
    assertEquals(0, ConnectionInfoService.getConnectionsFromText(emptyString).size());
    String wrongFormatString = "123456d890123 1234567 123.123.123.13 sldkfjASDFkasdf aslkdfj213 12345\n";
    assertEquals(0, ConnectionInfoService.getConnectionsFromText(wrongFormatString).size());
  }

  @Test
  void testGetConnectionsFromTextProperFormatInputString() {
    String input = "1234567890123 1234567 123.123.123.123 sldkfjASDFkasdf aslkdfj213 12345\n"
        + "1234567890124 1234567 123.123.123.123 sldkfjASDFkasdf aslkdfj213 12345\n";
    assertEquals(2, ConnectionInfoService.getConnectionsFromText(input).size());
  }

  @Test
  void testFilterConnectionsByTimeProperInput() {
    long timeFrom = new Date(2000, Calendar.JANUARY, 1).getTime();
    long timeTo = new Date(2010, Calendar.JANUARY, 1).getTime();
    Queue<ConnectionInfo> connections = ConnectionInfoService.generateConnectionInfoQueue(50);
    Queue<ConnectionInfo> filteredConnections = ConnectionInfoService
        .filterConnectionsByTime(connections, timeFrom, timeTo);
    for (ConnectionInfo connection : filteredConnections) {
      assertTrue(connection.getTime() >= timeFrom && connection.getTime() <= timeTo);
    }
  }

  @Test
  void testFilterConnectionsByTimeWithFromTimeBiggerThanToTime() {
    long timeFrom = new Date(2010, Calendar.JANUARY, 1).getTime();
    long timeTo = new Date(2000, Calendar.JANUARY, 1).getTime();
    Queue<ConnectionInfo> connections = ConnectionInfoService.generateConnectionInfoQueue(50);
    Queue<ConnectionInfo> filteredConnections = ConnectionInfoService
        .filterConnectionsByTime(connections, timeFrom, timeTo);
    assertEquals(0, filteredConnections.size());
  }

  @Test
  void testFilterConnectionsByTimeEmptyQueueInput() {
    long timeFrom = new Date(2000, Calendar.JANUARY, 1).getTime();
    long timeTo = new Date(2010, Calendar.JANUARY, 1).getTime();
    Queue<ConnectionInfo> connections = new PriorityQueue<>();
    Queue<ConnectionInfo> filteredConnections = ConnectionInfoService
        .filterConnectionsByTime(connections, timeFrom, timeTo);
    assertEquals(0, filteredConnections.size());
  }
}