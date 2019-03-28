package controller;

import model.MonitoredData;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Task4 {
    Map<String, Long> mapare(List<MonitoredData> list);
}
