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
		        // class �� rank_dummy �� ������ �ִ� div �±״� ���Խ�Ű�� �ʴ´�.
		        Elements elements = document.select("ol#realTimeSearchWord > li > div.roll_txt > div:not(.rank_dummy)");
		         
		        for (int i = 0; i < elements.size(); i++) {
		            //System.out.println("------------------------------------------");
		            //System.out.println("�˻��� : " + elements.get(i).select("span.txt_issue > a").text());
		            
		            //System.out.println("��ŷ : " + (i + 1));
		            //System.out.println("��¿��� : " + elements.get(i).select("span.screen_out").text());
		             
		            // <em class="img_vert txt_num ico_up"><span class="screen_out">���</span>10</em>
		            // �� HTML ���� ��´ܰ踦 ���ϱ� ���ؼ� ��¿��θ� �����մϴ�.
		            //elements.get(i).select("span.screen_out").remove();
		             
		            //System.out.println("��´ܰ� : " + elements.get(i).select("em.img_vert").html());
		            //System.out.println("��ũ URL : " + elements.get(i).select("span.txt_issue > a").attr("href"));
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
