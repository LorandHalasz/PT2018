package controller;

import model.MonitoredData;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {

    static List<MonitoredData> readMonitoredData(){
        Supplier<ArrayList> list = () -> new ArrayList<MonitoredData>();
        List<MonitoredData> monitoredData = list.get();
        Path path1 = Paths.get("Activities.txt");
        Stream<String> lines;
        try{
            lines = Files.lines(path1);
            lines.forEach((p) -> {StringTokenizer stringTokenizer = new StringTokenizer(p, "\t");
                LocalDateTime startTime = LocalDateTime.parse(stringTokenizer.nextToken(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime finalTime = LocalDateTime.parse(stringTokenizer.nextToken(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String activity = stringTokenizer.nextToken();
                monitoredData.add(new MonitoredData(startTime, finalTime, activity));
                //monitoredData.forEach(m -> System.out.println(m.toString()));
            });
        }catch (IOException e) {
            e.printStackTrace();
        }
        return monitoredData;
    }

    static Map<String, Integer> deMap(List<MonitoredData> list2, Integer ziuaCurenta){
        Supplier<HashMap> harta1 = () -> new HashMap<String, Integer>();
        Map<String, Integer> mappedList3_1 =  harta1.get();

        list2.forEach((x) -> {
            String activity = x.getActivity();
            Predicate<String> moreThanOne = s -> mappedList3_1.containsKey(s);
            Predicate<Integer> isCurentDate =  ziuaCurenta1 -> x.getStartTime().getDayOfYear() == ziuaCurenta1;

            Boolean ok2 = isCurentDate.test(ziuaCurenta);

            Boolean ok = moreThanOne.test(activity);
            Integer count2 = ok2 ? (ok ? (mappedList3_1.replace(activity, mappedList3_1.get(activity) + 1)) : mappedList3_1.put(activity, 1)) : mappedList3_1.replace(activity, mappedList3_1.get(activity));
        });
        return mappedList3_1;
    }

    static List<String> comp(Map<String, Integer> mappedListTask5, Map<String, Integer> mappedList){
        Supplier<ArrayList> lista4 = () -> new ArrayList<String>();
        List<String> listTask5 = lista4.get();
        mappedListTask5.entrySet().forEach(x -> {
            String activity = x.getKey();
            Integer nr1 = x.getValue();
            Integer nr2 = mappedList.get(activity);
            Predicate<Integer> moreThanOne =  (s) -> (nr1 * s / nr2) >= 90;
            Boolean ok = moreThanOne.test(100);
            Boolean count = ok ? listTask5.add(activity) : false;
            });
        return listTask5;
    }

    static Map<String, Long> testTask4(List<MonitoredData> list) {

        Task4 task4 = (List<MonitoredData> list2) -> {

            Supplier<HashMap> harta2 = () -> new HashMap<String, Long>();
            Map<String, Long> mappedList2 = harta2.get();

            list2.forEach((x) -> {
                String activity = x.getActivity();
                Predicate<String> moreThanOne = s -> mappedList2.containsKey(s);
                Boolean ok = moreThanOne.test(activity);
                Long l = SECONDS.between(x.getStartTime(), x.getEndTime());
                Long count = ok ? mappedList2.replace(activity, mappedList2.get(activity) + l) : mappedList2.put(activity, l);
            });
            Map<String, Long> mappedList3 = harta2.get();
            mappedList2.entrySet().stream().filter(x -> x.getValue() >= 36000).forEach(y -> {
                mappedList3.put(y.getKey(), y.getValue());
            });
            return mappedList3;
        };

        return task4.mapare(list);
    }

    static Map<String, Integer> metTask2(List<MonitoredData> monitoredData){
        Supplier<HashMap> harta = () -> new HashMap<String, Integer>();
        Map<String, Integer> mappedList = harta.get();
        Task2 task2 = (List<MonitoredData> list2) -> {
            Supplier<HashMap> harta2 = () -> new HashMap<String, Integer>();
            Map<String, Integer> mappedList2 = harta2.get();
            list2.forEach((x) -> {
                String activity = x.getActivity();
                Predicate<String> moreThanOne = s -> mappedList2.containsKey(s);
                Boolean ok = moreThanOne.test(activity);
                Integer count = ok ? mappedList2.replace(activity, mappedList2.get(activity) + 1) : mappedList2.put(activity, 1);
            });
            return mappedList2;
        };
        mappedList = task2.mapare(monitoredData);
        return mappedList;
    }

    static Map<Integer, Map<String, Integer>> metTask3(List<MonitoredData> monitoredData){
        Stream<Integer> zileMonitorizare = monitoredData.stream().map(s -> s.getStartTime().getDayOfYear()).distinct();
        Supplier<HashMap> harta2 = () -> new HashMap<Integer, HashMap<String, Integer>>();
        Map<Integer, Map<String, Integer>> mappedListTask3 = harta2.get();

        Task3 task3 = (List<MonitoredData> list2, Stream<Integer> zi) -> {
            Supplier<HashMap> harta3 = () -> new HashMap<Integer, HashMap<String, Integer>>();
            Map<Integer, Map<String, Integer>> mappedList3 = harta3.get();
            zi.forEach(z -> {
                Supplier<HashMap> harta1 = () -> new HashMap<String, Integer>();
                Map<String, Integer> mappedList3_1 =  harta1.get();
                mappedList3_1 = deMap(list2, z);
                mappedList3.put(z, mappedList3_1);
            });
            return mappedList3;
        };
        mappedListTask3 = task3.mapare(monitoredData, zileMonitorizare);
        return mappedListTask3;
    }

    static List<String> metTask5(List<MonitoredData> monitoredData, Map<String, Integer> mappedList){
        Supplier<HashMap> harta = () -> new HashMap<String, Integer>();
        Map<String, Integer> mappedListTask5 = harta.get();
        Task2 task5 = (List<MonitoredData> list2) -> {
            Supplier<HashMap> harta5 = () -> new HashMap<String, Integer>();
            Map<String, Integer> mappedList5 = harta5.get();
            Supplier<ArrayList> lista = () -> new ArrayList<MonitoredData>();
            List<MonitoredData> list3 = lista.get();
            list2.stream().filter(x -> {Long l = SECONDS.between(x.getStartTime(), x.getEndTime());
                l = l / 60; return l <= 5;
            }).forEach(y -> {
                list3.add(y);
            });
            list3.forEach((x) -> {
                String activity = x.getActivity();
                Predicate<String> moreThanOne = s -> mappedList5.containsKey(s);
                Boolean ok = moreThanOne.test(activity);
                Integer count = ok ? mappedList5.replace(activity, mappedList5.get(activity) + 1) : mappedList5.put(activity, 1);
            });
            return mappedList5;
        };
        mappedListTask5 = task5.mapare(monitoredData);
        Supplier<ArrayList> lista4 = () -> new ArrayList<String>();
        List<String> listTask5 = lista4.get();
        listTask5 = comp(mappedListTask5, mappedList);
        return listTask5;
    }

    static void writeMonitoredData(Long nr, Map<String, Integer> mappedList, Map<Integer, Map<String, Integer>> mappedListTask3, Map<String, Long> mappedListTask4, List<String> listTask5){
        Path path2 = Paths.get("NrOfEachActivity.txt");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path2, StandardOpenOption.CREATE)) {
            bufferedWriter.write("Task 1 " + nr);
            bufferedWriter.newLine();
            bufferedWriter.write("Task 2 " + mappedList.toString());
            bufferedWriter.newLine();
            bufferedWriter.write("Task 3 " + mappedListTask3.toString());
            bufferedWriter.newLine();
            bufferedWriter.write("Task 4 " + mappedListTask4.toString());
            bufferedWriter.newLine();
            bufferedWriter.write("Task 5 " + listTask5.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        // task 0
        Supplier<ArrayList> list = () -> new ArrayList<MonitoredData>();
        List<MonitoredData> monitoredData = list.get();
        monitoredData = readMonitoredData();
        // task 1
        Long nr = monitoredData.stream().map(s -> s.getStartTime().getYear() + (s.getStartTime().getMonth()).getValue() + s.getStartTime().getDayOfMonth()).distinct().count();
        // task 2
        Supplier<HashMap> harta = () -> new HashMap<String, Integer>();
        Map<String, Integer> mappedList = harta.get();
        mappedList = metTask2(monitoredData);
        // task 3
        Supplier<HashMap> harta2 = () -> new HashMap<Integer, HashMap<String, Integer>>();
        Map<Integer, Map<String, Integer>> mappedListTask3 = harta2.get();
        mappedListTask3 = metTask3(monitoredData);
        // task 4
        Supplier<HashMap> harta4 = () -> new HashMap<String, Long>();
        Map<String, Long> mappedListTask4 = harta4.get();
        mappedListTask4 = testTask4(monitoredData);
        // task 5
        Supplier<ArrayList> lista4 = () -> new ArrayList<String>();
        List<String> listTask5 = lista4.get();
        listTask5 = metTask5(monitoredData, mappedList);
        writeMonitoredData(nr, mappedList, mappedListTask3, mappedListTask4, listTask5);
    }
}
