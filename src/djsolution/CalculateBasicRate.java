package djsolution;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CalculateBasicRate {
    private String startDate;
    private String endDate;
    private Map<DayOfWeek,List<Double>> spectrumList;
    private int lengthOfDays=1;
    private final Map<DayOfWeek,Double> percentMultiplierMap;
    public CalculateBasicRate(String startDate, String endDate, Map<DayOfWeek, Double> percentMultiplierMap) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.percentMultiplierMap=percentMultiplierMap;
        if(endDate.contains("+")) {
            lengthOfDays= Integer.parseInt(endDate.split("\\+")[1])+1;
        }
    }
   class DOW {
        private String name;
        public DOW(String name, Double percentMultiplier, List<Double> spectrumList) {
            super();
            this.percentMultiplier=percentMultiplier;
            this.name = name;
            this.spectrumAverage=calculateAverageSpectrum(spectrumList);
        }
        private Double basicRate;
        private double percentMultiplier;
        private Double spectrumAverage;
        
        public Double calculateAverageSpectrum(List<Double> spectrumList) {
            if(spectrumList!=null) {
                spectrumAverage=spectrumList.stream().reduce(0d, (a,e)->a+e)/spectrumList.size();
            }else {
                spectrumAverage=null;
            }
            return spectrumAverage;
        }
        public Double getBasicRate() {
            calculateBasicRate();
            return basicRate;
        }
        private void calculateBasicRate() {
            if(spectrumAverage!=null) {
                basicRate = spectrumAverage * (percentMultiplier /100 ); 
            }
        }
    }

    public Map<String,Double> calculateRate() {
        Map<String,Double> mapOfBasicRate=new HashMap<String, Double>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime date=LocalDateTime.now();
        for(int i=0;i<lengthOfDays;i++) {
            LocalDateTime plusDays = date.plusDays(i);
            
            Function<DayOfWeek,Double> calculateBasicRateForDow= dayOfWeek->{
                List<Double> dowSpecificList=spectrumList.get(dayOfWeek);
                DOW dow = new DOW(dayOfWeek.name(),percentMultiplierMap.get(dayOfWeek), dowSpecificList);
                Double basicRate = dow.getBasicRate();
                return basicRate;
            };
            Function<DayOfWeek,Double> memoizedCalculateBasicRateForDow =  memoizeCalculateBasicRateForDow(calculateBasicRateForDow);
            
            //Double basicRate = calculateBasicRateForDow(plusDays.getDayOfWeek());
            Double basicRate = memoizedCalculateBasicRateForDow.apply(plusDays.getDayOfWeek());
            mapOfBasicRate.put(plusDays.format(formatter), basicRate);
        }
        return mapOfBasicRate;
    }
    
 private static <T, R> Function<T, R> memoizeCalculateBasicRateForDow(Function<T, R> fn) {
        final Map<T,R> cache= new HashMap<>();
        return t -> {
            if(cache.containsKey(t)) {
                return cache.get(t);
            }
            else {
                R r= fn.apply(t);
                cache.put(t,r);
                return r;
            }
        };
    }
    
    /*private Double calculateBasicRateForDow(DayOfWeek dayOfWeek) {
        List<Double> dowSpecificList=spectrumList.get(dayOfWeek);
        DOW dow = new DOW(dayOfWeek.name(),percentMultiplierMap.get(dayOfWeek), dowSpecificList);
        Double basicRate = dow.getBasicRate();
        return basicRate;
    }*/
     
 
    public void setSpectrumList(Map<DayOfWeek, List<Double>> spectrumList) {
        this.spectrumList = spectrumList;
    }

    public Map<DayOfWeek, List<Double>> getSpectrumList() {
        return spectrumList;
    }
}