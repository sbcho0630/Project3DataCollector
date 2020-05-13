package com.sist.evaluationtrend;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class temp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String mLink = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=188993";
			mLink = mLink.replace("basic", "point");
			//System.out.println("mlink=" + mLink);
			Document doc2 = Jsoup.connect(mLink).get();
//			/System.out.println("doc2=" + doc2);
			
			  String doc = doc2.toString();
		        int sper=doc.indexOf("sPer")+7;
		        String atemp=doc.substring(sper,sper+3);
		        String a=atemp.trim();
		        int male_rating=Integer.parseInt(a);
		        
		        int sper2=doc.indexOf("sPer",sper)+7;
		        String btemp=doc.substring(sper2,sper2+2);
		        String b=btemp.trim();
		        int female_rating=Integer.parseInt(b);
		        System.out.println("femalerating="+female_rating);
		        
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
