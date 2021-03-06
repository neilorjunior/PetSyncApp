package br.com.petsync.petsync.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Virgínia on 11/07/2016.
 */
public class WebClientEstablishments {

    public String getJsonFromUrl(String urlString) {

        String retorno = null;
        InputStream inputStream;
        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //connection.setRequestMethod("GET");
            //Object content = connection.getContent();
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            int responseCode = connection.getResponseCode();


            if(responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = connection.getInputStream();

            } else {
                inputStream = connection.getErrorStream();
            }
            String contentEncoding = connection.getContentEncoding();
            retorno = converterInputStreamToString(inputStream);
            inputStream.close();
            connection.disconnect();

            /*URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            //int codigoResposta;
            InputStream is;

            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            //codigoResposta = conexao.getResponseCode();
            //if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                is = conexao.getInputStream();
            //}else{
            //    is = conexao.getErrorStream();
            //}

            retorno = converterInputStreamToString(is);
            is.close();
            conexao.disconnect();*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    private static String converterInputStreamToString(InputStream inputStream){
        StringBuffer buffer = new StringBuffer();
        try{

            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);


            String line = null;
            while ((line = bReader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            inputStream.close();

            /*BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();*/
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }

}
