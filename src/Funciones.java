import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Funciones {
    //Funcion que encuentra el codigo de la moneda del pais
    public static String codigoMoneda (String pais) {
        Gson gson = new Gson();
        String fichero = "";
        String revision=pais;
        try (BufferedReader br = new BufferedReader(new FileReader("codigopaises.json"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Type userListType = new TypeToken<ArrayList<ConsultaDivisas.consultaDivisas>>(){}.getType();
        ArrayList<ConsultaDivisas.consultaDivisas> userArray = gson.fromJson(fichero, userListType);
        for(ConsultaDivisas.consultaDivisas consultaDivisas : userArray) {
            if (Objects.equals(consultaDivisas.getPais(), pais)) {
                pais=consultaDivisas.getCodigo();

            }
        }
        if (revision.equals(pais)){
            System.out.println("\t El pais fue mal escrito, vuelva a ingresarlo");
        }
        return pais;
    }

    //Funcion para obtener la conversion de la moneda seleccionada
    static void convertir(double valor, String paisOrigen, String paisDestino){
        String divisasOrigen = "";
        String divisasDestino = "";
        double cantidadMoneda = 0;
        Scanner leer = new Scanner(System.in);

        Gson gson = new Gson();
        String fichero = "";
        try (BufferedReader br = new BufferedReader(new FileReader("codigopaises.json"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Type userListType = new TypeToken<ArrayList<ConsultaDivisas.consultaDivisas>>() {
        }.getType();
        ArrayList<ConsultaDivisas.consultaDivisas> userArray = gson.fromJson(fichero, userListType);
        for (ConsultaDivisas.consultaDivisas consultaDivisas : userArray) {
            if (Objects.equals(consultaDivisas.getCodigo(), paisOrigen)) {
                divisasOrigen = consultaDivisas.getDivisa();
            }
            if (Objects.equals(consultaDivisas.getCodigo(), paisDestino)) {
                divisasDestino = consultaDivisas.getDivisa();
            }
        }
        
        while (valor>=cantidadMoneda) {
            System.out.printf("\t Ingrese la cantidad de "+divisasOrigen+" que desea convertir a "+divisasDestino+" :", paisOrigen);
            cantidadMoneda = leer.nextDouble();
            double moneda = cantidadMoneda / valor;
            moneda = (double) Math.round(moneda);
            if (cantidadMoneda>=valor) {
                System.out.println(Variables.fVerde + "\t El valor de $"+cantidadMoneda+" " +divisasOrigen+" son $"+moneda+" "+divisasDestino+Variables.b);
            }else{
                System.out.println("\t La cantidad ingresada debe ser mayor que "+Variables.rojo+valor+Variables.b);
            }
        }
    }
}
