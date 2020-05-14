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
				System.out.println("doc2=" + doc2);
			
			  String doc = doc2.toString();
		        int sper=doc.indexOf("sPer")+5;  //   ':' 의 인덱스
		        String atemp=doc.substring(sper,sper+10);
		        atemp=atemp.substring(atemp.indexOf(":")+1,atemp.indexOf("}"));
		        System.out.println("atemp=="+atemp);
		        String a=atemp.trim();
		        System.out.println("a="+a);
		        int male_rating=Integer.parseInt(a);
		        
		        int sper2=doc.indexOf("sPer",sper)+5;
		        String btemp=doc.substring(sper2,sper2+10);
		        btemp=btemp.substring(btemp.indexOf(":")+1,btemp.indexOf("}"));
		        System.out.println("btemp=="+btemp);
		        String b=btemp.trim();
		        System.out.println("b="+b);
		        int female_rating=Integer.parseInt(b);
		        System.out.println("femalerating="+female_rating);
		        
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
