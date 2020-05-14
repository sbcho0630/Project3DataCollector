package com.sist.manager;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.WatchingTrendDAO;
import com.sist.dao.WatchingTrendVO;

public class WatchingTrendManager {
	public List<WatchingTrendVO> WatchingData() {
		List<WatchingTrendVO> list = new ArrayList<WatchingTrendVO>();
		int count = 0;
		int preMovieId = -1;
		boolean isSameMovieId = false;
		try {

			WatchingTrendDAO dao = new WatchingTrendDAO();
			for (int k = 11; k <= 18; k++) {

				isSameMovieId = false;

				String mainlink = "https://movie.naver.com/movie/sdb/browsing/bmovie.nhn?form=" + k + "&page=";
				// System.out.println("mainlink="+mainlink);
				for (int i = 1; i <= 2800; i++) {
					System.out.println("i : " + i);
					Document doc = Jsoup.connect(mainlink + i).get();
					// System.out.println("mainlink+i="+mainlink+i);
					Elements link = doc.select("ul.directory_list > li > a");

					if (link == null) {
						break;
					}

					// System.out.println(link);

					// link=movie/bi/mi/basic.nhn?code=24239
					for (int j = 0; j < link.size(); j++) {
						// link.size()는 페이지당 a링크의 갯수 ==> 네이버 뮤직의경우 한페이지에 20개 따라서 link.size()=20
						// System.out.println("j : " + j);
						try {
							Element elem = link.get(j);
							//// system.out.println((i) + "-" + elem.attr("href"));
							String mLink = "https://movie.naver.com/" + elem.attr("href");
							// String mLink = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=190721";
							mLink = mLink.replace("basic", "point");
							//System.out.println("mlink=" + mLink);
							Document doc2 = Jsoup.connect(mLink).get();
							 //System.out.println("doc2=" + doc2);
							String str = elem.attr("href");
							String movieno = str.substring(str.lastIndexOf("=") + 1);
							int movie_id = Integer.parseInt(movieno);
							System.out.println("movie_id=" + movie_id);
							//// system.out.println("preMovieId : " + preMovieId);
							//// system.out.println("curMovieId : " + movie_id);
							if (preMovieId == movie_id) {
								isSameMovieId = true;
								break;
							}
							count++;
							if (j == 0) {
								preMovieId = movie_id;
							}
							// Watchingtrend 수집 시작

							Element age_10 = doc2.select("div.graph_box strong.graph_percent").get(0);
							String age1 = age_10.text();
							String age10 = age1.replaceAll("%", "");
							System.out.println("age_10=" + age10);

							Element age_20 = doc2.select("div.graph_box strong.graph_percent").get(1);
							String age2 = age_20.text();
							String age20 = age2.replaceAll("%", "");
							System.out.println("age_20=" + age20);

							Element age_30 = doc2.select("div.graph_box strong.graph_percent").get(2);
							String age3 = age_30.text();
							String age30 = age3.replaceAll("%", "");
							System.out.println("age_30=" + age30);

							Element age_40 = doc2.select("div.graph_box strong.graph_percent").get(3);
							String age4 = age_40.text();
							String age40 = age4.replaceAll("%", "");
							System.out.println("age_40=" + age40);

							Element age_50 = doc2.select("div.graph_box strong.graph_percent").get(4);
							String age5 = age_50.text();
							String age50 = age5.replaceAll("%", "");
							System.out.println("age_50=" + age50);

							
							String doc1 = doc2.toString();
							int sper = doc1.indexOf("sPer") + 5;
							String atemp = doc1.substring(sper, sper + 10);
							atemp=atemp.substring(atemp.indexOf(":")+1,atemp.indexOf("}"));
							String a = atemp.trim();
							double male_rating = Double.parseDouble(a);
							System.out.println("malerating=" + male_rating);

							int sper2 = doc1.indexOf("sPer", sper) + 5;
							String btemp = doc1.substring(sper2, sper2 + 10);
							btemp=btemp.substring(btemp.indexOf(":")+1,btemp.indexOf("}"));
							System.out.println("btemp="+btemp);
							String b=btemp.trim();
							double female_rating = Double.parseDouble(b);
							System.out.println("femalerating=" + female_rating);

							
							
							
							WatchingTrendVO vo = new WatchingTrendVO();
							
							
							vo.setMovie_id(movie_id);
							System.out.println("vo.movie_id=" + vo.getMovie_id());

							/*
							 * double mr = Double.parseDouble(male_rating.text()); vo.setMale_rating(mr);
							 * 
							 * double fr = Double.parseDouble(female_rating.text());
							 * vo.setFemale_rating(fr);
							 */

							double age101 = Double.parseDouble(age10);
							vo.setAge_10(age101);

							double age201 = Double.parseDouble(age20);
							vo.setAge_20(age201);

							double age301 = Double.parseDouble(age30);
							vo.setAge_30(age301);

							double age401 = Double.parseDouble(age40);
							vo.setAge_40(age401);

							double age501 = Double.parseDouble(age50);
							vo.setAge_50(age501);
							vo.setMale_rating(male_rating);
							

							vo.setFemale_rating(female_rating);
							System.out.println("vo.male_rating=" + vo.getMale_rating());
							// Watchingtrend 수집 끝
							System.out.println("dao=" + dao);

							list.add(vo);
							dao.WatchingTrendInsert(vo);

						} catch (Exception ex) {
						}
					}
					if (isSameMovieId == true) {
						break;
					}
				}
			}
			// system.out.println("DataBase Insert End....");
		} catch (Exception ex) {
		}
		System.out.println("count : " + count);
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WatchingTrendManager m = new WatchingTrendManager();
		List<WatchingTrendVO> list = m.WatchingData();
		System.out.println("actual count : " + list.size());
	}

}
