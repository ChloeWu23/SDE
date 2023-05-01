package Q2;

import java.util.ArrayList;
import java.util.List;

public class HttpRequestBuilder {
    private  String url=null;
    private String body=null;
    private List<String> params=new ArrayList<>(); //debug here
    private HttpRequest.Method method=null; //debug here; using enum from other class, no need to include enum in this class
    private  List<String> headers=new ArrayList<>();

    public HttpRequestBuilder withUrl(String url){
        this.url = url;
        return this;
    }

    public HttpRequestBuilder withBody(String body){
        this.body = body;
        return this;
    }

    public HttpRequestBuilder withParams(String p){
        this.params.add(p); //debug here
        return this;
    }

    public HttpRequestBuilder withMethod(HttpRequest.Method m){
        this.method= m;
        return this;
    }

    public HttpRequestBuilder withHeader(String headers){
        this.headers.add(headers);
        return this;
    }

    public HttpRequest build(){
        return new HttpRequest(url,method,params,headers,body);
    }
}
