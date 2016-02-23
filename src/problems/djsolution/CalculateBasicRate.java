package problems.djsolution;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateBasicRate {
    private String startDate;
    private String endDate;
    private List<Double> spectrumList;
    private List<Double> percentMultiplierList=Arrays.asList(11.2d,23.5d,36d,42d,35d,64d,73d);
    private int lengthOfDays=1;
    public CalculateBasicRate(String startDate, String endDate) {
        this.startDate=startDate;
        this.endDate=endDate;
        if(endDate.contains("+")) {
            lengthOfDays= Integer.parseInt(endDate.split("\\+")[1])+1;
        }
    }
    public enum DOW {
        MONDAY {
            @Override
            public void setSpectrumForDow(List<Double> spectrumList,Double percentMultiplier) {
                setSpectrumAverage(calculateAverageSpectrum(spectrumList));
                setPercentageMultiplier(percentMultiplier);
            }
        },TUESDAY {
            @Override
            public void setSpectrumForDow(List<Double> spectrumList,Double percentMultiplier) {
                setSpectrumAverage(calculateAverageSpectrum(spectrumList));
                setPercentageMultiplier(percentMultiplier);
            }
        },WEDNESDAY {
            @Override
            public void setSpectrumForDow(List<Double> spectrumList,Double percentMultiplier) {
                setSpectrumAverage(calculateAverageSpectrum(spectrumList));     
                setPercentageMultiplier(percentMultiplier);
            }
        },THURSDAY {
            @Override
            public void setSpectrumForDow(List<Double> spectrumList,Double percentMultiplier) {
                setSpectrumAverage(calculateAverageSpectrum(spectrumList));   
                setPercentageMultiplier(percentMultiplier);
            }
        },FRIDAY {
            @Override
            public void setSpectrumForDow(List<Double> spectrumList,Double percentMultiplier) {
                setSpectrumAverage(calculateAverageSpectrum(spectrumList));
                setPercentageMultiplier(percentMultiplier);
            }
        },SATURDAY {
            @Override
            public void setSpectrumForDow(List<Double> spectrumList,Double percentMultiplier) {
                setSpectrumAverage(calculateAverageSpectrum(spectrumList));  
                setPercentageMultiplier(percentMultiplier);
            }
        },SUNDAY {
            @Override
            public void setSpectrumForDow(List<Double> spectrumList,Double percentMultiplier) {
                setSpectrumAverage(calculateAverageSpectrum(spectrumList));
                setPercentageMultiplier(percentMultiplier);
            }
        };
        private double basicRate;
        private double percentMultiplier;
        private Double spectrumAverage;
        
        public Double calculateAverageSpectrum(List<Double> spectrumList) {
            Double spectrumAverage;
            if(spectrumList!=null) {
                spectrumAverage=spectrumList.stream().reduce(0d, (a,e)->a+e)/spectrumList.size();
            }else {
                spectrumAverage=null;
            }
            return spectrumAverage;
        }
        public double getBasicRate() {
            calculateBasicRate();
            return basicRate;
        }
        public double getPercentageMultiplier() {
            return percentMultiplier;
        }
        public void setPercentageMultiplier(double percentMultiplier) {
            this.percentMultiplier=percentMultiplier;
        }
        public Double getSpectrumAverage() {
            return spectrumAverage;
        }
        public void setSpectrumAverage(double spectrumAverage) {
            this.spectrumAverage=spectrumAverage;
        }
        private void calculateBasicRate() {
            if(spectrumAverage!=null) {
                basicRate = spectrumAverage * (percentMultiplier /100 ); 
            }
        }
        public abstract void setSpectrumForDow(List<Double> spectrumList, Double double1);
        
        
    }

    public Map<String,Double> calculateRate() {
        Map<String,Double> mapOfBasicRate=new HashMap<String, Double>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime date=LocalDateTime.now();
        for(int i=0;i<lengthOfDays;i++) {
            LocalDateTime plusDays = date.plusDays(i);
            double basicRate = calculateBasicRateForDow(plusDays);
            mapOfBasicRate.put(plusDays.format(formatter), basicRate);
        }
        return mapOfBasicRate;
        
    }

    private double calculateBasicRateForDow(LocalDateTime plusDays) {
        DOW dow = DOW.valueOf(plusDays.getDayOfWeek().name());
        dow.setSpectrumForDow(spectrumList,percentMultiplierList.get(dow.ordinal()));
        double basicRate = dow.getBasicRate();
        return basicRate;
    }

    public List<Double> getSpectrumList() {
        return spectrumList;
    }

    public void setSpectrumList(List<Double> spectrumList) {
        this.spectrumList = spectrumList;
    }
}