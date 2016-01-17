package functions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DaumTopKeywords {

	String pageUrl="http://www.daum.net"; 
	Document document;
	HashSet<String> searchlist;
	public DaumTopKeywords() {
		// TODO Auto-generated constructor stub
	}
	
	public HashSet<String> getKeywords() {
		
		searchlist = new HashSet<String>();
		//Document document = Jsoup.connect(pageUrl).get();
	    try {
			document = Jsoup.parse(new URL(pageUrl).openStream(),"UTF-8", pageUrl);
		
		    //Document document = Jsoup.parse(new URL(pageUrl), 1000);
		    
		    
		    
		    if (null != document) {
		        // class 가 rank_dummy 를 가지고 있는 div 태그는 포함시키지 않는다.
		        Elements elements = document.select("ol#realTimeSearchWord > li > div.roll_txt > div:not(.rank_dummy)");
		         
		        for (int i = 0; i < elements.size(); i++) {
		            //System.out.println("------------------------------------------");
		            //System.out.println("검색어 : " + elements.get(i).select("span.txt_issue > a").text());
		            
		            //System.out.println("랭킹 : " + (i + 1));
		            //System.out.println("상승여부 : " + elements.get(i).select("span.screen_out").text());
		             
		            // <em class="img_vert txt_num ico_up"><span class="screen_out">상승</span>10</em>
		            // 위 HTML 에서 상승단계를 구하기 위해서 상승여부를 제거합니다.
		            //elements.get(i).select("span.screen_out").remove();
		             
		            //System.out.println("상승단계 : " + elements.get(i).select("em.img_vert").html());
		            //System.out.println("링크 URL : " + elements.get(i).select("span.txt_issue > a").attr("href"));
		            //System.out.println("------------------------------------------");
		            String title = elements.get(i).select("span.txt_issue > a").text();
		            //for(String s : title.split(" ")) {
		            		
		            		//searchlist.add(s);
		            
		            //}
		            searchlist.add(title);
	
		            //System.out.println(title);
		         } 
		     }
	    } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return searchlist;
	}
	    
    
}
