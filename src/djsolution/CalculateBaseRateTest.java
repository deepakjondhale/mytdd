package djsolution;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CalculateBaseRateTest {
    static  CalculateBasicRate basicRate;
    static Map<DayOfWeek,List<Double>> spectrumList=new HashMap<DayOfWeek, List<Double>>();
    static Map<DayOfWeek,Double> percentMultiplierMap=new HashMap<DayOfWeek, Double>();
    //CalculateBasicRate.DOW MONDAY=new CalculateBasicRate.DOW(null, null, null);
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        
        List<Double> mondayList=new ArrayList<Double>();
        mondayList.add(12.4d);
        mondayList.add(13.6d);
        spectrumList.put(DayOfWeek.MONDAY,mondayList);
        List<Double> tuesdayList=new ArrayList<Double>();
        tuesdayList.add(22.4d);
        tuesdayList.add(53.6d);
        spectrumList.put(DayOfWeek.TUESDAY,tuesdayList);
        List<Double> wednesdayList=new ArrayList<Double>();
        wednesdayList.add(11.3d);
        wednesdayList.add(13.3d);
        spectrumList.put(DayOfWeek.WEDNESDAY,wednesdayList);
        spectrumList.put(DayOfWeek.THURSDAY,null);
        spectrumList.put(DayOfWeek.FRIDAY,null);
        List<Double> saturdayList=new ArrayList<Double>();
        saturdayList.add(11.3d);
        saturdayList.add(16.3d);
        spectrumList.put(DayOfWeek.SATURDAY,saturdayList);
        List<Double> sundayList=new ArrayList<Double>();
        sundayList.add(21.3d);
        sundayList.add(16.3d);
        spectrumList.put(DayOfWeek.SUNDAY,sundayList);
        
        percentMultiplierMap.put(DayOfWeek.MONDAY,11.2d);
        percentMultiplierMap.put(DayOfWeek.TUESDAY,11.2d);
        percentMultiplierMap.put(DayOfWeek.WEDNESDAY,11.2d);
        percentMultiplierMap.put(DayOfWeek.THURSDAY,11.2d);
        percentMultiplierMap.put(DayOfWeek.FRIDAY,11.2d);
        percentMultiplierMap.put(DayOfWeek.SATURDAY,11.2d);
        percentMultiplierMap.put(DayOfWeek.SUNDAY,11.2d);
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
        basicRate=new CalculateBasicRate("TODAY", "TODAY", percentMultiplierMap);
        basicRate.setSpectrumList(spectrumList);
        Map<String, Double> calculateRate = basicRate.calculateRate();
        assertEquals(1.3776, calculateRate.get("2016-02-24"),0.01);
    }
    
    @Test
    public void getBasicRateForTodayAndTodayPlus1() {
        basicRate=new CalculateBasicRate("TODAY", "TODAY+1", percentMultiplierMap);
        basicRate.setSpectrumList(spectrumList);
        Map<String, Double> calculateRate = basicRate.calculateRate();
        assertEquals(1.3776, calculateRate.get("2016-02-24"),0.01);
        assertEquals(null, calculateRate.get("2016-02-25"));
    }
    

    @Test
    public void getBasicRateForTodayAndTodayPlus8() {
        basicRate=new CalculateBasicRate("TODAY", "TODAY+8", percentMultiplierMap);
        basicRate.setSpectrumList(spectrumList);
        Map<String, Double> calculateRate = basicRate.calculateRate();
        assertEquals(1.3776, calculateRate.get("2016-02-24"),0.01);
        assertEquals(null, calculateRate.get("2016-02-25"));
        assertEquals(null, calculateRate.get("2016-02-26"));
        assertEquals(1.5456, calculateRate.get("2016-02-27"),0.01);
    }
}
