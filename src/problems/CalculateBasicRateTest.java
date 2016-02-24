package problems;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculateBasicRateTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBasicRateForToday() {
        CalculateBasicRate basicRate=new CalculateBasicRate("TODAY+1","TODAY");
        
        Map<Integer, Double> dowWisePM=new HashMap<Integer, Double>();
        dowWisePM.put(Calendar.FRIDAY, 10d);
        basicRate.setDOWData(dowWisePM);
        
        Map<Integer, List<Double>> spectrumDowWise=new HashMap<Integer, List<Double>>();
        
        List<Double> spectrumForDOWFriday=new ArrayList<>();
        spectrumForDOWFriday.add(12d);
        
        spectrumDowWise.put(Calendar.FRIDAY, spectrumForDOWFriday);
        basicRate.setSpectrumData(spectrumDowWise);
        
        Map<String, Double> calculateMap = basicRate.prepareData();
        
        assertEquals(1.2, calculateMap.get(Calendar.FRIDAY), 0.01);
    }
    
    @Test
    public void ableToTestTodayAndTodayPlus_1_Data() {
        CalculateBasicRate basicRate=new CalculateBasicRate("TODAY","TODAY+1");
        
        Map<Integer, Double> dowWisePM=new HashMap<Integer, Double>();
        dowWisePM.put(Calendar.FRIDAY, 10d);
        dowWisePM.put(Calendar.SATURDAY, 12d);
        basicRate.setDOWData(dowWisePM);
        
        Map<Integer, List<Double>> spectrumDowWise=new HashMap<Integer, List<Double>>();
        
        List<Double> spectrumForDOWFriday=new ArrayList<>();
        spectrumForDOWFriday.add(12d);
        
        spectrumDowWise.put(Calendar.FRIDAY, spectrumForDOWFriday);
        List<Double> spectrumForDOWSaturday=new ArrayList<>();
        spectrumForDOWSaturday.add(43.5);
        spectrumForDOWSaturday.add(4.45);
        spectrumForDOWSaturday.add(47.45);
        
        spectrumDowWise.put(Calendar.SATURDAY, spectrumForDOWSaturday);
        basicRate.setSpectrumData(spectrumDowWise);
        
        Map<String, Double> calculateMap = basicRate.prepareData();
        
        assertEquals(1.2, calculateMap.get(Calendar.FRIDAY), 0.01);
        assertEquals(3.816, calculateMap.get(Calendar.SATURDAY), 0.01);
    }
    
    @Test
    public void ableToTestTodayAndTodayPlus_2_Data() {
        CalculateBasicRate basicRate=new CalculateBasicRate("TODAY","TODAY+2");
        
        Map<Integer, Double> dowWisePM=new HashMap<Integer, Double>();
        dowWisePM.put(Calendar.FRIDAY, 10d);
        dowWisePM.put(Calendar.SATURDAY, 12d);
        dowWisePM.put(Calendar.SUNDAY, 14d);
        basicRate.setDOWData(dowWisePM);
        
        Map<Integer, List<Double>> spectrumDowWise=new HashMap<Integer, List<Double>>();
        
        List<Double> spectrumForDOWFriday=new ArrayList<>();
        spectrumForDOWFriday.add(12d);
        
        spectrumDowWise.put(Calendar.FRIDAY, spectrumForDOWFriday);
        List<Double> spectrumForDOWSaturday=new ArrayList<>();
        spectrumForDOWSaturday.add(43.5);
        spectrumForDOWSaturday.add(4.45);
        spectrumForDOWSaturday.add(47.45);
        
        spectrumDowWise.put(Calendar.SATURDAY, spectrumForDOWSaturday);
        basicRate.setSpectrumData(spectrumDowWise);
        
        Map<String, Double> calculateMap = basicRate.prepareData();
        
        assertEquals(1.2, calculateMap.get(Calendar.FRIDAY), 0.01);
        assertEquals(3.816, calculateMap.get(Calendar.SATURDAY), 0.01);
        assertEquals(null, calculateMap.get(Calendar.SUNDAY));
    }
    
    @Test
    public void ableToTestToday_To_TodayPlus_3_Data() {
        CalculateBasicRate basicRate=new CalculateBasicRate("TODAY","TODAY+3");
        
        Map<Integer, Double> dowWisePM=new HashMap<Integer, Double>();
        dowWisePM.put(Calendar.FRIDAY, 10d);
        dowWisePM.put(Calendar.SATURDAY, 12d);
        dowWisePM.put(Calendar.SUNDAY, 14d);
        dowWisePM.put(Calendar.MONDAY, 788d);
        basicRate.setDOWData(dowWisePM);
        
        Map<Integer, List<Double>> spectrumDowWise=new HashMap<Integer, List<Double>>();
        
        List<Double> spectrumForDOWFriday=new ArrayList<>();
        spectrumForDOWFriday.add(12d);
        
        spectrumDowWise.put(Calendar.FRIDAY, spectrumForDOWFriday);
        List<Double> spectrumForDOWSaturday=new ArrayList<>();
        spectrumForDOWSaturday.add(43.5);
        spectrumForDOWSaturday.add(4.45);
        spectrumForDOWSaturday.add(47.45);
        
        spectrumDowWise.put(Calendar.SATURDAY, spectrumForDOWSaturday);
        basicRate.setSpectrumData(spectrumDowWise);
        
        List<Double> spectrumForDOWModay=new ArrayList<>();
        spectrumForDOWModay.add(13.5);
        spectrumForDOWModay.add(224.45);
        spectrumForDOWModay.add(447.45);
        spectrumForDOWModay.add(47.7);
        spectrumForDOWModay.add(567.15);
        spectrumDowWise.put(Calendar.MONDAY, spectrumForDOWModay);
        Map<String, Double> calculateMap = basicRate.prepareData();
        
        assertEquals(1.2, calculateMap.get(Calendar.FRIDAY), 0.01);
        assertEquals(3.816, calculateMap.get(Calendar.SATURDAY), 0.01);
        assertEquals(null, calculateMap.get(Calendar.SUNDAY));
        
    }
}
