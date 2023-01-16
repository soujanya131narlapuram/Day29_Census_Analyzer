package com.bridgelabz;

import com.bridgelabz.CensusAnalyserException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class StateCensusAnalyser {
    private int recordCount;

    public void loadData(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Iterator<String[]> iterator = br.lines().map(line -> line.split(",")).iterator();
            while (iterator.hasNext()) {
                iterator.next();
                recordCount++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new CensusAnalyserException("File not found", CensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new CensusAnalyserException("Incorrect file type", CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE);
        }
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void compareRecordCount(CSVStates states) {
        if (recordCount == states.getRecordCount()) {
            System.out.println("Number of records matches the expected value of " + states.getRecordCount());
        } else {
            System.out.println("Number of records does not match the expected value of " + states.getRecordCount() + ". Actual value is " + recordCount);
        }
    }

    public static void main(String[] args) {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        CSVStates states = new CSVStates();
        states.loadData("C:\\Users\\soujanya\\IdeaProjects\\Day29_Census_Analyser\\src\\main\\java\\com\\bridgelabz\\IndiaStateCode.csv");
        analyser.loadData("C:\\Users\\soujanya\\IdeaProjects\\Day29_Census_Analyser\\src\\main\\java\\com\\bridgelabz\\IndiaStateCensusData.csv");
        analyser.compareRecordCount(states);
    }
}
