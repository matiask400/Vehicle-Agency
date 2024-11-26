package ar.edu.utn.frc.bda.client;

import ar.edu.utn.frc.bda.config.Coordenadas;
import ar.edu.utn.frc.bda.config.ZonaRestringida;
import ar.edu.utn.frc.bda.controller.DTO.DTOAgencia;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ApiClient {
    private String urlString = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";

    public DTOAgencia getAgenciaInfo(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(urlString, String.class);

            if (response == null){
                throw new RuntimeException("Respuesta de la API nula");
            }

            JSONObject jsonResponse = new JSONObject(response);
            JSONObject coordenadasAgencia = jsonResponse.getJSONObject("coordenadasAgencia");
            double lat = coordenadasAgencia.getDouble("lat");
            double lon = coordenadasAgencia.getDouble("lon");
            int radioAdmitido = jsonResponse.getInt("radioAdmitidoKm");

            // Procesamos las zonas restringidas
            JSONArray zonasRestringidasJson = jsonResponse.getJSONArray("zonasRestringidas");
            List<ZonaRestringida> zonasRestringidas = new ArrayList<>();

            for (int i = 0; i < zonasRestringidasJson.length(); i++) {
                JSONObject zona = zonasRestringidasJson.getJSONObject(i);
                JSONObject noroeste = zona.getJSONObject("noroeste");
                JSONObject sureste = zona.getJSONObject("sureste");

                zonasRestringidas.add(new ZonaRestringida(
                        new Coordenadas(noroeste.getDouble("lat"), noroeste.getDouble("lon")),
                        new Coordenadas(sureste.getDouble("lat"), sureste.getDouble("lon"))
                ));
            }

            return new DTOAgencia(new Coordenadas(lat, lon), radioAdmitido, zonasRestringidas);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener información de la agencia.");
        }
    }
}