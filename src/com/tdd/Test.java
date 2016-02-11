package com.tdd;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


public class Test {

	public static void main(String[] args) {
		String startDateArray[]={"2007-01-01","2007-01-01","2007-01-01","2007-03-01","2007-03-01","2007-03-01","2007-05-01","2007-05-01","2007-05-01","2007-07-01"};
        System.out.println("Its Second Checkin");
		//getCommonDates(startDateArray);
		Locale locale;
		locale=Locale.GERMAN;
		String str="hj66r,5sdf5";
		
		DecimalFormat numberFormatter=null;
		System.out.println("tmpStr "+str);
		try
		{
		//System.out.println("StringUtils.isNumeric(tmpStr); "+StringUtils.isNumeric(str));
		/*num = Double.parseDouble( tmpStr );
		if( Double.isNaN(num) )
			return "";*/
		
		 numberFormatter = new DecimalFormat("0.00",new DecimalFormatSymbols(locale));

		NumberFormat numFormat = NumberFormat.getInstance(locale);

		if (numFormat instanceof DecimalFormat)
		{
			int size = ((DecimalFormat) numFormat).getGroupingSize();
			numberFormatter.setGroupingSize(size);
			numberFormatter.setGroupingUsed(true);
		}
		
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		double myNumber = nf.parse(str).doubleValue();
		System.out.println("numberFormatter.format( str ); "+myNumber);
	}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//return numberFormatter.format( num );
		
		
	}
	
	private static String[] getCommonDates(String startDate[])
	{
		
		
		int len=startDate.length;
		String rowSpan[]=new String[startDate.length];
		
		List list=new ArrayList();
		
		for(int i=0;i<len;i++)
		{
			list.add(startDate[i]);
		}
		
		Set<String> uniqueSet = new HashSet<String>(list);
		for (String temp : uniqueSet) {
			
			System.out.println(temp + ": " + Collections.frequency(list, temp));
			for(int i=0;i<len;i++)
			{
				if(startDate[i].equals(temp))
				{
					rowSpan[i]=temp+":"+Collections.frequency(list, temp)+"";
				}
			}
		}
		for(int i=0;i<rowSpan.length;i++)
		{
			System.out.println(" i "+i +" rowSpan "+rowSpan[i]);
		}
		return rowSpan;
	}

}
