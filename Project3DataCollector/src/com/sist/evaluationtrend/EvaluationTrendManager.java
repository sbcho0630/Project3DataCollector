package com.sist.evaluationtrend;

import java.util.*;
import java.lang.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EvaluationTrendManager {
	public List<EvaluationTrendVO> EvaluationData() {
		List<EvaluationTrendVO> list = new ArrayList<EvaluationTrendVO>();
		int count = 0;
		int preMovieId = -1;
		boolean isSameMovieId = false;
		try {

			EvaluationTrendDAO dao = new EvaluationTrendDAO();
			for (int k = 16; k <= 16; k++) {
				
				isSameMovieId = false;
				
				String mainlink="https://movie.naver.com/movie/sdb/browsing/bmovie.nhn?form="+k+"&page=";
				////system.out.println("mainlink="+mainlink);
				for (int i = 1; i <= 2800; i++) {
					////system.out.println("i : " + i);
					Document doc = Jsoup
							.connect(mainlink + i )
							.get();
					////system.out.println("mainlink+i="+mainlink+i);
					Elements link = doc.select("ul.directory_list > li > a");
					
					if(link == null){
						break;
					}
					
					////system.out.println(link);

					// link=movie/bi/mi/basic.nhn?code=24239
					for (int j = 0; j < link.size(); j++) {
						// link.size()는 페이지당 a링크의 갯수 ==> 네이버 뮤직의경우 한페이지에 20개 따라서 link.size()=20
						////system.out.println("j : " + j);
						try {
							Element elem = link.get(j);
							////system.out.println((i) + "-" + elem.attr("href"));
							String mLink = "https://movie.naver.com/" + elem.attr("href");

							mLink = mLink.replace("basic", "point");
							System.out.println("mlink=" + mLink);
							Document doc2 = Jsoup.connect(mLink).get();
							//System.out.println("doc2="+doc2);
							String str = elem.attr("href");
							String movieno = str.substring(str.lastIndexOf("=") + 1);
							int movie_id = Integer.parseInt(movieno);
							////system.out.println("preMovieId : " + preMovieId);
							////system.out.println("curMovieId : " + movie_id);
							if(preMovieId == movie_id){
								isSameMovieId = true;
								break;
							}
							count++;
							if(j == 0){
								preMovieId = movie_id;
							}
							
							////system.out.println("movieno=" + movieno);

							Element evaluation_point = doc2.select("div#netizen_point_tab_inner").get(0);
							System.out.println("evaluation_point=" + evaluation_point.text());

							Element people_count = doc2.selectFirst("span.user_count em");
							////system.out.println("people_count=" + people_count.text());

							Element male_rating = doc2.selectFirst("div.grp_male strong.graph_point");
							////system.out.println("male_rating=" + male_rating.text());

							Element female_rating = doc2.selectFirst("div.grp_female strong.graph_point");
							////system.out.println("female_rating=" + female_rating.text());

							Element age_10 = doc2.select("div.grp_age strong.graph_point").get(0);
							//system.out.println("age_10=" + age_10.text());

							Element age_20 = doc2.select("div.grp_age strong.graph_point").get(1);
							//system.out.println("age_20=" + age_20.text());

							Element age_30 = doc2.select("div.grp_age strong.graph_point").get(2);
							//system.out.println("age_30=" + age_30.text());

							Element age_40 = doc2.select("div.grp_age strong.graph_point").get(3);
							//system.out.println("age_40=" + age_40.text());

							Element age_50 = doc2.select("div.grp_age strong.graph_point").get(4);
							//system.out.println("age_50=" + age_50.text());

							Element production_point = doc2.select("li.point01 span.grp_score").get(0);
							String pp = production_point.text();
							pp = pp.replaceAll("%", "");
							//system.out.println("production_point=" + pp);

							Element acting_point = doc2.select("li.point02 span.grp_score").get(0);
							String ap = acting_point.text();
							ap = ap.replaceAll("%", "");
							//system.out.println("acting_point=" + ap);

							Element story_point = doc2.select("li.point03 span.grp_score").get(0);
							String sp = story_point.text();
							sp = sp.replaceAll("%", "");
							//system.out.println("story_point=" + sp);

							Element visual_point = doc2.select("li.point04 span.grp_score").get(0);
							String vp = visual_point.text();
							vp = vp.replaceAll("%", "");
							//system.out.println("visual_point=" + vp);

							Element ost_point = doc2.select("li.point05 span.grp_score").get(0);
							String op = ost_point.text();
							op = op.replaceAll("%", "");
							//system.out.println("ost_point=" + op);

							//system.out.println("==============================================");

							EvaluationTrendVO vo = new EvaluationTrendVO();
							vo.setMovie_id(movie_id);
							//system.out.println("vo.movie_id=" + vo.getMovie_id());
							double ep = Double.parseDouble(evaluation_point.text());
							vo.setEvaluation_point(ep);

							int pc = Integer.parseInt(people_count.text());
							vo.setPeople_count(pc);

							double mr = Double.parseDouble(male_rating.text());
							vo.setMale_rating(mr);

							double fr = Double.parseDouble(female_rating.text());
							vo.setFemale_rating(fr);

							double age10 = Double.parseDouble(age_10.text());
							vo.setAge_10(age10);

							double age20 = Double.parseDouble(age_20.text());
							vo.setAge_20(age20);

							double age30 = Double.parseDouble(age_30.text());
							vo.setAge_30(age30);

							double age40 = Double.parseDouble(age_40.text());
							vo.setAge_40(age40);

							double age50 = Double.parseDouble(age_50.text());
							vo.setAge_50(age50);

							int productionp = Integer.parseInt(pp);
							vo.setProduction_point(productionp);

							int actingp = Integer.parseInt(ap);
							vo.setActing_point(actingp);

							int storyp = Integer.parseInt(sp);
							vo.setStory_point(storyp);

							int visualp = Integer.parseInt(vp);
							vo.setVisual_point(visualp);

							int ostp = Integer.parseInt(op);
							vo.setOst_point(ostp);
							
							
							//EvaluationTrend 수집 끝
							System.out.println("dao="+dao);
							
							list.add(vo);
							 //dao.EvaluationTrendInsert(vo);

						} catch (Exception ex) {
						}
					}
					if(isSameMovieId == true){
						break;
					}
				}
			}
			//system.out.println("DataBase Insert End....");
		} catch (Exception ex) {
		}
		System.out.println("count : " + count);
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//system.out.println("hello");
		EvaluationTrendManager m = new EvaluationTrendManager();
		List<EvaluationTrendVO> list = m.EvaluationData();
		System.out.println("actual count : " + list.size());
	}

}
