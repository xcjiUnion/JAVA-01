package io.github.kimmking.gateway.router;

import java.util.List;

/**
 * 轮询路由
 * @author jixch
 *
 */
public class RollPollingHttpEndpointRouter {

	private List<String> urls;
	
	private int currentIndex;  
    private int totalUrl; 
    
    public RollPollingHttpEndpointRouter(List<String> urls) {
    	this.urls = urls;
    	totalUrl = urls.size();
		currentIndex = totalUrl - 1;
    }
    
	public String route() {
		return round();
	}
	
	/**
	 * 轮询
	 * @return
	 */
    public String round() {  
    	//当前索引取余
        currentIndex = (currentIndex + 1) % totalUrl;  
        return urls.get(currentIndex);  
    }  

}
