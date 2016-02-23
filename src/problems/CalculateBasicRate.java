package problems;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateBasicRate {
    private String startRange;
    private String endRange;
    private Calendar cal;
    private int length;
    private Map<Integer, Double> dowWisePM;
    private Map<Integer, List<Double>> spectrumDowWise;
    
    public CalculateBasicRate(String startRange, String endRange) {
        this.startRange=startRange;
        this.endRange=endRange;
    }

    public Map<String,Double> prepareData() {
        Map<String,Double> basicRateMap=new HashMap<>();
        
        length = calculateLengthOfRange();
        cal=Calendar.getInstance();
        for(int i=0;i<length;i++) {
            cal.add(Calendar.DATE, i);
            int dow = cal.get(Calendar.DAY_OF_WEEK);
            Date date=cal.getTime();
            SimpleDateFormat formatter=new SimpleDateFormat("YYYY-MM-dd");
            
            Double dowWisePM = fetchDowWisePM(dow);
            Double averageOfSpectrum = computeAverageOfSpectrumIfAvailableByDow(dow);
            calculateBasicRate(basicRateMap, dow, dowWisePM, averageOfSpectrum);
        }
        return basicRateMap;
    }

    private void calculateBasicRate(Map<String, Double> basicRateMap, int dow, Double dowWisePM, Double averageOfSpectrum) {
        if(averageOfSpectrum!=null) {
            basicRateMap.put(dow+"", averageOfSpectrum*(dowWisePM/100));
        }
    }
    
    
    
    private Double computeAverageOfSpectrumIfAvailableByDow(int dow) {
        List<Double> list = spectrumDowWise.get(dow);
        Double average=null;
        if(list!=null) {
            long count = list.stream().count();
            Double sum = list.stream().reduce(0d, (a, b) -> a+b);
            average=sum/count;
        }
        return average;
    }

 
    private Double fetchDowWisePM(int dow) {
        return dowWisePM.get(dow);
    }

    private int calculateLengthOfRange() {
        int len=-1;
        
        if(endRange.contains("+")) {
            len= Integer.parseInt(endRange.split("\\+")[1])+1;
        }else if(endRange.equals(startRange)) {
            len=1;
        }
        return len;
    }

    public void setDOWData(Map<Integer,Double> dowwisePM) {
        this.dowWisePM=dowwisePM;
    }

    public void setSpectrumData(Map<Integer, List<Double>> spectrumDowWise2) {
        this.spectrumDowWise=spectrumDowWise2;
    }


}
