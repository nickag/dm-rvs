import java.net.*;
import java.io.*;


public class requestRead
{
  public static void main(String args[])
  {

    doHttpUrlConnectionAction(args[0]);
  }
  private String doHttpUrlConnectionAction(String desiredUrl) throws Exception
	{
	URL url = null;
    BufferedReader reader = null;
    StringBuilder stringBuilder;
    try
    {
      // create the HttpURLConnection
      url = new URL(desiredUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       
      //just want to do an HTTP GET here

      connection.setRequestMethod("GET");
       
      // uncomment this if you want to write output to this url
		// connection.setDoOutput(true);
       
      //give it 15 seconds to respond
      connection.setReadTimeout(15*1000);
      connection.connect();
 
      //read the output from the server
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      stringBuilder = new StringBuilder();
 
      String line = null;
      while ((line = reader.readLine()) != null)
      {
        stringBuilder.append(line + "\n");
      }
      return stringBuilder.toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
    finally
    {
      // close the reader; this can throw an exception too, so
      // wrap it in another try/catch block.
      if (reader != null)
      {
        try
        {
          reader.close();
        }
        catch (IOException ioe)
        {
          ioe.printStackTrace();
        }
      }
    }
}
