package com.nanosoft.mystockportfolio.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nanosoft.mystockportfolio.model.Stock;

@RestController
@RequestMapping("/stocks")
public class StockResource {
	@Autowired
	private RestTemplate restTemplate;
	List<Stock> allStockList ;

	@RequestMapping("/allStocks")
	public List<Stock> getAllStocks() {
		System.out.println("start getAllStocks");
		// restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId()
		// , Movie.class);
		if (allStockList == null) {
			allStockList = new ArrayList<Stock>();
			List<String> urls = new ArrayList<String>();
			String URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=BA&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=BSVN&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=BABA&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=BAC&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=BIDU&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=GOLD&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=BHC&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=BLDP&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			URL1 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=SAN&apikey=LBLQ2L19LXXNSXHZ";
			urls.add(URL1);
			// System.out.println("getStocks1");
			for (String URL : urls) {
				ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
						new ParameterizedTypeReference<String>() {
						});
				// System.out.println("getStocks2");
				String pojoObj = responseEntity.getBody();
				// System.out.println("getStocks3");
				// System.out.println(pojoObj);
				JSONParser parse = new JSONParser();
				try {
					Stock s1 = new Stock();
					JSONObject jobj = (JSONObject) parse.parse(pojoObj);
					JSONObject jobj1 = (JSONObject) jobj.get("Meta Data");
					if (jobj1 != null) {
						s1.setCompanyName((String) jobj1.get("2. Symbol"));

						JSONObject jobj2 = (JSONObject) jobj.get("Time Series (Daily)");
						// System.out.println(jobj2.get("2019-06-06"));
						// JSONArray jobj3 = (JSONArray) jobj2.get("Time Series (Daily)");
						// System.out.println(jobj3.get(1));

						JSONObject jobj3 = (JSONObject) jobj2.get("2019-06-06");
						String high = (String) jobj3.get("2. high");
						s1.setHigh(Double.parseDouble(high));
						String low = (String) jobj3.get("3. low");
						s1.setLow(Double.parseDouble(low));
						String volume = (String) jobj3.get("5. volume");
						s1.setVolume(Integer.parseInt(volume));
						System.out.println("Stock Symbol:" + s1.getCompanyName() + " High:" + s1.getHigh() + " Low:"
								+ s1.getLow() + " Volume:" + s1.getVolume());
						allStockList.add(s1);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println("end getAllStocks");
		return allStockList;

	}

	@RequestMapping("/{companyName}")
	public Stock getStockDetailsOfCompany(@PathVariable("companyName") String companyName) {
		System.out.println("start getStockDetailsOfCompany");
		List<Stock> allStockList = getAllStocks();
		for (Stock stock : allStockList) {
			if (stock.getCompanyName().equalsIgnoreCase(companyName))
				return stock;
		}
		System.out.println("end getStockDetailsOfCompany");
		return null;

	}

}
