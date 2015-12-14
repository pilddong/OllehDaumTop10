package functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
/**
 * @see "http://www.tistory.com/guide/api/post"
 * API 雅뚯눘�펔ttp://twitiwt.tistory.com/api ID1955909占쎈챷弛놅옙�쇱춳�얜챸釉�묾罹얶DHYXG7
 */
public class TistoryClient {
    private static final String ACCESS_TOKEN = "cab82b722e31ca038a042bff0fe5092e_1b10c8255a77db2f5152b974d1c48f12";

    private static final String CLIENT_ID = "9c7bbaa9d598879d9666b86f0f969412";
    private static final String SECRET_KEY = "9c7bbaa9d598879d9666b86f0f969412077343b9a65ef69b196fae537a46590dea587963";
    private static final String REDIRECT_URI = "http://168.131.153.174:8080/OllehMiningEngine/sns_success.jsp";

    private static final String TARGET_URL = "ollehidea";
    private static final String WRITE_API_URL = "https://www.tistory.com/apis/post/write";
    private static final String MODIFY_API_URL = "https://www.tistory.com/apis/post/modify";
    private static final String CATEGORY_LIST_API_URL = "https://www.tistory.com/apis/category/list";

    // �뚣끋逾�獄쏉옙堉긴틠�⑤┛
    private static final String DONATE = "<iframe src=\"http://gift.blog.daum.net/widget?entryId=0&amp;setNo=2043\" width=\"100%\" height=\"250\" frameborder=\"0\" border=\"0\" scrolling=\"no\" allowtransparency=\"true\" ;=\"\"></iframe>";

    private static final String OUTPUT = "json";

    private static final String GAME_CATEGORY = "607233";

    private static final String USER_AGENT = "Mozilla/5.0";

    public void getAccessToken() {
        String clientId = "9c7bbaa9d598879d9666b86f0f969412";
        String clientSecret = "9c7bbaa9d598879d9666b86f0f969412077343b9a65ef69b196fae537a46590dea587963";
        String redirectUri = "http://168.131.153.174:8080/OllehMiningEngine/sns_success.jsp";
        String grantType = "authorization_code";

//        String requestUrl = "https://www.tistory.com/oauth/access_token/?code=" + authorization_code +
//                "&client_id=" + CLIENT_ID +
//                "&client_secret=" + SECRET_KEY +
//                "&redirect_uri=" + REDIRECT_URI +
//                "&grant_type=" + grantType;
    }
    /**
     * write tistory article
     * @param tistoryBrainDotsArticle
     */
    public void write(TistoryBrainDotsArticle tistoryBrainDotsArticle) throws IOException {
        // common validation check
        checkCommonValidation(tistoryBrainDotsArticle);

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(WRITE_API_URL);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        // String title = "�됰슢�낉옙占쏙옙袁る뱜 占썬끋�믭옙�곻옙 " + tistoryBrainDotsArticle.getStage() + " 占쎈��곻옙占쏙옙�덉겫占쏙옙;
        String title = tistoryBrainDotsArticle.getTitle();


        String content = tistoryBrainDotsArticle.getContent();
        String category = tistoryBrainDotsArticle.getCategory();
        //        "<h2>�됰슢�낉옙占쏙옙袁る뱜 占썬끋�믭옙�곻옙 " + tistoryBrainDotsArticle.getTitle() + " 占쎈��곻옙占�h2>" +
        //                "<div>" + tistoryBrainDotsArticle.getYoutube() + "</div>\n" +
        //                "<div>" + tistoryBrainDotsArticle.getStrategy() + "</div>\n" +
        //                "<div>" + DONATE + "</div>\n";

        //Set<String> tagSets = getBrainDotsTags();

        //tagSets.add(tistoryBrainDotsArticle.getTitle());

        //String tags = Joiner.on(",").join(tagSets);
        String tags = tistoryBrainDotsArticle.getTag();

        List urlParameters = Lists.newArrayList();
        urlParameters.add(new BasicNameValuePair("access_token", ACCESS_TOKEN));
        urlParameters.add(new BasicNameValuePair("targetUrl", TARGET_URL)); // �곗뒪�좊━ 二쇱냼. http://xxx.tistory.com �쇨꼍��xxx 留��낅젰, 2李⑤룄硫붿씤��寃쎌슦 http://�쒓굅��url �낅젰
        urlParameters.add(new BasicNameValuePair("output", OUTPUT));    // output type

        urlParameters.add(new BasicNameValuePair("title", title));  // �쒕ぉ
        urlParameters.add(new BasicNameValuePair("content", content));  // �댁슜
        urlParameters.add(new BasicNameValuePair("category", category));   // 移댄뀒怨좊━
        urlParameters.add(new BasicNameValuePair("tag", tags)); // �쒓렇

        if (tistoryBrainDotsArticle.getVisibility() != null) {
            urlParameters.add(new BasicNameValuePair("visibility", tistoryBrainDotsArticle.getVisibility()));
            System.out.println(tistoryBrainDotsArticle.getVisibility());
        }

        post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

        HttpResponse response = client.execute(post);

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
    }

    /*
    public void modify(TistoryBrainDotsArticle tistoryBrainDotsArticle) throws Exception {
        // validation check for modify
        if (tistoryBrainDotsArticle.getPostId() == null) {
            throw new RuntimeException("postId needed");
        }

        // common validation check
        checkCommonValidation(tistoryBrainDotsArticle);


        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(MODIFY_API_URL);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        String title = "�됰슢�낉옙占쏙옙袁る뱜 占썬끋�믭옙�곻옙 " + tistoryBrainDotsArticle.getStage() + " 占쎈��곻옙占쏙옙�덉겫占쏙옙;

       String content =
                "<h2>�됰슢�낉옙占쏙옙袁る뱜 占썬끋�믭옙�곻옙 " + tistoryBrainDotsArticle.getStage() + " 占쎈��곻옙占�h2>" +
                "<div>" + tistoryBrainDotsArticle.getYoutube() + "</div>" +
                "<div>" + tistoryBrainDotsArticle.getStrategy() + "</div>" +
                "<div>" + DONATE + "</div>\n";

        Set<String> tagSets = getBrainDotsTags();

        tagSets.add(tistoryBrainDotsArticle.getStage());

        String tags = Joiner.on(",").join(tagSets);

        List urlParameters = Lists.newArrayList();
        urlParameters.add(new BasicNameValuePair("access_token", ACCESS_TOKEN));
        urlParameters.add(new BasicNameValuePair("targetUrl", TARGET_URL)); //
        urlParameters.add(new BasicNameValuePair("title", title));  // 占쎌뮆��        urlParameters.add(new BasicNameValuePair("content", content));  // 占쎈똻��        urlParameters.add(new BasicNameValuePair("category", GAME_CATEGORY));   // 燁삳똾�믤�醫듼봺
        urlParameters.add(new BasicNameValuePair("tag", tags)); // 占쎌뮄��        urlParameters.add(new BasicNameValuePair("output", OUTPUT));    // output type

        urlParameters.add(new BasicNameValuePair("postId", tistoryBrainDotsArticle.getPostId()));   // only modify

        post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

        HttpResponse response = client.execute(post);

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
    }
    */

    protected void checkCommonValidation(TistoryBrainDotsArticle tistoryBrainDotsArticle) {
       /* if (tistoryBrainDotsArticle.getYoutube() == null) {
            throw new RuntimeException("youtube needed");
        }

        if (tistoryBrainDotsArticle.getStage() == null) {
            throw new RuntimeException("stage needed");
        }

        if (tistoryBrainDotsArticle.getStrategy() == null) {
            throw new RuntimeException("strategy needed");
        }
        */
        
        if (tistoryBrainDotsArticle.getTitle() == null) {
            throw new RuntimeException("title needed");
        }
        if (tistoryBrainDotsArticle.getContent() == null) {
            throw new RuntimeException("content needed");
        }
    }


    /**
     * brain dots tags
     * @return tags set
     */
    private Set<String> getBrainDotsTags() {
        Set<String> tagSets = Sets.newHashSet();
        tagSets.add("�됰슢�낉옙紐껊즲占쏙옙");
        tagSets.add("braindots");
        tagSets.add("brain dots");
        tagSets.add("野껊슣��");
        tagSets.add("game");
        tagSets.add("占쎌눘已�");
        tagSets.add("puzzle");
        tagSets.add("�⑤벉��");
        tagSets.add("strategy");
        tagSets.add("占쎈��곻옙占�");
        tagSets.add("clear");
        tagSets.add("�믩챶��");
        tagSets.add("brain");

        return tagSets;
    }

    /**
     * get category list
     * @throws IOException
     */
    public void categoryList() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        String output = "json";
        HttpGet request = new HttpGet(CATEGORY_LIST_API_URL + "?access_token=" + ACCESS_TOKEN + "&targetUrl=" + TARGET_URL + "&output=" + output);

        // add header
        request.setHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
    }
}