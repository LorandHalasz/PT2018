package controller;

import model.MonitoredData;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Task2 {
    Map<String, Integer> mapare(List<MonitoredData> list);
}



