package djsolution;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculateBaseRateTest {
    static  CalculateBasicRate basicRate;
    static List<Double> spectrumList=new ArrayList<>();
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        spectrumList.add(12.3d);
        spectrumList.add(13.3d);
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
        basicRate=new CalculateBasicRate("TODAY", "TODAY");
        basicRate.setSpectrumList(spectrumList);
        Map<String, Double> calculateRate = basicRate.calculateRate();
        assertEquals(1.433, calculateRate.get("2016-02-22"),0.01);
    }
    
    @Test
    public void getBasicRateForTodayAndTodayPlus1() {
        basicRate=new CalculateBasicRate("TODAY", "TODAY+1");
        basicRate.setSpectrumList(spectrumList);
        Map<String, Double> calculateRate = basicRate.calculateRate();
        assertEquals(1.433, calculateRate.get("2016-02-22"),0.01);
        assertEquals(3.008, calculateRate.get("2016-02-23"),0.01);
    }
    

    @Test
    public void getBasicRateForTodayAndTodayPlus8() {
        basicRate=new CalculateBasicRate("TODAY", "TODAY+8");
        basicRate.setSpectrumList(spectrumList);
        Map<String, Double> calculateRate = basicRate.calculateRate();
        assertEquals(1.433, calculateRate.get("2016-02-22"),0.01);
        assertEquals(3.008, calculateRate.get("2016-02-23"),0.01);
        assertEquals(1.433, calculateRate.get("2016-02-29"),0.01);
        assertEquals(3.008, calculateRate.get("2016-03-01"),0.01);
    }
}
