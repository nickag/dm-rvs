import java.net.URL;
import java.net.HttpURLConnection;
import java.io.*;
import java.util.Date;
import java.net.SocketTimeoutException;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

import java.nio.ByteBuffer;
import java.util.*;

import java.io.UnsupportedEncodingException;


public class urltest{

	public static void main(String argv[])

	{

		String Url=argv[0];
		String USER_AGENT= "Mozilla/5.0 ;Windows NT 6.2; WOW64; rv:27.0; Gecko/20100101 Firefox/27.0";
		String msg=Url+";";
		int responseCode=500;
		float dnsLookupTime=0,elapsedTime=0,ttfb=0;
		try{

			URL obj=new URL(Url);
			String host=obj.getHost();
			long start=new Date().getTime();
			InetAddress ip=InetAddress.getByName(host);          
			dnsLookupTime=(new Date().getTime()-start)/1000f;
		

		    HttpURLConnection con= (HttpURLConnection)obj.openConnection();
			//System.out.println((new Date().getTime()-start)/1000f);
		    con.setRequestMethod("GET");
		    con.setRequestProperty("User-Agent",USER_AGENT);
		    //System.out.println(con.getConnectTimeout());
			//con.setConnectTimeout(2000);
		    con.setReadTimeout(5000);
		     start=new Date().getTime();
		    //con.connect();
		    responseCode=con.getResponseCode();
		    //elapsedTime=(new Date().getTime()-start)/1000f;
		    
			if (responseCode<HttpURLConnection.HTTP_BAD_REQUEST){
		        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
				int firstByte=0;
		        while ((inputLine = in.readLine()) != null) {
					if (firstByte==0)
					{
						firstByte=1;
						ttfb=(new Date().getTime()-start)/1000f;
					}
					
		            response.append(inputLine);
		        }
				elapsedTime=(new Date().getTime()-start)/1000f;	//total_time
		            in.close();

		            System.out.println(response.toString());
		      } else
		      	System.out.println("GET request Failed(400/500) Error");
		    
		    System.out.println(Url+" ; Dns Lookup Time: "+dnsLookupTime+"ms ; Time Taken: "+elapsedTime+" ms ;  Response Code: "+responseCode+ " ; "+Thread.currentThread().getName());
        }
		catch(UnknownHostException ue){
		
			System.out.println(ue);	
		}
		catch(SocketTimeoutException te){
				responseCode=408;
				System.out.println(te);	
			}	
        catch(Exception e){
            e.printStackTrace();
					
        }
		finally{
			
	        msg+=Integer.toString(responseCode)+";"+ Float.toString(dnsLookupTime)+";"+Float.toString(ttfb)+";"+Float.toString(elapsedTime);
			System.out.println(msg);
		}



	}


}
