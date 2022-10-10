package EjercicioVodafone;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
//@author ajmerino
public class ejercicio {

	public static void main(String[] args) {
		try {
			System.out.println("********************************************************");
			System.out.println("      Introduzca el nombre de la cripto  deseada:");
			System.out.println("********************************************************");
			 Scanner entrada=new Scanner(System.in);
			String cripto=entrada.nextLine();
			URL url = new URL("https://api.coingecko.com/api/v3/coins/"+cripto);
			HttpsURLConnection conectar = (HttpsURLConnection) url.openConnection();
			conectar.setRequestMethod("GET");
			conectar.connect();

			int Respuesta = conectar.getResponseCode();

			if (Respuesta == 200) {
				StringBuilder informacion = new StringBuilder();
				Scanner scaner = new Scanner(url.openStream());
				while (scaner.hasNext()) {
					informacion.append(scaner.nextLine());
				}
				scaner.close();
				
				String informacionBuena = informacion.toString();
			
				String[] parte1 = informacionBuena.split("usd\":");
				String parte11 = parte1[0]; 
				String parte12 = parte1[1]; 
				String[] parte2 = parte12.split(",\"vef");
				String parte21 = parte2[0]; 
				String parte22 = parte2[1]; 
				System.out.println("El precio actual del "+cripto+" es : "+parte21 +" Dolares");
				
				
			} else if(Respuesta == 404) {
				System.out.println("No existe ninguna criptomoneda con el id: "+cripto);
			}else {
				throw new RuntimeException("Se ha obtenido una respuesta inesperada.");
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
