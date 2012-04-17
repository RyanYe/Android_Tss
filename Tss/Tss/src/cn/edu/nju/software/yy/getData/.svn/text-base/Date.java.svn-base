package cn.edu.nju.software.yy.getData;

public class Date {

	public int year,month,day,hour,minute;
	public String s;
	public Date(int y,int m,int d,int h,int mi){
		year = y;
		month = m;
		day = d;
		hour = h;
		minute =  mi;
	}
	public static Date parseDate(String date){
		
		String list[] = date.split(" ");
		
		String days = list[0];
		int year = Integer.parseInt(days.split("-")[0]);
		int month = Integer.parseInt(days.split("-")[1]);
		int day =Integer.parseInt(days.split("-")[2]);
		
		int hour = Integer.parseInt(list[1].split(":")[0]);
		int min = Integer.parseInt(list[1].split(":")[1]);
		
		Date dates = new Date(year,month,day,hour,min);
		
		return dates;
		
		
	}
	public boolean unread(Date lastLoginDate){
		if(year>lastLoginDate.year)
			return true;
		else if(year<lastLoginDate.year)
			return false;
		else{
			if(month > lastLoginDate.month){
				return true;
			}
			else if(month<lastLoginDate.month)
				return false;
			else{
				if(day>lastLoginDate.day)
					return true;
				else if(day<lastLoginDate.day)
					return false;
				else{
					if(hour>lastLoginDate.hour)
						return true;
					else if(hour<lastLoginDate.hour)
						return false;
					else{
						if(minute>=lastLoginDate.minute)
							return true;
						else 
							return false;
					}
				}
			}
		}
	}
	
	
	public String toString(){
		return ""+year+"-"+month+"-"+day+" "+hour+":"+minute;
		
	}
	
	
	
	
	
}
