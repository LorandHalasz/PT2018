package controller;

import model.MonitoredData;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@FunctionalInterface
public interface Task3 {
    Map<Integer, Map<String, Integer>> mapare(List<MonitoredData> list, Stream<Integer> zi);
}



